package com.raxerz.news.ui.newslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.raxerz.news.R
import com.raxerz.news.data.model.NewsItem
import kotlinx.android.synthetic.main.fragment_news_list.*

class NewsListFragment: Fragment() {

    private lateinit var newsListAdapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_news_list, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        populateList()
    }

    private fun setupUi(){
        recyclerView.layoutManager = LinearLayoutManager(context)
        newsListAdapter = NewsListAdapter(arrayListOf())
        recyclerView.adapter = newsListAdapter
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