package com.example.instalite.models

import com.google.firebase.firestore.PropertyName

data class Post (
    var description: String = "",
    @get:PropertyName("image_url") @set:PropertyName("image_url") var imageUrl: String = "",
    @get:PropertyName("current_time_ms") @set:PropertyName("current_time_ms") var currentTimeMs: Long = 0,
    var user: User? = null
)