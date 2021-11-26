package com.example.postrequsingjsonserver

data class ResponseModel(
    val comments: List<Comment>,
    val posts: List<Post>,
    val profile: Profile
)