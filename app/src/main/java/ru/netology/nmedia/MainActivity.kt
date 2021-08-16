package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_main.*
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.utils.Utils
import ru.netology.nmedia.viewmodel.PostViewModel
import androidx.activity.viewModels


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this){
            post ->
            with(binding) {
                author.text = post.author
                content.text = post.content
                published.text = post.published
                likeCount.text = Utils().reductionInNumbers(post.likesCount)
                shareCount.text = Utils().reductionInNumbers(post.sharesCount)
                like.setImageResource(if (post.likedByMe) R.drawable.ic_baseline_favorite_24
                else R.drawable.ic_baseline_favorite_border_24)
            }
        }

       like.setOnClickListener {
          viewModel.like()
        }

        share.setOnClickListener {
            viewModel.share()
        }

    }
}


