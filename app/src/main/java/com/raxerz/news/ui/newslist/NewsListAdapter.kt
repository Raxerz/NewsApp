package com.raxerz.news.ui.newslist

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raxerz.news.R
import com.raxerz.news.data.model.NewsItem
import com.raxerz.news.data.model.SingleNewsItem
import com.raxerz.news.databinding.ItemNewsBinding
import kotlinx.android.synthetic.main.item_news.view.*

class NewsListAdapter(private val newsItems: ArrayList<SingleNewsItem>):
    RecyclerView.Adapter<NewsListAdapter.NewsListItemViewHolder>() {

    private var binding: ItemNewsBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListItemViewHolder {
        binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsListItemViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: NewsListItemViewHolder, position: Int) {
        holder.bind(newsItems[position])
    }

    override fun getItemCount(): Int = newsItems.size

    fun addItems(items: NewsItem){
        newsItems.apply {
            clear()
            items.articles?.let { addAll(it) }
        }
    }


    class NewsListItemViewHolder(binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(newsItem: SingleNewsItem){
            itemView.apply {
                Glide.with(imageView.context)
                    .load(newsItem.urlToImage)
                    .into(imageView)
                title.text = newsItem.title
                setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(newsItem.url))
                    imageView.context.startActivity(intent)
                }
            }
        }
    }
}