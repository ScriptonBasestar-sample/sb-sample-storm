package sample.topology.test.origin.test2kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.storm.Config
import org.apache.storm.LocalCluster
import org.apache.storm.kafka.spout.*
import org.apache.storm.topology.TopologyBuilder
import org.apache.storm.tuple.Fields
import org.apache.storm.tuple.Values
import sample.core.util.logger
import java.util.stream.Stream

val marketSymbol = "xbtusd"
val TOPIC_Test_In = "test-$marketSymbol"

val Stream_Test = "test-stream-$marketSymbol"

val logger = logger()
fun main() {
    val kafkaHost = "localhost:9092"
    run {
        val kafkaSpoutConfig = KafkaSpoutConfig.builder(kafkaHost, TOPIC_Test_In)
            .setProp(ConsumerConfig.GROUP_ID_CONFIG, "matching_filled_group")
//            .setRetry(getRetryService())
            .setRecordTranslator(
                ByTopicRecordTranslator<String, String>(
                    { r -> Values(r.topic(), r.partition(), r.offset(), r.key(), r.value()) },
                    Fields("topic", "partition", "offset", "key", "value"),
                    Stream_Test
                )
            )
            .setProcessingGuarantee(KafkaSpoutConfig.ProcessingGuarantee.AT_MOST_ONCE)
            .setOffsetCommitPeriodMs(10_000)
            .setFirstPollOffsetStrategy(FirstPollOffsetStrategy.UNCOMMITTED_EARLIEST)
            .setMaxUncommittedOffsets(250)
            .build()
        val topologyBuilder = TopologyBuilder()
            .apply {
                setSpout("kafka_spout", KafkaSpout(kafkaSpoutConfig))
                val bolt1 = TestBolt1()
                setBolt("kafka_bolt1", bolt1)
                    .shuffleGrouping("kafka_spout", Stream_Test)
                setBolt("bolt2", TestBolt2())
//                    .shuffleGrouping("kafka_bolt1", MatchingFilled_Stream)
                    .shuffleGrouping("kafka_bolt1")
                setBolt("bolt3", TestBolt3())
                    .shuffleGrouping("bolt2", "stream-test3")
                setBolt("bolt4", TestBolt4())
                    .shuffleGrouping("bolt2", "stream-test4")
            }

        val conf = getConfig()
        val cluster = LocalCluster()
        logger.info("Submitting topology.")
        cluster.submitTopology("test2kafka", conf, topologyBuilder.createTopology())
        logger.info("Topology submitted.")
        Thread.sleep(600000)
    }
}

fun getConfig(): Config = Config().apply {
    setDebug(true)
}

fun getRetryService(): KafkaSpoutRetryService {
    return KafkaSpoutRetryExponentialBackoff(
        KafkaSpoutRetryExponentialBackoff.TimeInterval.microSeconds(500),
        KafkaSpoutRetryExponentialBackoff.TimeInterval.milliSeconds(2),
        Integer.MAX_VALUE,
        KafkaSpoutRetryExponentialBackoff.TimeInterval.seconds(10)
    )
}

