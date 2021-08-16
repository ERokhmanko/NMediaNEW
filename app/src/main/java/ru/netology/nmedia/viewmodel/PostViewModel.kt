package ru.netology.nmedia.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.repository.PostRepositoryInMemory

class PostViewModel: ViewModel() {
    private val repository = PostRepositoryInMemory()
    val data = repository.get()
    fun like() = repository.Like()
    fun share() = repository.share()
}