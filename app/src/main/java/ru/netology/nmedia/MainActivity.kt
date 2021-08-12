package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_main.*
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.utils.Utils


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущег",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb\"",
            published = "21 мая в 18:36",
            likesCount = 999,
            sharesCount = 999
        )

//        findViewById<ImageButton>(R.id.like).setOnClickListener {
//            (it as? ImageButton)?.setImageResource(R.drawable.ic_baseline_favorite_24)
//        }
        //        использование кликлистнера без синтетика/КАЕ


//        like.setOnClickListener {
//            like.setImageResource(R.drawable.ic_baseline_favorite_24)
//        }
        // работа с синтетиком. Минусы: Если лайк будет null, то приложение упадет. Поэтому сделаем через VIEW BINDING ниже

        with(binding) {
            author.text = post.author
            content.text = post.content
            published.text = post.published
            likeCount.text = post.likesCount.toString()
            shareCount.text = post.sharesCount.toString()

            if (post.likedByMe) {
                like?.setImageResource(R.drawable.ic_baseline_favorite_24)
            }

            like?.setOnClickListener {
                post.likedByMe = !post.likedByMe
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_baseline_favorite_24
                    else R.drawable.ic_baseline_favorite_border_24
                )

                val counter = post.likesCount + 1
                if (post.likedByMe) likeCount.text = Utils().reductionInNumbers(counter) else likeCount.text =
                    Utils().reductionInNumbers(post.likesCount)
            }

            share.setOnClickListener {
                val counter = ++post.sharesCount
                shareCount.text = Utils().reductionInNumbers(counter)
            }

        }

    }
}


