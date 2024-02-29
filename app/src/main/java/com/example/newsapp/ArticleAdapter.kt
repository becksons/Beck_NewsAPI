package com.example.newsapp

import ListFragment
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ListFragmentItemBinding
import android.view.LayoutInflater
import com.example.newsapp.newsapi.Article



class ArticleAdapter(
    private var articles: List<Article> = emptyList(),
    private var fragment: ListFragment
) : RecyclerView.Adapter<ArticleHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListFragmentItemBinding.inflate(inflater, parent, false)
        return ArticleHolder(binding, fragment)
    }
    fun updateData(articleList: List<Article>) {
        notifyDataSetChanged()

        articles = articleList
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
    }

    override fun getItemCount() = articles.size
}
class ArticleHolder(
    private val binding: ListFragmentItemBinding,
    private val fragment: ListFragment
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(article: Article) {
        binding.titleText.text = article.title
        binding.authorText.text = article.author

        binding.root.setOnClickListener {

            MainActivity.title = article.title
            MainActivity.author = article.author ?: "Author"
            MainActivity.content = article.content ?: "Content"

            fragment.newsNavigation()
        }
    }
}