package com.du4r.instagramclone.common.base


import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment

abstract class BaseFragment<B, P: BasePresenter>(
    @LayoutRes layoutId: Int,
    val bind: (View) -> B
): Fragment(layoutId){

    protected var binding: B? = null

    abstract var presenter: P

    abstract fun setupViews()

    abstract fun setupPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMenu()?.let {
            setHasOptionsMenu(true)
        }
        setupPresenter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = bind(view)
        if (savedInstanceState == null){
            setupViews()
        }

    }

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        getMenu()?.let {
            menu.clear()
            inflater.inflate(it,menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    @MenuRes
    open fun getMenu(): Int?{
        return null
    }
}