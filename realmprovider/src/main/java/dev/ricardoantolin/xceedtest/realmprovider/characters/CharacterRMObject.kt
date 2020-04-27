package dev.ricardoantolin.xceedtest.realmprovider.characters

import dev.ricardoantolin.xceedtest.data.characters.CharacterEntity
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CharacterRMObject(
    @PrimaryKey
    var name: String = "",
    var height: String = "",
    var mass: String = "",
    var hairColor: String = "",
    var skinColor: String = "",
    var eyeColor: String = "",
    var birthYear: String = "",
    var gender: String = ""
): RealmObject() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CharacterRMObject) return false

        if (name != other.name) return false
        if (height != other.height) return false
        if (mass != other.mass) return false
        if (hairColor != other.hairColor) return false
        if (skinColor != other.skinColor) return false
        if (eyeColor != other.eyeColor) return false
        if (birthYear != other.birthYear) return false
        if (gender != other.gender) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + height.hashCode()
        result = 31 * result + mass.hashCode()
        result = 31 * result + hairColor.hashCode()
        result = 31 * result + skinColor.hashCode()
        result = 31 * result + eyeColor.hashCode()
        result = 31 * result + birthYear.hashCode()
        result = 31 * result + gender.hashCode()
        return result
    }

}

fun CharacterRMObject.mapToEntity(): CharacterEntity =
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

fun CharacterEntity.mapToRealm(): CharacterRMObject =
    CharacterRMObject(
        name = name,
        height = height,
        mass = mass,
        hairColor = hairColor,
        skinColor = skinColor,
        eyeColor = eyeColor,
        birthYear = birthYear,
        gender = gender
    )

fun List<CharacterRMObject>.mapToEntity(): List<CharacterEntity> = map { it.mapToEntity() }
fun List<CharacterEntity>.mapToRealm(): List<CharacterRMObject> = map { it.mapToRealm() }