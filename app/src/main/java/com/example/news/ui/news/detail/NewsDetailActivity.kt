package com.example.news.ui.news.detail

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.WindowInsetsControllerCompat
import com.example.news.R
import com.example.news.data.model.news.ArticleResponse
import com.example.news.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityNewsDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val articleResponse: ArticleResponse =
            intent.getSerializableExtra("article") as ArticleResponse

        with(binding) {
            titleTv.text = articleResponse.title
            newsImageSdv.setImageURI(articleResponse.imageUrl)
            authorTv.text = articleResponse.author
            sourceTv.text = articleResponse.source.name
            contentTv.text = articleResponse.content
            dateTv.text = articleResponse.publishedAt
        }
        setFullScreenFlags()
        updateStatusBarToDark()
    }

    override fun onDestroy() {
        clearFullScreenFlags()
        updateStatusBarToLight()
        super.onDestroy()
    }

    fun setFullScreenFlags() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    fun clearFullScreenFlags() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
    }
    fun updateStatusBarToDark(@ColorRes statusBarColor: Int = R.color.color_primary_dark) {
        val window: Window = window
        val decorView = window.decorView

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(statusBarColor)

        WindowInsetsControllerCompat(window, decorView).isAppearanceLightStatusBars = false
    }

    fun updateStatusBarToLight(@ColorRes statusBarColor: Int = R.color.white) {
        val window: Window = window
        val decorView = window.decorView

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(statusBarColor)

        WindowInsetsControllerCompat(window, decorView).isAppearanceLightStatusBars = true
    }

}