package dev.ricardoantolin.xceedtest.scenes.common.extensions

inline fun <T> T.applyIf(condition: Boolean, block: (T) -> T): T {
    if (condition) return block(this)
    return this
}