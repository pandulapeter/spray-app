package com.gyorgyzoltan.sprayApp.main.shared.utilities

class Consumable<out T>(private val content: T) {

    var consumed = false
        private set

    fun consume(): T? = if (consumed) null else {
        consumed = true
        content
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Consumable<*>
        if (content != other.content) return false
        if (consumed != other.consumed) return false
        return true
    }

    override fun hashCode(): Int {
        var result = content?.hashCode() ?: 0
        result = 31 * result + consumed.hashCode()
        return result
    }
}