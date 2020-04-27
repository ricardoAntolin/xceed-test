package dev.ricardoantolin.xceedtest.scenes.list

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dev.ricardoantolin.xceedtest.R
import dev.ricardoantolin.xceedtest.scenes.common.base.BaseActivity
import javax.inject.Inject

class CharacterListActivity: BaseActivity() {
    override val layoutResId: Int = R.layout.character_list_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadCharacterListFragment()
    }
}