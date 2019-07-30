package sample.topology.test.trident.testnumber

import sample.core.util.logger
import org.apache.storm.trident.spout.ITridentSpout

class TestNumberCoordinator: ITridentSpout.BatchCoordinator<String> {
    private val logger = logger()
    override fun isReady(txid: Long): Boolean {
        logger.trace("TestNumberCoordinator.isReady() called on Thread {}, param txid: {} return {}", Thread.currentThread().id, txid, true)
        return true
    }

    override fun initializeTransaction(txid: Long, prevMetadata: String, currMetadata: String): String {
        logger.trace("TestNumberCoordinator.initializeTransaction() called on Thread {}, param txid: {} prevMetadata: {} currMetadata: {} return {}", Thread.currentThread().id, txid, prevMetadata, currMetadata, "")
        return ""
    }

    override fun close() {
        logger.trace("TestNumberCoordinator.close() called on Thread {}", Thread.currentThread().id)
    }

    override fun success(txid: Long) {
        logger.trace("TestNumberCoordinator.success() called on Thread {}, param txid: {} return {}", Thread.currentThread().id, txid)
    }

}