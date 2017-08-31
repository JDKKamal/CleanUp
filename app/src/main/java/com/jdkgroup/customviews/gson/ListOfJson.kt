package com.jdkgroup.customviews.gson

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Created by kamlesh on 8/23/2017.
 */

class ListOfJson<T>(wrapper: Class<T>) : ParameterizedType {
    private val wrapped: Class<*>

    init {
        this.wrapped = wrapper
    }

    override fun getActualTypeArguments(): Array<Type> {
        return arrayOf(wrapped)
    }

    override fun getRawType(): Type {
        return List::class.java
    }

    override fun getOwnerType(): Type? {
        return null
    }
}