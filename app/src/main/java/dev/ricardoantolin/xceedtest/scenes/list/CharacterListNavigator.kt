package dev.ricardoantolin.xceedtest.scenes.list

import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import dev.ricardoantolin.xceedtest.R
import dev.ricardoantolin.xceedtest.scenes.common.extensions.navigateToActivity
import dev.ricardoantolin.xceedtest.scenes.common.extensions.pushFragment
import dev.ricardoantolin.xceedtest.scenes.detail.CharacterDetailActivity

fun CharacterListActivity.loadCharacterListFragment() {
    pushFragment(
        fragment = CharacterListFragment(),
        contentFrame = R.id.characterListContainer
    )
}
const val CHARACTER_MODEL_KEY = "CHARACTER_MODEL_KEY"
fun FragmentActivity.goToDetailActivity(character: CharacterUIModel) {
    navigateToActivity(
        classToStartIntent = CharacterDetailActivity::class.java,
        extras = bundleOf(CHARACTER_MODEL_KEY to character)
    )
}