package sample.core.reqres

import java.util.*
import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * @author archmagece
 * @since 2017-07-19
 */
class SBRequestWrapper<DATA> {
	@NotNull
	var lang: String? = null
	@NotNull
	var requestedAt: Date? = null

	@Valid
	private val data: List<DATA>? = null
}