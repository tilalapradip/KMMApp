package com.example.kmmapp.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual val ioDispatcher: CoroutineDispatcher = Dispatchers.Main
internal actual val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
internal actual val computationDispatcher: CoroutineDispatcher = Dispatchers.Default
