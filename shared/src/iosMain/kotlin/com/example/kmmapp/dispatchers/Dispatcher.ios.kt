package com.example.kmmapp.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import platform.darwin.dispatch_queue_t
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.freeze

internal actual val ioDispatcher: CoroutineDispatcher = NsQueueDispatcher(dispatch_get_main_queue())
internal actual val mainDispatcher: CoroutineDispatcher =
    NsQueueDispatcher(dispatch_get_main_queue())
internal actual val computationDispatcher: CoroutineDispatcher =
    NsQueueDispatcher(dispatch_get_main_queue())

internal class NsQueueDispatcher(private val dispatchQueueT: dispatch_queue_t) :
    CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatchQueueT.freeze()) {
            block.run()
        }
    }
}
