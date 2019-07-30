package sample.topology.common.redis

//import org.apache.storm.task.IMetricsContext
//import org.apache.storm.trident.state.Serializer
//import org.apache.storm.trident.state.State
//import org.apache.storm.trident.state.StateFactory
//import org.apache.storm.trident.state.StateType
//import java.net.InetSocketAddress
//
//class RedisFactory(
//    val type: StateType,
//    val serer:InetSocketAddress,
//    val serializer: Serializer<Any>,
//    val factory: KeyFactory,
//    val options: RedisState.Options<Any>
//): StateFactory {
//    init {
//        if(options.serializer == null){
//            this.serializer = sample.topology.common.redis.RedisState.
//        }
//    }
//    override fun makeState(
//        conf: MutableMap<String, Any>,
//        metrics: IMetricsContext,
//        partitionIndex: Int,
//        numPartitions: Int
//    ): State {
//
//    }
//
//}