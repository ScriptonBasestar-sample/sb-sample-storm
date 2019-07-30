package sample.topology.test.trident.testnumber

import sample.core.util.logger
import org.apache.storm.trident.operation.BaseFunction
import org.apache.storm.trident.operation.TridentCollector
import org.apache.storm.trident.tuple.TridentTuple
import org.apache.storm.tuple.Values

class TestNumberMultiplyFunction : BaseFunction() {
    private val logger = logger()
    override fun execute(tuple: TridentTuple, collector: TridentCollector) {
        logger.trace( "TestNumberMultiplyFunction.execute() called on Thread {}, param tuple: {} collector: {}", Thread.currentThread().id, tuple, collector)
        val testNumber = tuple.getLong(0)

        collector.emit(Values(testNumber * 2))
    }
}