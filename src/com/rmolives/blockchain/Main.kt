package com.rmolives.blockchain

fun main() {
    val chain = Chain()
    val t1 = Register("RMOlive", "rmolives@gamil.com")
    val t2 = Register("T2", "rmolives@gamil.com")
    val member = t1.member
    val (a) = member.transaction(member.user.address, t2.member.user.address, 0, chain)
    println(a)
    println(chain.mineTransactionPool(member.user.address, 0).hash)
    println(chain.validateChain())
}