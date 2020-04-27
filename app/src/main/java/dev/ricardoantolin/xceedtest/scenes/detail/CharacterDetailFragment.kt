package dev.ricardoantolin.xceedtest.scenes.detail

import android.os.Bundle
import android.view.View
import dev.ricardoantolin.xceedtest.R
import dev.ricardoantolin.xceedtest.scenes.common.base.BaseFragment
import dev.ricardoantolin.xceedtest.scenes.list.CHARACTER_MODEL_KEY
import dev.ricardoantolin.xceedtest.scenes.list.CharacterUIModel
import kotlinx.android.synthetic.main.character_detail_fragment.*

class CharacterDetailFragment: BaseFragment(R.layout.character_detail_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<CharacterUIModel>(CHARACTER_MODEL_KEY)?.let { fillData(it) }
    }

    private fun fillData(character: CharacterUIModel) = with(character) {
        lblCharacterName.text = name
        lblHeight.text = height
        lblMass.text = mass
        lblHairColor.text = hairColor
        lblSkinColor.text = skinColor
        lblGender.text = eyeColor
        lblBornDate.text = birthYear
        lblGender.text = gender
    }
}