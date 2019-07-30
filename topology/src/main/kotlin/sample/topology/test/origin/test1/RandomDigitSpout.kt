package sample.topology.test.origin.test1

import org.apache.storm.spout.SpoutOutputCollector
import org.apache.storm.task.TopologyContext
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichSpout
import org.apache.storm.tuple.Fields
import org.apache.storm.tuple.Values
import java.util.concurrent.ThreadLocalRandom

open class RandomDigitSpout: BaseRichSpout() {
    private lateinit var collector: SpoutOutputCollector
    override fun nextTuple() {
        val randomDigit = ThreadLocalRandom.current().nextInt(0, 10)
        collector.emit(Values(randomDigit))
    }

    override fun open(conf: MutableMap<String, Any>, context: TopologyContext, collector: SpoutOutputCollector) {
        this.collector = collector
    }

    override fun declareOutputFields(declarer: OutputFieldsDeclarer) {
        declarer.declare(Fields("random-digit"))
    }

}