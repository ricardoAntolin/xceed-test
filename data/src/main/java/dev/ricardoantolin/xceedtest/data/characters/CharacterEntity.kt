package dev.ricardoantolin.xceedtest.data.characters

import dev.ricardoantolin.xceedtest.domain.characters.Character

data class CharacterEntity(
    val name: String,
    val height: String,
    val mass: String,
    val hairColor: String,
    val skinColor: String,
    val eyeColor: String,
    val birthYear: String,
    val gender: String
)

fun CharacterEntity.mapToDomain(): Character =
    Character(
        name = name,
        height = height,
        mass = mass,
        hairColor = hairColor,
        skinColor = skinColor,
        eyeColor = eyeColor,
        birthYear = birthYear,
        gender = gender
    )

fun List<CharacterEntity>.mapToDomain(): List<Character> = map { it.mapToDomain() }