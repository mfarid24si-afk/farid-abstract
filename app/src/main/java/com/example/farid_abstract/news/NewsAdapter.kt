package com.example.farid_abstract.news

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.farid_abstract.R
import com.example.farid_abstract.databinding.ItemNewsBinding
import java.text.SimpleDateFormat
import java.util.Locale

class NewsAdapter(private var articles: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]
        holder.binding.apply {

            // Judul
            tvNewsTitle.text = article.title ?: "Tanpa Judul"

            // Deskripsi/Ringkasan
            tvNewsDesc.text = article.description ?: "Tidak ada ringkasan."

            // Format tanggal: "2024-01-15T10:30:00Z" → "15 Jan 2024"
            tvNewsDate.text = formatDate(article.publishedAt)

            // Thumbnail dengan Glide
            Glide.with(ivNewsThumbnail.context)
                .load(article.urlToImage)
                .placeholder(R.drawable.ic_launcher_background) // Gambar placeholder
                .error(R.drawable.ic_launcher_background)
                .centerCrop()
                .into(ivNewsThumbnail)

            // Klik item → buka URL berita di browser
            root.setOnClickListener {
                val url = article.url
                if (!url.isNullOrEmpty()) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    root.context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount() = articles.size

    fun updateData(newArticles: List<Article>) {
        articles = newArticles
        notifyDataSetChanged()
    }

    private fun formatDate(rawDate: String?): String {
        if (rawDate.isNullOrEmpty()) return ""
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale("id", "ID"))
            val date = inputFormat.parse(rawDate)
            outputFormat.format(date!!)
        } catch (e: Exception) {
            rawDate.take(10) // Fallback: ambil 10 karakter pertama
        }
    }
}