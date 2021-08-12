package ru.netology.nmedia.dto;

data class Post (
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likesCount: Int = 0,
    var likedByMe: Boolean = false,
    var share: Boolean = false,
    var sharesCount: Int = 0
        )
