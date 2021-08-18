package ru.netology.nmedia.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.repository.PostRepositoryInMemory

class PostViewModel: ViewModel() {
    private val repository = PostRepositoryInMemory()
    val data = repository.get()
    fun like(id: Long) = repository.like(id)
    fun share(id: Long) = repository.share(id)
}