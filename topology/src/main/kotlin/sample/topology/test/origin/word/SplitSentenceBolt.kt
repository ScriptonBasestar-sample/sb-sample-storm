package sample.topology.test.origin.word

import org.apache.storm.topology.BasicOutputCollector
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseBasicBolt
import org.apache.storm.tuple.Fields
import org.apache.storm.tuple.Tuple
import org.apache.storm.tuple.Values
import java.text.BreakIterator


open class SplitSentenceBolt: BaseBasicBolt() {
    override fun execute(tuple: Tuple, collector: BasicOutputCollector) {
        //Get the sentence content from the tuple
        val sentence = tuple.getString(0)
        //An iterator to get each word
        val boundary = BreakIterator.getWordInstance()
        //Give the iterator the sentence
        boundary.setText(sentence)
        //Find the beginning first word
        var start = boundary.first()
        //Iterate over each word and emit it to the output stream
        var end = boundary.next()
        while (end != BreakIterator.DONE) {
            //get the word
            var word = sentence.substring(start, end)
            //If a word is whitespace characters, replace it with empty
            word = word.replace("\\s+".toRegex(), "")
            //if it's an actual word, emit it
            if (word != "") {
                collector.emit(Values(word))
            }
            start = end
            end = boundary.next()
        }
    }

    override fun declareOutputFields(declarer: OutputFieldsDeclarer) {
        declarer.declare(Fields("word"))
    }

}