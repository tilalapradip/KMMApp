package com.example.kmmapp.services

import com.example.kmmapp.data.MobileItem

internal interface ApiService {

    suspend fun getMobiles(): List<MobileItem>

    suspend fun getMobilesFake(): List<MobileItem>

    companion object {
        fun create(): ApiService {
            return ApiServiceImpl()
        }
    }
}