package sample.topology.test.origin.test1

import com.google.common.collect.Lists
import org.apache.storm.Config
import org.apache.storm.StormSubmitter
import org.apache.storm.topology.TopologyBuilder

open class Test1Topology

fun main() {
    val builder = TopologyBuilder().apply {
        setSpout("random-digit-spout", RandomDigitSpout())
        setBolt("even-digit-bolt", EvenDigitBolt(), 2)
            .shuffleGrouping("random-digit-spout")
        setBolt("multiplied-by-ten-bolt", MultiplyByTenBolt(), 4)
            .shuffleGrouping("even-digit-bolt")
    }
    val conf = Config().apply {
//        set(Config.NIMBUS_SEEDS, Lists.newArrayList("172.24.0.3"))
        set(Config.NIMBUS_SEEDS, Lists.newArrayList("127.0.0.1"))
        setNumWorkers(2)
    }
    StormSubmitter.submitTopology("out-simple-topology", conf, builder.createTopology())
}