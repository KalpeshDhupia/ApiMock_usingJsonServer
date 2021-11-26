package com.example.postrequsingjsonserver

data class Comment(
    val body: String,
    val id: Int,
    val postId: Int
)