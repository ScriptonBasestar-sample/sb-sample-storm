package sample.topology.common.redis

import org.apache.storm.trident.state.Serializer
import redis.clients.jedis.Protocol
import java.io.Serializable

class RedisOptions<T>(
    val localCacheSide: Int = 1000,
    val globalKey: String = "\$REDIS-MAP-STATE-GLOBAL",
    val serializer: Serializer<T>? = null,
    val keyFactory: KeyFactory? = null,
    val connectionTimeout: Int = Protocol.DEFAULT_TIMEOUT,
    val password: String? = null,
    val database: Int = Protocol.DEFAULT_DATABASE,
    val hkey: String? = null
) : Serializable
