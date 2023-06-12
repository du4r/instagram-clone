package com.du4r.instagramclone.search.view

import android.app.SearchManager
import android.content.Context
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.du4r.instagramclone.R
import com.du4r.instagramclone.common.base.BaseFragment
import com.du4r.instagramclone.common.base.DependencyInjector
import com.du4r.instagramclone.common.model.UserAuth
import com.du4r.instagramclone.databinding.FragmentSearchBinding
import com.du4r.instagramclone.search.Search
import com.du4r.instagramclone.search.presentation.SearchPresenter
import java.util.UUID

class SearchFragment : BaseFragment<FragmentSearchBinding, Search.Presenter>(
    R.layout.fragment_search, FragmentSearchBinding::bind
), Search.View {

    override fun showProgress(enabled: Boolean) {
        binding?.searchProgress?.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    override fun displayFullUsers(users: List<UserAuth>) {
        binding?.searchTxtEmpty?.visibility = View.GONE
        binding?.searchRv?.visibility = View.VISIBLE
        adapter.users = users
        adapter.notifyDataSetChanged()
    }

    override fun displayEmptyUsers() {
        binding?.searchTxtEmpty?.visibility = View.VISIBLE
        binding?.searchRv?.visibility = View.GONE
    }

    override lateinit var presenter: Search.Presenter

    private var searchListener: SearchListener? = null

    private val onItemClicked: (String) -> Unit = { uuid ->
        searchListener?.goToProfile(uuid)
    }

    private val adapter by lazy { SearchAdapter(onItemClicked) }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is SearchListener){
            searchListener = context
        }
    }

    override fun setupViews() {

        binding?.searchRv?.layoutManager = LinearLayoutManager(requireContext())
        binding?.searchRv?.adapter = adapter
    }

    override fun setupPresenter() {
        val repository = DependencyInjector.searchRepository(requireContext())
        presenter = SearchPresenter(this, repository)
    }

    override fun getMenu(): Int? {
        return R.menu.menu_search
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = (menu.findItem(R.id.menu_search).actionView as SearchView)

        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText?.isNotEmpty() == true){
                        presenter.fetchUsers(newText)
                        return true
                    }
                    return false
                }
            })
        }
    }

    interface SearchListener{
        fun goToProfile(uuid: String)
    }

}