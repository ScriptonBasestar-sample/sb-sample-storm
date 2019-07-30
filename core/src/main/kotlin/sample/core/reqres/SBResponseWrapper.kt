package sample.core.reqres

import java.io.Serializable
import java.util.*

/**
 * @author archmagece
 * @since 2019-02-17
 */
data class SBResponseWrapper<in T : Serializable>(
		val success: Boolean = true,
		val lang: String = "ko",
		val responseAt: Date = Date(),
		val message: String = "",
		var validationErrorSet: Set<Map<String, String>> = HashSet()
) {
	private val data = ArrayList<T>()

	fun add(t: T?): SBResponseWrapper<T> {
		if (t == null) {
			return this
		}
		data.add(t)
		return this
	}

	fun add(vararg ts: T): SBResponseWrapper<T> {
		if (ts.isEmpty()) {
			return this
		}
		for (t in ts) {
			add(t)
		}
		return this
	}

	fun add(list: List<T>?): SBResponseWrapper<T> {
		if (list == null) {
			return this
		}
		data.addAll(list)
		return this
	}

	fun getListCount(): Int {
		if (data.isEmpty()) {
			return 0
		}
		return data.size
	}

	fun hasData(): Boolean {
		return !data.isEmpty()
	}
}
