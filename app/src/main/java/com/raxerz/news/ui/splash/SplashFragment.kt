package com.raxerz.news.ui.splash

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.raxerz.news.R
import com.raxerz.news.ui.base.ViewModelFactory
import com.raxerz.news.ui.viewmodels.NewsListViewModel
import com.raxerz.news.utils.UIState
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.raxerz.news.data.model.NewsItem
import com.raxerz.news.utils.Resource
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment: Fragment() {

    private lateinit var newsListViewModel: NewsListViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_splash, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(requireActivity(), R.id.navhostFragment)
        setupViewModel()
        setupObserver()
    }

    private fun setupViewModel(){
        newsListViewModel = ViewModelProvider(this, ViewModelFactory())
            .get(NewsListViewModel::class.java)
    }

    private fun setupObserver(){
        newsListViewModel.getNewsItems().observe(requireActivity(), Observer {
            when(it.status){
                UIState.SUCCESS -> {
                    handleSuccessState(it)
                }
                UIState.LOADING -> {
                    handleLoadingState()
                }
                UIState.ERROR -> {
                    handleErrorState(it)
                }
            }
        })
    }

    private fun handleSuccessState(resource: Resource<NewsItem>) {
        Log.d("SplashFragment", "Success")
        progressBar.visibility = View.GONE
        resource.data?.toString()?.let { it1 -> Log.d("SplashFragment", it1) }
        resource.data?.let {
                newsItems ->

            // Alternate implementation
            /* val bundle = Bundle()
            bundle.putParcelable("data", resource.data)
            navController.navigate(R.id.action_splashFragment_to_nnewsListFragment, bundle) */

            val action = SplashFragmentDirections.actionSplashFragmentToNnewsListFragment(resource.data)
            navController.navigate(action)
        }
    }

    private fun handleLoadingState() {
        Log.d("SplashFragment", "Loading")
        progressBar.visibility = View.VISIBLE
    }

    private fun handleErrorState(resource: Resource<NewsItem>) {
        Log.d("SplashFragment", "Error")
        progressBar.visibility = View.VISIBLE
        Toast.makeText(context, resource.message, Toast.LENGTH_LONG).show()
    }
}