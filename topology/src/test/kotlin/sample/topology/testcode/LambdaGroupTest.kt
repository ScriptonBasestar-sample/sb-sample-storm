package sample.topology.testcode

import sample.core.sumByLong
import org.junit.jupiter.api.Test

class LambdaGroupTest {
    class TestObj(
        val a: Double,
        val b: String,
        val c: Long
    )

    @Test
    fun testlambda() {
        val list = arrayListOf(
            TestObj(0.5, "a1111", 30),
            TestObj(0.5, "a1111", 30),
            TestObj(1.0, "a1111", 30),
            TestObj(1.0, "a1111", 30),
            TestObj(2.0, "a1111", 30)
        )
        val result1 = list.groupBy { it.a }
        println(result1)
        val result2 = list.groupBy { it.a }.map { it.value.sumByLong { it2 -> it2.c } }
        println(result2)
        val result3 = list.groupBy { it.a }.map { it.value.sumByLong { it2 -> it2.c } }
//        val result3 = list.asSequence().groupBy { it.a }.map { it.value.sumByLong { it2 -> it2.c } }
        println(result3)
        val result4 = list.groupBy { it.a }.map { Pair(it.key, it.value.sumByLong { it2 -> it2.c }) }
        println(result4)
        val result5 = list.groupBy { it.a }.map { it.key to it.value.sumByLong { it2 -> it2.c } }.toMap()
        println(result5)
        println(result5.values.toList())
//        val result6 = list.groupBy { it.a }.map { it.value.associateBy {  }.key to it.value.sumByLong { it2 -> it2.c } }.toMap()
//        println(result6)
    }
}