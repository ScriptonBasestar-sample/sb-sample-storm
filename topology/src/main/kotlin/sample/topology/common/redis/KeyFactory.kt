package sample.topology.common.redis

import java.io.Serializable
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

interface KeyFactory : Serializable {
    fun build(key: List<Any>): String
}