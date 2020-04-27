package dev.ricardoantolin.xceedtest.scenes.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedrogomez.renderers.AdapteeCollection
import com.pedrogomez.renderers.RendererBuilder
import com.pedrogomez.renderers.RendererViewHolder
import dev.ricardoantolin.xceedtest.R
import dev.ricardoantolin.xceedtest.scenes.common.ErrorUIModel
import dev.ricardoantolin.xceedtest.scenes.common.base.BaseAdapter
import dev.ricardoantolin.xceedtest.scenes.common.base.BaseRenderer
import dev.ricardoantolin.xceedtest.scenes.common.extensions.setSensitiveClickListener
import kotlinx.android.synthetic.main.character_list_item.view.*

abstract class SimpleRenderer<T>: BaseRenderer<T>() {

    abstract val layout: Int

    override fun inflate(inflater: LayoutInflater, parent: ViewGroup?): View? =
        inflater.inflate(layout, parent, false)

    override fun render() { }
    override fun hookListeners(rootView: View) { }
    override fun setUpView(rootView: View?) { }
}

class CharacterItemRenderer(
    private val onClick: (CharacterUIModel, Int) -> Unit
): SimpleRenderer<CharacterItemType.Listing>() {
    override val layout: Int
        get() = R.layout.character_list_item

    override fun render() {
        super.render()
        with(rootView){
            lblCharacterName.text = content.model.name
            lblBornDate.text = content.model.birthYear
        }
    }

    override fun hookListeners(rootView: View) {
        rootView.setSensitiveClickListener {
            onClick(content.model, position)
        }
    }
}

class CharacterItemLoadingRenderer: SimpleRenderer<CharacterItemType.Loading>() {
    override val layout: Int
        get() = R.layout.character_list_item_loading
}

class CharacterItemErrorRenderer: SimpleRenderer<CharacterItemType.Error>() {
    override val layout: Int
        get() = R.layout.character_list_item
}

class UpdatableAdapter<T>(
    rendererBuilder: RendererBuilder<T>,
    collection: AdapteeCollection<T>
): BaseAdapter<T>(rendererBuilder, collection) {
    override fun onBindViewHolder(
        holder: RendererViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val renderer = holder.renderer

        if (renderer is UpdatableRenderer && payloads.isNotEmpty()) {
            renderer.partialUpdate()
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }
}

interface UpdatableRenderer {
    fun partialUpdate()
}

class CharacterListRendererBuilder: RendererBuilder<CharacterItemType>() {
    override fun getPrototypeClass(content: CharacterItemType): Class<*> =
        when (content) {
            is CharacterItemType.Loading -> CharacterItemLoadingRenderer::class.java
            is CharacterItemType.Listing -> CharacterItemRenderer::class.java
            is CharacterItemType.Error -> CharacterItemErrorRenderer::class.java
        }
}

sealed class CharacterItemType {
    class Loading: CharacterItemType()
    class Listing(val model: CharacterUIModel): CharacterItemType()
    class Error(val error: ErrorUIModel): CharacterItemType()
}