package sample.topology.test.origin.test1

import org.apache.storm.task.OutputCollector
import org.apache.storm.task.TopologyContext
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichBolt
import org.apache.storm.tuple.Fields
import org.apache.storm.tuple.Tuple
import org.apache.storm.tuple.Values

open class EvenDigitBolt: BaseRichBolt() {
    private lateinit var collector: OutputCollector
    override fun prepare(topoConf: MutableMap<String, Any>, context: TopologyContext, collector: OutputCollector) {
    }

    override fun execute(input: Tuple) {
        val randomDigit = input.getInteger(0)
        if (randomDigit % 2 == 0){
            collector.emit(Values(randomDigit))
        }
    }

    override fun declareOutputFields(declarer: OutputFieldsDeclarer) {
        declarer.declare(Fields("even-digit"))
    }
}
