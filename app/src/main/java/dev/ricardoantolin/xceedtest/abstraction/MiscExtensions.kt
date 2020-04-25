package dev.ricardoantolin.xceedtest.abstraction

inline fun <T> T.applyIf(condition: Boolean, block: (T) -> T): T {
    if (condition) return block(this)
    return this
}