package dev.ricardoantolin.xceedtest.data.characters

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import dev.ricardoantolin.xceedtest.domain.characters.Character
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterDataRepositoryTest {
    lateinit var repository: CharacterDataRepository
    @Mock
    lateinit var storageProvider: CharacterStorageProvider
    @Mock
    lateinit var remoteProvider: CharacterRemoteProvider

    @Before
    fun setUp() {
        repository = CharacterDataRepository(remoteProvider, storageProvider)
    }

    @Test
    fun should_call_to_remote_and_storage_providers_at_update() {
        val givenCharacters = listOf(
            CharacterEntity(
                name = "Luke Skywalker",
                height = 172,
                mass = 77,
                hairColor = "blond",
                skinColor = "fair",
                eyeColor = "blue",
                birthYear = "19BBY",
                gender = "male")
        )

        given(remoteProvider.fetchRemoteCharacters(1))
            .willReturn(Single.just(givenCharacters))
        given(storageProvider.saveCharacters(givenCharacters))
            .willReturn(Completable.complete())

        repository.updateCharacters(1)
            .test()
            .assertNoErrors()
            .assertComplete()

        verify(remoteProvider, times(1)).fetchRemoteCharacters(1)
        verify(storageProvider, times(1)).saveCharacters(givenCharacters)
    }

    @Test
    fun should_not_call_to_storage_provider_when_remote_response_error_at_update() {
        val error = Throwable()
        given(remoteProvider.fetchRemoteCharacters(1)).willReturn(Single.error(error))

        repository.updateCharacters(1)
            .test()
            .assertError(error)

        verify(remoteProvider, times(1)).fetchRemoteCharacters(1)
        verify(storageProvider, never()).saveCharacters(any())
    }

    @Test
    fun should_call_to_storage_provider_at_get_all() {
        val givenCharacters = listOf(
            CharacterEntity(
                name = "Luke Skywalker",
                height = 172,
                mass = 77,
                hairColor = "blond",
                skinColor = "fair",
                eyeColor = "blue",
                birthYear = "19BBY",
                gender = "male")
        )

        given(storageProvider.findCharacters())
            .willReturn(Flowable.just(givenCharacters))

        val expectedCharacters = listOf(
            Character(
                name = "Luke Skywalker",
                height = 172,
                mass = 77,
                hairColor = "blond",
                skinColor = "fair",
                eyeColor = "blue",
                birthYear = "19BBY",
                gender = "male")
        )

        repository.bindCharacters()
            .test()
            .assertNoErrors()
            .assertValue { it == expectedCharacters }
            .assertComplete()


        verify(storageProvider, times(1)).findCharacters()
    }
}