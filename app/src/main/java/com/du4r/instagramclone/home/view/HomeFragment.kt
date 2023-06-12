package com.du4r.instagramclone.home.view

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.du4r.instagramclone.R
import com.du4r.instagramclone.common.base.BaseFragment
import com.du4r.instagramclone.common.base.DependencyInjector
import com.du4r.instagramclone.common.model.Post
import com.du4r.instagramclone.databinding.FragmentHomeBinding
import com.du4r.instagramclone.home.Home
import com.du4r.instagramclone.home.presentation.HomePresenter
import com.du4r.instagramclone.profile.view.FeedAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding,Home.Presenter>(
    R.layout.fragment_home,
    FragmentHomeBinding::bind
),Home.View {

    override lateinit var presenter: Home.Presenter
    private val adapter = FeedAdapter()


    override fun setupViews() {
        binding?.homeRv?.layoutManager = LinearLayoutManager(requireContext())
        binding?.homeRv?.adapter = adapter

        presenter.fetchFeed()
    }

    override fun setupPresenter() {
        presenter = HomePresenter(this, DependencyInjector.homeRepository())
    }

    override fun getMenu() = R.menu.menu_profile
    override fun showProgress(enabled: Boolean) {
        binding?.homeProgress?.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    override fun displayRequestFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun displayEmptyPosts() {
        binding?.homeTxtEmpty?.visibility = View.VISIBLE
        binding?.homeRv?.visibility = View.GONE
    }

    override fun displayFullPosts(posts: List<Post>) {
        binding?.homeTxtEmpty?.visibility = View.GONE
        binding?.homeRv?.visibility = View.VISIBLE
        adapter.items = posts
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        binding = null
        super.onDestroy()
    }
}