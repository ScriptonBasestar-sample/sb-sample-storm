package sample.topology.test.trident.testnumber

import sample.core.util.logger
import org.apache.storm.trident.operation.TridentCollector
import org.apache.storm.trident.spout.ITridentSpout
import org.apache.storm.trident.topology.TransactionAttempt
import org.apache.storm.tuple.Values
import java.util.concurrent.atomic.AtomicInteger

class TestNumberEmitter : ITridentSpout.Emitter<String> {
    private val logger = logger()

    private val successfulTransactions = AtomicInteger(0)

    override fun emitBatch(tx: TransactionAttempt, coordinatorMeta: String, collector: TridentCollector) {
        logger.trace(
            "TestNumberCoordinator.emitBatch() called on Thread {}, param tx: {}",
            Thread.currentThread().id,
            tx
        )
        for (i in 0..10000) {
            collector.emit(Values(i))
        }
    }

    override fun close() {
        logger.trace("TestNumberCoordinator.close() called on Thread {}", Thread.currentThread().id)
    }

    override fun success(tx: TransactionAttempt) {
        logger.trace("TestNumberCoordinator.success() called on Thread {}, param tx: {}", Thread.currentThread().id, tx)
        successfulTransactions.incrementAndGet()
    }
}