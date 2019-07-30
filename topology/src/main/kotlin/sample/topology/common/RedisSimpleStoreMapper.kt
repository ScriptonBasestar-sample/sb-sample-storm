package sample.topology.common

import org.apache.storm.redis.common.mapper.RedisDataTypeDescription
import org.apache.storm.redis.common.mapper.RedisStoreMapper
import org.apache.storm.tuple.ITuple

class RedisSimpleStoreMapper(
    private val description: RedisDataTypeDescription,
    private val keyName: String,
    private val valueName: String
) : RedisStoreMapper {
    override fun getDataTypeDescription(): RedisDataTypeDescription {
        return description
    }

    override fun getKeyFromTuple(tuple: ITuple): String {
        return tuple.getStringByField(valueName)
    }

    override fun getValueFromTuple(tuple: ITuple): String {
        return tuple.getStringByField(keyName)
    }

}