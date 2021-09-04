package com.raxerz.news.ui.newslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.raxerz.news.data.model.NewsItem
import com.raxerz.news.databinding.FragmentNewsListBinding

class NewsListFragment: Fragment() {

    private lateinit var newsListAdapter: NewsListAdapter
    private var binding: FragmentNewsListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsListBinding.inflate(inflater, container, false)
        val root = binding?.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        populateList()
    }

    private fun setupUi(){
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        newsListAdapter = NewsListAdapter(arrayListOf())
        binding?.recyclerView?.adapter = newsListAdapter
    }


    private fun populateList(){
        val newsItems = arguments?.getParcelable<NewsItem>("data")
        newsListAdapter.apply {
            newsItems?.let {
                addItems(it)
                notifyDataSetChanged()
            }
        }
    }

}