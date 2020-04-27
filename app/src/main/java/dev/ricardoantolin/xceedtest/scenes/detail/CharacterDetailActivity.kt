package dev.ricardoantolin.xceedtest.scenes.detail

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dev.ricardoantolin.xceedtest.R
import dev.ricardoantolin.xceedtest.scenes.common.base.BaseActivity
import dev.ricardoantolin.xceedtest.scenes.common.extensions.pushFragment
import javax.inject.Inject

class CharacterDetailActivity: BaseActivity() {
    override val layoutResId: Int = R.layout.character_detail_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragment = CharacterDetailFragment()
        fragment.arguments = intent.extras
        pushFragment(fragment, R.id.characterDetailContainer)
    }
}