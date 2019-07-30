package sample.topology.test.trident.testnumber

import org.apache.storm.Config
import org.apache.storm.LocalCluster
import org.apache.storm.generated.StormTopology
import org.apache.storm.trident.TridentTopology
import org.apache.storm.tuple.Fields

private fun buildTopology(): StormTopology {
    val topology = TridentTopology()
    val spout = TestNumberSpout()
    val inputStream = topology.newStream("test-number-spout", spout)

    inputStream.each(Fields("test-number-spout"), TestNumberOddFilter())
        .each(Fields("test-number-spout"), TestNumberMultiplyFunction(), Fields("multiple"))
        .groupBy(Fields("multiple"))
//        .persistentAggregate()
//        .each(Fields(""))
    return topology.build()
}

fun main(){
   LocalCluster().submitTopology("cdc", Config(), buildTopology()).run {
      Thread.sleep(200_000)
   }
}