package com.example.kmmapp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MobileItem(
    @SerialName("id") val id: String,
    @SerialName("name")val name: String,
//    @SerialName("data") val data: Meta?
    @SerialName("data") val data: Map<String, String>?
)

//@Serializable
//data class Meta(
//    @SerialName("color") val color: String?,
//    @SerialName("capacity") val capacity: String?
//)
