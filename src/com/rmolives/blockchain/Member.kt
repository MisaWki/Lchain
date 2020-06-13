package com.rmolives.blockchain

/**
 * @author RMOLive (rmolives@gmail.com)
 * @param user Current member user.
 * @param publicKey Current member public key.
 * @param privateKey Current member private key
 */
class Member(val user: User, private val publicKey: String,  private val privateKey: String) {
    /**
     * Initiate a new transaction.
     * @param from The person who initiated the transfer.
     * @param to Receiver.
     * @param amount Number of transfers.
     * @return <signature, transaction>
    */
    fun transaction(from: String, to: String, amount: Long, chain: Chain): Triple<Boolean, String, Transaction> {
        val transaction = Transaction(from, to, amount, publicKey)
        val sign = transaction.sign(privateKey)
        return Triple(chain.addTransaction(transaction), sign, transaction)
    }
}