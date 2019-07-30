/**
 * @author archmagece
 * @since 2015-06-18-19
 * @with buskingplay-parent
 */


import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.jmx.JMXConfigurator
import ch.qos.logback.classic.jmx.MBeanUtil

import java.lang.management.ManagementFactory
import java.nio.charset.Charset

//statusListener(OnConsoleStatusListener)

def MODULE_NAME = "web-admin"
def PHASE = System.getProperty("spring.profiles.active")
def USER_HOME = System.getProperty("user.home")
def appenderList= ["CONSOLE"]

def jmxConfigurator() {
	def contextName = context.name
	def objectNameAsString = MBeanUtil.getObjectNameFor(contextName, JMXConfigurator.class)
	def objectName = MBeanUtil.string2ObjectName(context, this, objectNameAsString)
	def platformMBeanServer = ManagementFactory.getPlatformMBeanServer()
	if (!MBeanUtil.isRegistered(platformMBeanServer, objectName)) {
		JMXConfigurator jmxConfigurator = new JMXConfigurator((LoggerContext) context, platformMBeanServer, objectName)
		try {
			platformMBeanServer.registerMBean(jmxConfigurator, objectName)
		} catch (all) {
			addError("Failed to create mbean", all)
		}
	}
}
jmxConfigurator()

if(appenderList.contains("CONSOLE")) {
	appender("CONSOLE", ConsoleAppender) {
		encoder(PatternLayoutEncoder) {
			pattern = "%d{HH:mm:ss.SSS} %-5level [%thread] %logger{36}.%method : %msg%n"
			immediateFlush = true
			charset = Charset.forName("UTF-8")
		}
	}
}

logger("org.hibernate", WARN)
logger("org.hibernate.SQL", DEBUG)
logger("org.hibernate.type", DEBUG)

logger("org.springframework", DEBUG)
logger("org.springframework.amqp.rabbit", WARN)

logger("org.apache.storm", WARN)

root(WARN, appenderList)
