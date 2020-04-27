package dev.ricardoantolin.xceedtest.scenes.list

import android.os.Parcel
import android.os.Parcelable
import dev.ricardoantolin.xceedtest.domain.characters.Character

data class CharacterUIModel(
    val name: String,
    val height: String,
    val mass: String,
    val hairColor: String,
    val skinColor: String,
    val eyeColor: String,
    val birthYear: String,
    val gender: String
): Parcelable {
    constructor(source: Parcel): this(
        name = source.readString() ?: "",
        height = source.readString() ?: "",
        mass = source.readString() ?: "",
        hairColor = source.readString() ?: "",
        skinColor = source.readString() ?: "",
        eyeColor = source.readString() ?: "",
        birthYear = source.readString() ?: "",
        gender = source.readString() ?: ""
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeString(height)
        writeString(mass)
        writeString(hairColor)
        writeString(skinColor)
        writeString(eyeColor)
        writeString(birthYear)
        writeString(gender)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<CharacterUIModel> =
            object: Parcelable.Creator<CharacterUIModel> {
                override fun createFromParcel(source: Parcel): CharacterUIModel =
                    CharacterUIModel(source)

                override fun newArray(size: Int): Array<CharacterUIModel?> = arrayOfNulls(size)
            }
    }
}

fun Character.mapToUI(): CharacterUIModel =
    CharacterUIModel(
        name = name,
        height = height,
        mass = mass,
        hairColor = hairColor,
        skinColor = skinColor,
        eyeColor = eyeColor,
        birthYear = birthYear,
        gender = gender
    )

fun List<Character>.mapToUI(): List<CharacterUIModel> = map { it.mapToUI() }