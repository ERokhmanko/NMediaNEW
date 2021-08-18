package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.utils.Utils

interface PostCallback{
    fun onLike(post: Post)
    fun onShare(post: Post)
}

//typealias OnLikeListener = (Post) -> Unit

class PostsAdapter(private val postCallback: PostCallback) :
    ListAdapter <Post, PostViewHolder>(PostsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, postCallback)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

}

class PostViewHolder(private val binding: CardPostBinding,
                    private val postCallback: PostCallback) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        with(binding) {
            author.text = post.author
            content.text = post.content
            published.text = post.published
            likeCount.text = Utils().reductionInNumbers(post.likesCount)
            shareCount.text = Utils().reductionInNumbers(post.sharesCount)
            like.setImageResource(
                if (post.likedByMe) R.drawable.ic_baseline_favorite_24
                else R.drawable.ic_baseline_favorite_border_24
            )

            like.setOnClickListener {
                postCallback.onLike(post)
            }

            share.setOnClickListener {
                postCallback.onShare(post)
            }
        }
    }
}

class PostsDiffCallback : DiffUtil.ItemCallback<Post>(){

    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}