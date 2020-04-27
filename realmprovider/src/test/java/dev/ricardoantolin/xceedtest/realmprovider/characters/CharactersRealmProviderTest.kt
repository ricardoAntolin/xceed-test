package dev.ricardoantolin.xceedtest.realmprovider.characters

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import dev.ricardoantolin.xceedtest.data.characters.CharacterEntity
import dev.ricardoantolin.xceedtest.realmprovider.RealmTest
import dev.ricardoantolin.xceedtest.realmprovider.extensions.completableTransaction
import io.reactivex.Flowable
import io.realm.RealmQuery
import io.realm.RealmResults
import org.junit.Before

import org.junit.Test
import org.powermock.api.mockito.PowerMockito.mockStatic

class CharactersRealmProviderTest: RealmTest() {
    private lateinit var charactersRealmProvider: CharactersRealmProvider

    @Before
    override fun setUp() {
        super.setUp()
        charactersRealmProvider = CharactersRealmProvider()
    }

    @Test
    fun must_save_all_products_on_realm() {
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

        charactersRealmProvider.saveCharacters(givenCharacters)
            .test()
            .assertNoErrors()
            .assertComplete()

        val expectedCharacters = listOf(
            CharacterRMObject(
                name = "Luke Skywalker",
                height = 172,
                mass = 77,
                hairColor = "blond",
                skinColor = "fair",
                eyeColor = "blue",
                birthYear = "19BBY",
                gender = "male")
        )

        verify(mockRealm, times(1))
            .completableTransaction{ mockRealm.copyToRealmOrUpdate(expectedCharacters) }
    }

    @Test
    fun must_get_all_products_stored_on_realm() {
        val givenCharacters = listOf(
            CharacterRMObject(
                name = "Luke Skywalker",
                height = 172,
                mass = 77,
                hairColor = "blond",
                skinColor = "fair",
                eyeColor = "blue",
                birthYear = "19BBY",
                gender = "male")
        )

        val mockRealmQuery: RealmQuery<CharacterRMObject> = mockRealmQuery()
        given(mockRealm.where(CharacterRMObject::class.java)).willReturn(mockRealmQuery)
        mockStatic(RealmResults::class.java)
        val mockRealmResults: RealmResults<CharacterRMObject> = mockRealmResults()
        given(mockRealmQuery.findAllAsync()).willReturn(mockRealmResults)
        given(mockRealmResults.asFlowable()).willReturn(Flowable.just(mockRealmResults))
        given(mockRealmResults.isLoaded()).willReturn(true)
        given(mockRealm.copyFromRealm(mockRealmResults)).willReturn(givenCharacters)


        val expectedCharacters = listOf(
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

        charactersRealmProvider.findCharacters()
            .test()
            .assertNoErrors()
            .assertValue { it == expectedCharacters }
    }
}
