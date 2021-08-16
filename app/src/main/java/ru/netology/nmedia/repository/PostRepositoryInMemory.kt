package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.utils.Utils

class PostRepositoryInMemory: PostRepository {
    private var post = Post(
        id = 1,
        author = "Нетология. Университет интернет-профессий будущег",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb\"",
        published = "21 мая в 18:36",
        likesCount = 999,
        sharesCount = 996
    )

    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data

    override fun Like() {
        val counter = if (post.likedByMe) post.likesCount - 1 else post.likesCount + 1

        post = post.copy(likedByMe = !post.likedByMe, likesCount = counter)
        data.value = post

    }

    override fun share() {
        val counter = post.sharesCount + 1

        post = post.copy(share = true, sharesCount = counter)
        data.value = post
    }
}