package com.jdkgroup.view

import com.jdkgroup.model.User

interface RXJavaView {
    fun responseListUser(response: List<User>)

    fun doMerge(str: String)

    fun doDistinct(value: Int?)
}

