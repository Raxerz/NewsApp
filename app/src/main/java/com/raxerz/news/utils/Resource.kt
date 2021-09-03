package com.raxerz.news.utils

data class Resource<out T>(val status: UIState, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(UIState.SUCCESS, data, null)
        }

        fun <T> error(data: T?, msg: String): Resource<T> {
            return Resource(UIState.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(UIState.LOADING, data, null)
        }
    }
}
