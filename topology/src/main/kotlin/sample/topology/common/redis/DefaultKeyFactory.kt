package sample.topology.common.redis

class DefaultKeyFactory: KeyFactory {
    override fun build(key: List<Any>): String {
        if(key.size != 1){
            throw RuntimeException("Default KeyFactory does not support compound keys")
        }
        return key[0].toString()
    }
}