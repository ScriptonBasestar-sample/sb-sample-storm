package sample.core.reqres

import java.util.*

/**
 * @author archmagece
 * @since 2017-07-19
 */
class ValidationErrorDto {

	val fieldErrors = ArrayList<FieldErrorDto>()

	fun addFieldError(path: String, message: String) {
		val error = FieldErrorDto(path, message)
		fieldErrors.add(error)
	}
}