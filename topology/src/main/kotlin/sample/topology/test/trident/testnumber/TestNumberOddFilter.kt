package sample.topology.test.trident.testnumber

import sample.core.util.logger
import org.apache.storm.trident.operation.BaseFilter
import org.apache.storm.trident.tuple.TridentTuple

class TestNumberOddFilter: BaseFilter() {
    private val logger = logger()

    override fun isKeep(tuple: TridentTuple): Boolean {
        logger.trace("TestNumberOddFilter.isKeep() called on Thread {}, param: tuple {}", Thread.currentThread().id, tuple)
        val testNumber = tuple.getLong(0)
        return testNumber % 2L == 0L
    }
}