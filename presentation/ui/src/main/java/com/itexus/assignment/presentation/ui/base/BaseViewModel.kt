package com.itexus.assignment.presentation.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

private const val DEFAULT_ERROR_MESSAGE = "Something went wrong"

open class BaseViewModel : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableSharedFlow<Error>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val error = _error.asSharedFlow()

    suspend fun wrapLoading(
        loading: MutableSharedFlow<Boolean> = _loading,
        action: suspend () -> Unit,
    ) {
        handleError {
            loading.tryEmit(true)
            action()
            loading.tryEmit(false)
        }
    }

    private suspend fun handleError(
        errorHandlingBlock: ((throwable: Throwable) -> Unit) = {
            _error.tryEmit(Error(it.message ?: DEFAULT_ERROR_MESSAGE))
        },
        action: suspend () -> Unit,
    ) {
        try {
            action()
        } catch (throwable: Throwable) {
            errorHandlingBlock(throwable)
        }
    }

    data class Error(
        val message: String = DEFAULT_ERROR_MESSAGE,
    )
}
