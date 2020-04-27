package dev.ricardoantolin.xceedtest.scenes.common.base

import com.pedrogomez.renderers.AdapteeCollection
import com.pedrogomez.renderers.RVRendererAdapter
import com.pedrogomez.renderers.Renderer
import com.pedrogomez.renderers.RendererBuilder

open class BaseAdapter<T>: RVRendererAdapter<T> {

    constructor(rendererBuilder: RendererBuilder<T>, collection: AdapteeCollection<T>): super(rendererBuilder, collection)

    constructor(rendererBuilder: RendererBuilder<T>): super(rendererBuilder)

    constructor(renderer: Renderer<T>): this(RendererBuilder(renderer))

    override fun updateRendererExtraValues(content: T, renderer: Renderer<T>, position: Int) {
        super.updateRendererExtraValues(content, renderer, position)
        if (renderer is BaseRenderer)
            renderer.position = position
    }

    fun getItems(): List<T> = (0 until super.getCollection().size()).map { collection.get(it) }

    fun replace(oldItem: T, newItem: T) {
        val index = getItems().indexOf(oldItem)
        val items = getItems().toMutableList()
        items[index] = newItem
        super.getCollection().clear()
        super.getCollection().addAll(items)
        notifyDataSetChanged()
    }

    fun replaceIfExist(oldItem: T, newItem: T) {
        if (getItems().contains(oldItem)) {
            replace(oldItem, newItem)
        }
    }

    fun updateItems(newItems: List<T>) {
        clear()
        addAll(newItems)
        notifyDataSetChanged()
    }

    fun addIfNotExist(item: T) {
        if (!getItems().contains(item)) {
            add(item)
            notifyItemInserted(collection.size())
        }
    }

    fun removeAndNotify(item: T) {
        remove(item)
        notifyItemRemoved(collection.size())
    }
}
