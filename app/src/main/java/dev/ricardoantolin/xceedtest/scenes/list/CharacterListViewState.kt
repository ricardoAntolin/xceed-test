package dev.ricardoantolin.xceedtest.scenes.list

import dev.ricardoantolin.xceedtest.scenes.common.ErrorUIModel

sealed class CharacterListViewState {
    class Loading: CharacterListViewState()
    class Listing(val characterList: List<CharacterUIModel>): CharacterListViewState()
    class Error(val error: ErrorUIModel): CharacterListViewState()
}

fun CharacterListViewState.mapToCharacterItemType(): List<CharacterItemType> {
    return when (this) {
        is CharacterListViewState.Loading -> listOf(0,1,2,3,4,5,6,7,8,9).map { CharacterItemType.Loading() }
        is CharacterListViewState.Listing ->  characterList.map{ CharacterItemType.Listing(it) }
        is CharacterListViewState.Error -> listOf(CharacterItemType.Error(error))
    }
}