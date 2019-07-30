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

open class TestBolt3: BaseRichBolt() {
    private val logger = logger()
    private lateinit var collector: OutputCollector
    private lateinit var mapper: ObjectMapper
    override fun prepare(topoConf: MutableMap<String, Any>, context: TopologyContext, collector: OutputCollector) {
        this.collector = collector
        this.mapper = ObjectMapper().registerModule(KotlinModule())
    }

    override fun execute(input: Tuple) {
        logger.info("--------------------test3")
        logger.info(input.toString())
//        collector.emit("stream-test", input, Values("testv1", "testv2"))
    }

    override fun declareOutputFields(declarer: OutputFieldsDeclarer) {
        declarer.declare(Fields("testk1", "testk2"))
    }

}
