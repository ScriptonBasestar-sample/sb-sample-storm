package sample.core.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.invoke.MethodHandles

inline fun logger(): Logger {
	return LoggerFactory.getLogger(MethodHandles.lookup().lookupClass())
}
