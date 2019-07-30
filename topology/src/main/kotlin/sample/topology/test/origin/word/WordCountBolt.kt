package sample.topology.test.origin.word

import sample.core.util.logger
import org.apache.storm.Constants
import org.apache.storm.topology.BasicOutputCollector
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseBasicBolt
import org.apache.storm.tuple.Tuple
import org.apache.storm.tuple.Fields
import org.apache.storm.tuple.Values


open class WordCountBolt(emitFrequency: Int = 5) : BaseBasicBolt() {
    private val logger = logger()
    private val counts = hashMapOf<String, Int>()

    override fun execute(tuple: Tuple, collector: BasicOutputCollector) {
        //If it's a tick tuple, emit all words and counts
        if (tuple.sourceComponent == Constants.SYSTEM_COMPONENT_ID &&
            tuple.sourceStreamId == Constants.SYSTEM_TICK_STREAM_ID
        ) {
            for (word in counts.keys) {
                val count = counts[word]
                collector.emit(Values(word, count))
                logger.info("Emitting a count of $count for word $word")
            }
        } else {
            //Get the word contents from the tuple
            val word = tuple.getString(0)
            //Have we counted any already?
            var count = counts[word]
            if (count == null)
                count = 0
            //Increment the count and store it
            count++
            counts[word] = count
        }
    }

    override fun declareOutputFields(declarer: OutputFieldsDeclarer) {
        declarer.declare(Fields("word", "count"))
    }

}
