package sample.topology.test.origin.test1

import org.apache.storm.task.OutputCollector
import org.apache.storm.task.TopologyContext
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichBolt
import org.apache.storm.tuple.Fields
import org.apache.storm.tuple.Tuple
import org.apache.storm.tuple.Values

open class MultiplyByTenBolt: BaseRichBolt(){
    private lateinit var collector: OutputCollector
    override fun prepare(topoConf: MutableMap<String, Any>, context: TopologyContext, collector: OutputCollector) {
        this.collector = collector
    }

    override fun execute(input: Tuple) {
        val evenDigit: Int = input.getInteger(0)
        collector.emit(Values(evenDigit * 10))
    }

    override fun declareOutputFields(declarer: OutputFieldsDeclarer) {
        declarer.declare(Fields("even-digit-multiplied-by-ten"))
    }
}