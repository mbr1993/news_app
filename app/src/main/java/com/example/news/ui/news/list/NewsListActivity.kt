package com.example.news.ui.news.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.example.news.BuildConfig.BASE_URL
import com.example.news.data.model.news.ArticleListResponse
import com.example.news.data.source.ArticleRestService
import com.example.news.databinding.ActivityNewsListBinding
import com.example.news.ui.news.detail.NewsDetailActivity
import com.example.news.ui.news.list.adapter.BreakingNewsAdapter
import com.example.news.ui.news.list.adapter.LastNewsAdapter
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber


class NewsListActivity : AppCompatActivity() {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val articleRestService: ArticleRestService = retrofit.create()
    private var callNewsList: Call<ArticleListResponse>? = null

    private val breakingNewsAdapter = BreakingNewsAdapter {
        startActivity(Intent(this, NewsDetailActivity::class.java))
    }
    private val lastNewsAdapter = LastNewsAdapter {
        startActivity(Intent(this, NewsDetailActivity::class.java))
    }

    private val binding by lazy { ActivityNewsListBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        loadNews()
    }

    override fun onDestroy() {
        callNewsList?.cancel()
        super.onDestroy()
    }

    private fun initViews() {
        with(binding) {
            breakingNewsRv.layoutManager =
                LinearLayoutManager(this@NewsListActivity, HORIZONTAL, false)
            breakingNewsRv.adapter = breakingNewsAdapter

            lastNewsRv.layoutManager =
                LinearLayoutManager(this@NewsListActivity, VERTICAL, false)
            lastNewsRv.adapter = lastNewsAdapter
        }
    }

    private fun loadNews() {
        callNewsList = articleRestService.getPopularArticles()

        callNewsList?.enqueue(object : Callback<ArticleListResponse> {
            override fun onResponse(
                call: Call<ArticleListResponse>,
                response: Response<ArticleListResponse>
            ) {
                Timber.d(response.toString())

                breakingNewsAdapter.setData(response.body()?.articles ?: listOf())
                lastNewsAdapter.setData(response.body()?.articles ?: listOf())
            }

            override fun onFailure(call: Call<ArticleListResponse>, t: Throwable) {
                Timber.e(t)
            }
        })
    }
}