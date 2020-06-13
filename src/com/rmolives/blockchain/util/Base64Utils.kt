package com.rmolives.blockchain.util

import java.io.*
import java.util.*

/**
 * @author RMOLive (rmolives@gmail.com)
 */
object Base64Utils {
    private const val CACHE_SIZE = 1024

    fun decode(base64: String): ByteArray {
        return Base64.getDecoder().decode(base64.toByteArray())
    }

    fun encode(bytes: ByteArray): String {
        return String(Base64.getEncoder().encode(bytes))
    }

    fun encode(str: String): String {
        val bytes = str.toByteArray(charset("utf-8"))
        return encode(bytes)
    }

    fun encodeFile(filePath: String): String {
        val bytes = fileToByte(filePath)
        return encode(bytes)
    }

    fun decodeToFile(filePath: String, base64: String) {
        val bytes = decode(base64)
        byteArrayToFile(bytes, filePath)
    }

    private fun fileToByte(filePath: String): ByteArray {
        var data = ByteArray(0)
        val file = File(filePath)
        if (file.exists()) {
            val `in` = FileInputStream(file)
            val out = ByteArrayOutputStream(2048)
            val cache = ByteArray(CACHE_SIZE)
            var nRead: Int
            while (`in`.read(cache).also { nRead = it } != -1) {
                out.write(cache, 0, nRead)
                out.flush()
            }
            out.close()
            `in`.close()
            data = out.toByteArray()
        }
        return data
    }

    private fun byteArrayToFile(bytes: ByteArray, filePath: String) {
        val `in`: InputStream = ByteArrayInputStream(bytes)
        val destFile = File(filePath)
        if (!destFile.parentFile.exists()) {
            destFile.parentFile.mkdirs()
        }
        destFile.createNewFile()
        val out: OutputStream = FileOutputStream(destFile)
        val cache = ByteArray(CACHE_SIZE)
        var nRead = 0
        while (`in`.read(cache).also { nRead = it } != -1) {
            out.write(cache, 0, nRead)
            out.flush()
        }
        out.close()
        `in`.close()
    }
}