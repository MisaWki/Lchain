package com.rmolives.blockchain.util

import java.lang.StringBuilder
import java.security.MessageDigest

fun sha256(strText: String) = sha(strText, "SHA-256")
fun sha512(strText: String) = sha(strText, "SHA-512")

fun sha(strText: String, strType: String): String {
    if (strText.isNotEmpty()) {
        val messageDigest = MessageDigest.getInstance(strType)
        messageDigest.update(strText.toByteArray())
        val byteBuffer = messageDigest.digest()

        val strHexString = StringBuilder()
        for (i in byteBuffer.indices) {
            val hex = Integer.toHexString(0xff and byteBuffer[i].toInt())
            if (hex.length == 1)
                strHexString.append('0')
            strHexString.append(hex)
        }
        return strHexString.toString()
    } else
        throw KotlinNullPointerException("Sha256 strText is Empty!")
}