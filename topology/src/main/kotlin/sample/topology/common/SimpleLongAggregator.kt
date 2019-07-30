package sample.topology.common

import org.apache.storm.trident.operation.CombinerAggregator
import org.apache.storm.trident.tuple.TridentTuple

class SimpleLongAggregator: CombinerAggregator<Long> {
    override fun init(tuple: TridentTuple): Long {
        return 1
    }

    override fun zero(): Long {
        return 1
    }

    override fun combine(val1: Long, val2: Long): Long {
        return 1
    }
}