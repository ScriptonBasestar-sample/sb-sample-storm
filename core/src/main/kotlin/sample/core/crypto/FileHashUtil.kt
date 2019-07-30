package sample.core.crypto

import java.io.InputStream
import java.security.MessageDigest


/**
 * @author archmagece
 * @since 2017-07-13
 */
object FileHashUtil {

	private fun digest(digestType: HashType, inputStream: InputStream): String {
		val digest = MessageDigest.getInstance(digestType.value)
		digest.update(inputStream.readBytes())
		inputStream.close()

		val bytes = digest.digest()

		val sb = StringBuilder()
//			for (i in 0..bytes.size-1) {
		for (i in 0 until bytes.size) {
			sb.append(Integer.toString((bytes[i].toInt() and 0xff) + 0x100, 16).substring(1))
		}
		return sb.toString()
	}

	@JvmStatic
	fun md5(inputStream: InputStream): String {
		val digest = MessageDigest.getInstance("MD5")
		digest.update(inputStream.readBytes())
		inputStream.close()

		val bytes = digest.digest()
		val sb = StringBuilder()
		for (i in 0 until bytes.size) {
			sb.append(Integer.toString((bytes[i].toInt() and 0xff) + 0x100, 16).substring(1))
		}
		return sb.toString()
	}

	@JvmStatic
	fun sha1Digest(inputStream: InputStream): String {
		return digest(HashType.SHA1, inputStream)
	}

	@JvmStatic
	fun sha256(inputStream: InputStream): String {
		return digest(HashType.SHA256, inputStream)
	}

}
