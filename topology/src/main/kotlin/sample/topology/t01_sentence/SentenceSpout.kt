package sample.topology.t01_sentence

import org.apache.storm.spout.SpoutOutputCollector
import org.apache.storm.task.TopologyContext
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichSpout
import org.apache.storm.tuple.Fields
import org.apache.storm.tuple.Values

class SentenceSpout:BaseRichSpout() {
    //생성자를 쓰거나 nextTuple에서 파라미터를 주지 않고 이렇게 쓰는 이유
    private lateinit var collector: SpoutOutputCollector
    private val sentences = arrayOf(
        "throw error instead of setting status and header",
        "throw the returned value in place of the default NotImplemented error",
        "throw the returned value in place of the default MethodNotAllowed error"
    )
    override fun nextTuple() {
        sentences.forEach { this.collector.emit(Values(it)) }
    }

    override fun open(conf: MutableMap<String, Any>, context: TopologyContext, collector: SpoutOutputCollector) {
        this.collector = collector
    }

    override fun declareOutputFields(declarer: OutputFieldsDeclarer) {
        declarer.declare(Fields("sentence"))
    }
}
