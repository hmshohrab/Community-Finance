package com.jomibaba.jomibaba.core

import com.hmshohrab.communityfinance.core.Status


data class Resource<out T, out E>(val status: Status, val data: T?, val error: E?) {

    companion object {

        fun <T, E> success(data: T?): Resource<T, E> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T, E> error( data: T?,error: E): Resource<T, E> {
            return Resource(Status.ERROR, data, error)
        }

        fun <T, E> loading(data: T?): Resource<T, E> {
            return Resource(Status.LOADING, data, null)
        }
    }
}