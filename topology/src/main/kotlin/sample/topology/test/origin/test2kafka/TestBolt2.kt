package sample.topology.test.origin.test2kafka

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import sample.core.util.logger
import org.apache.storm.task.OutputCollector
import org.apache.storm.task.TopologyContext
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichBolt
import org.apache.storm.tuple.Fields
import org.apache.storm.tuple.Tuple
import org.apache.storm.tuple.Values

open class TestBolt2: BaseRichBolt() {
    private val logger = logger()
    private lateinit var collector: OutputCollector
    private lateinit var mapper: ObjectMapper
    override fun prepare(topoConf: MutableMap<String, Any>, context: TopologyContext, collector: OutputCollector) {
        this.collector = collector
        this.mapper = ObjectMapper().registerModule(KotlinModule())
    }

    override fun execute(input: Tuple) {
        logger.info("--------------------test2")
        logger.info(input.toString())
//        collector.emit("stream-test", input, Values("testv1", "testv2"))
//        collector.emit("stream-test", Fields("testk1", "testk2"), Values("testv1", "testv2"))
//        collector.emit("stream-test3", input, Values("testv1", "testv2"))
//        collector.emit("stream-test4", input, Values("testv1", "testv2"))
        collector.emit("stream-test3", input, Values("testv3-1", "testv3-2"))
        collector.emit("stream-test4", input, Values("testv4-1", "testv4-2"))
//        collector.emit(input, Values())
        collector.ack(input)
    }

    override fun declareOutputFields(declarer: OutputFieldsDeclarer) {
        declarer.declareStream("stream-test3", Fields("testk3-1", "testk3-2"))
        declarer.declareStream("stream-test4", Fields("testk4-1", "testk4-2"))
        declarer.declare(Fields("defaultStreamField"))
    }

}