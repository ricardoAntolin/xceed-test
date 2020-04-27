package dev.ricardoantolin.xceedtest.networkprovider.characters

import com.google.gson.annotations.SerializedName
import dev.ricardoantolin.xceedtest.data.characters.CharacterEntity

data class CharacterResponse(
    val results: List<CharacterNPModel>
)

data class CharacterNPModel(
    val name: String,
    val height: String,
    val mass: String,
    @SerializedName("hair_color")
    val hairColor: String,
    @SerializedName("skin_color")
    val skinColor: String,
    @SerializedName("eye_color")
    val eyeColor: String,
    @SerializedName("birth_year")
    val birthYear: String,
    val gender: String
)

fun CharacterNPModel.mapToEntity(): CharacterEntity =
    CharacterEntity(
        name = name,
        height = height,
        mass = mass,
        hairColor = hairColor,
        skinColor = skinColor,
        eyeColor = eyeColor,
        birthYear = birthYear,
        gender = gender
    )

fun List<CharacterNPModel>.mapToEntity(): List<CharacterEntity> = map { it.mapToEntity() }