package sample.core.exception

/**
 * @author archmagece
 * @since 2018-03-16
 */
class SBAbortException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)