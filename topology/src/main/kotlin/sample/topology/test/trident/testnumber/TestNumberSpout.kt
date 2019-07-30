package sample.topology.test.trident.testnumber

import sample.core.util.logger
import org.apache.storm.task.TopologyContext
import org.apache.storm.trident.spout.ITridentSpout
import org.apache.storm.tuple.Fields

class TestNumberSpout: ITridentSpout<String> {
    private val logger = logger()
    private val coordinator = TestNumberCoordinator()
    private val emitter = TestNumberEmitter()

    override fun getCoordinator(
        txStateId: String,
        conf: MutableMap<String, Any>,
        context: TopologyContext
    ): ITridentSpout.BatchCoordinator<String> {
        logger.trace("TestNumberSpout.getCoordinator called - return {}", coordinator)
        return coordinator
    }

    override fun getOutputFields(): Fields {
        logger.trace("TestNumberSpout.getOutputFields called - return {}", Fields("value"))
        return Fields("value")
    }

    override fun getEmitter(
        txStateId: String,
        conf: MutableMap<String, Any>,
        context: TopologyContext
    ): ITridentSpout.Emitter<String> {
        logger.trace("TestNumberSpout.getEmitter called - return {}", emitter)
        return emitter
    }

    override fun getComponentConfiguration(): MutableMap<String, Any> {
        return mutableMapOf()
    }

}