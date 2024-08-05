package com.example.kmmapp.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

internal expect val ioDispatcher: CoroutineDispatcher
internal expect val mainDispatcher: CoroutineDispatcher
internal expect val computationDispatcher: CoroutineDispatcher