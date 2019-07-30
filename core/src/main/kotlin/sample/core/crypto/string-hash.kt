package sample.core.crypto

import java.security.MessageDigest

internal enum class HashType(val value: String) {
	MD5("MD5"), SHA1("SHA-1"), SHA256("SHA-256");
}

private fun String.hashWithAlgorithm(algorithm: HashType): String {
	val digest = MessageDigest.getInstance(algorithm.value)
	val bytes = digest.digest(this.toByteArray(Charsets.UTF_8))
	return bytes.fold("") { str, it -> str + "%02x".format(it) }
}

fun String.md5(): String {
	return hashWithAlgorithm(HashType.MD5)
}

fun String.sha1(): String {
	return hashWithAlgorithm(HashType.SHA1)
}

fun String.sha256(): String {
	return hashWithAlgorithm(HashType.SHA256)
}
