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


class TestBolt1 : BaseRichBolt() {
    private val logger = logger()
    private lateinit var collector: OutputCollector
    private lateinit var mapper: ObjectMapper
    override fun prepare(topoConf: MutableMap<String, Any>, context: TopologyContext, collector: OutputCollector) {
        this.collector = collector
        this.mapper = ObjectMapper().registerModule(KotlinModule())
    }

    override fun execute(input: Tuple) {
        logger.info("==================================================test1")
        logger.info(input.toString())
        val topic = input.getString(0)
        val partition = input.getInteger(1)
        val offset = input.getLong(2)
        val key = input.getString(3)
        val model = input.getValue(4)
        logger.info(topic)
        logger.info(partition.toString())
        logger.info(offset.toString())
        logger.info(key)
        logger.info(model.toString())
//        mapper
        this.collector.emit(input, Values("testbolt1-val1"))
    }

    override fun declareOutputFields(declarer: OutputFieldsDeclarer) {
        declarer.declare(Fields("testbolt1-key1"))
//        declarer.declare(Fields("topic", "partition", "offset", "key", "value", "testbolt1-key1"))
    }

}
