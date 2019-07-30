package sample.topology.test.origin.word

import org.apache.storm.spout.SpoutOutputCollector
import org.apache.storm.task.TopologyContext
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichSpout
import org.apache.storm.tuple.Fields
import org.apache.storm.tuple.Values
import org.apache.storm.utils.Utils
import kotlin.random.Random

open class RandomSentenceSpout : BaseRichSpout() {
    private lateinit var _collector: SpoutOutputCollector
    private lateinit var _rand: Random
    override fun nextTuple() {
        Utils.sleep(100)
        val sentences = arrayOf(
            "the cow jumped over the moon",
            "an apple a day keeps the doctor away",
            "four score and seven years ago",
            "snow white and the seven dwarfs",
            "i am at two with nature"
        )
        val sentence = sentences[_rand.nextInt(sentences.size)]
        _collector.emit(Values(sentence))
    }

    override fun open(conf: MutableMap<String, Any>, context: TopologyContext, collector: SpoutOutputCollector) {
        this._collector = collector
        this._rand = Random(System.currentTimeMillis())
    }

    override fun declareOutputFields(declarer: OutputFieldsDeclarer) {
        declarer.declare(Fields("sentence"))
    }
}
