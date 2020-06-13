package com.rmolives.blockchain

import com.rmolives.blockchain.util.AddressUtils
import com.rmolives.blockchain.util.Base64Utils
import com.rmolives.blockchain.util.ECDSAUtils

/**
 * Registration class.
 * @author RMOLive (rmolives@gmail.com)
 * @param name name
 * @param contact contact method.
 */
class Register(name: String, contact: String) {
    val member: Member
    private val publicKey: String
    private val privateKey: String

    init {
        val key = ECDSAUtils.initKey()
        publicKey = Base64Utils.encode(key.public.encoded)
        privateKey = Base64Utils.encode(key.private.encoded)
        val address = AddressUtils.getAddress(publicKey)
        member = Member(User(name, contact, address), publicKey, privateKey)
    }
}