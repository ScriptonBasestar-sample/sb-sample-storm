package sample.topology.test.trident.tutorial

import org.apache.storm.trident.TridentState
import org.apache.storm.trident.TridentTopology
import org.apache.storm.trident.operation.builtin.Count
import org.apache.storm.trident.testing.FixedBatchSpout
import org.apache.storm.trident.testing.MemoryMapState
import org.apache.storm.trident.testing.Split
import org.apache.storm.tuple.Fields
import org.apache.storm.tuple.Values


fun main(){
    val spout = FixedBatchSpout(Fields("sentence"), 3,
        Values("the cow jumped over the moon"),
        Values("the man went to the store and bought some candy"),
        Values("four score and seven years ago"),
        Values("how many apples can you eat"))
    spout.setCycle(true)

    val topology = TridentTopology()
    val wordCounts = topology.newStream("spout1", spout)
        .each(Fields("sentence"), Split(), Fields("count"))
        .groupBy(Fields("word"))
        .persistentAggregate(MemoryMapState.Factory(), Count(), Fields("count"))
        .parallelismHint(6)
}