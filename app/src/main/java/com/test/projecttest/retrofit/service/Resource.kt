package com.test.projecttest.retrofit.service

class Resource<T>(
    val data: T?,
    val error: String? = null
)