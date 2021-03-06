package com.example.letschat.data

import androidx.room.Entity
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class Message(
    val id: String,
    val messages: List<MessageX>
){
    @Serializable
    data class MessageX(
            val message: String,
            val sender: String,
            val timestamp: String
    )
}

