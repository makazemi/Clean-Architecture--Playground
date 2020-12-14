package com.example.testbasalam.base

import androidx.appcompat.app.AppCompatActivity
import com.example.testbasalam.framework.presentation.displayToast
import com.example.testbasalam.business.domain.state.ErrorBody
import com.example.testbasalam.business.domain.state.Event
import com.example.testbasalam.business.domain.state.Loading
import com.example.testbasalam.business.domain.state.TypeError
import timber.log.Timber

abstract class BaseActivity:AppCompatActivity(){

    private fun handleError(msg: String?, typeError: TypeError, ignoreError: Boolean) {
        msg?.let {
            if (!ignoreError) {
                when (typeError) {
                    TypeError.TOAST -> displayToast(it)
                    else -> displayToast(it)
                }
            }
        }
    }

    fun onDataStateChange(
        loading: Loading,
        error: Event<ErrorBody>?,
        isDialog: Boolean = true,
        isShowProgress: Boolean = true,
        typeError: TypeError = TypeError.TOAST,
        ignoreError: Boolean = false
    ) {
        if (isShowProgress) {
            when {
                isDialog -> displayDialogProgressBar(loading.isLoading)
                else -> displayProgressBar(loading.isLoading)
            }
        }

        error?.let {
            it.getContentIfNotHandled()?.let {
                handleError(it.message, typeError, ignoreError)
                Timber.d( "error message:${it.message}")
            }
        }

    }

    private fun displayDialogProgressBar(inProgress: Boolean) {}
    abstract fun displayProgressBar(inProgress: Boolean)
}