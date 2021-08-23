package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel
import androidx.activity.viewModels
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import ru.netology.nmedia.adapter.PostCallback
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.utils.Utils


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        val adapter = PostsAdapter(object : PostCallback {
            override fun onLike(post: Post) {
                viewModel.like(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.share(post.id)
            }

            override fun remove(post: Post) {
                viewModel.remove(post.id)
            }

            override fun edit(post: Post) {
                group.visibility = View.VISIBLE
                viewModel.edit(post)
                post_text.text = post.content
            }

        })

        binding.list.adapter = adapter
        binding.list.animation = null // отключаем анимацию

        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        viewModel.edited.observe(this) { post ->
            if (post.id == 0L) {
                return@observe
            }

            with(binding.content) {
                setText(post.content)
                requestFocus()
            }
        }

        binding.save.setOnClickListener {
            with(binding.content) {
                if (text.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        context.getString(R.string.error_empty_content),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                viewModel.changeContent(text.toString())
                viewModel.save()

                binding.group.visibility = View.GONE
                setText("")// очищает окно ввода
                clearFocus()//убирает курсор
                Utils.hideKeyboard(it)//скрывает клавиатуру
            }
        }

        binding.cancelEditing.setOnClickListener {
            viewModel.cancelEditing()
            binding.content.setText("")
            group.visibility = View.GONE
            Utils.hideKeyboard(it)
        }
    }
}



