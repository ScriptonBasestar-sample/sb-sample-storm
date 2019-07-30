package sample.core.reqres

import java.util.*

/**
 * @author archmagece
 * @since 2019-02-17
 */
data class SBEmptyResponseWrapper(
		val success: Boolean = true,
		val lang: String = "ko",
		val leadtime: Long = 0,
		val responseAt: Date = Date(),
		val message: String = "",
		var validationErrorSet: Set<Map<String, String>> = HashSet()
)
