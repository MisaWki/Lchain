package com.rmolives.blockchain

abstract class Task(val address: String, var amount: Long, var state: Boolean) {
    abstract fun run()
}