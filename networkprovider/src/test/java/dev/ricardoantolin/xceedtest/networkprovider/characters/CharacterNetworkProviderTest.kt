package dev.ricardoantolin.xceedtest.networkprovider.characters

import dev.ricardoantolin.xceedtest.data.RemoteProviderError
import dev.ricardoantolin.xceedtest.networkprovider.RemoteServiceConfig
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File
import java.io.IOException


class CharacterNetworkProviderTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var characterNetworkProvider: CharacterNetworkProvider

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        val remoteConfig = RemoteServiceConfig(
            debug = true,
            baseUrl = mockWebServer.url("/").toString()
        )
        characterNetworkProvider = CharacterNetworkProvider(remoteConfig)
    }

    @Test
    fun should_response_list_of_3_products() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody("{\n" +
                        "  \"count\": 82,\n" +
                        "  \"next\": \"http://swapi.dev/api/people/?page=2\",\n" +
                        "  \"previous\": null,\n" +
                        "  \"results\": [\n" +
                        "    {\n" +
                        "      \"name\": \"Luke Skywalker\",\n" +
                        "      \"height\": \"172\",\n" +
                        "      \"mass\": \"77\",\n" +
                        "      \"hair_color\": \"blond\",\n" +
                        "      \"skin_color\": \"fair\",\n" +
                        "      \"eye_color\": \"blue\",\n" +
                        "      \"birth_year\": \"19BBY\",\n" +
                        "      \"gender\": \"male\",\n" +
                        "      \"homeworld\": \"http://swapi.dev/api/planets/1/\",\n" +
                        "      \"films\": [\n" +
                        "        \"http://swapi.dev/api/films/1/\",\n" +
                        "        \"http://swapi.dev/api/films/2/\",\n" +
                        "        \"http://swapi.dev/api/films/3/\",\n" +
                        "        \"http://swapi.dev/api/films/6/\"\n" +
                        "      ],\n" +
                        "      \"species\": [],\n" +
                        "      \"vehicles\": [\n" +
                        "        \"http://swapi.dev/api/vehicles/14/\",\n" +
                        "        \"http://swapi.dev/api/vehicles/30/\"\n" +
                        "      ],\n" +
                        "      \"starships\": [\n" +
                        "        \"http://swapi.dev/api/starships/12/\",\n" +
                        "        \"http://swapi.dev/api/starships/22/\"\n" +
                        "      ],\n" +
                        "      \"created\": \"2014-12-09T13:50:51.644000Z\",\n" +
                        "      \"edited\": \"2014-12-20T21:17:56.891000Z\",\n" +
                        "      \"url\": \"http://swapi.dev/api/people/1/\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"name\": \"C-3PO\",\n" +
                        "      \"height\": \"167\",\n" +
                        "      \"mass\": \"75\",\n" +
                        "      \"hair_color\": \"n/a\",\n" +
                        "      \"skin_color\": \"gold\",\n" +
                        "      \"eye_color\": \"yellow\",\n" +
                        "      \"birth_year\": \"112BBY\",\n" +
                        "      \"gender\": \"n/a\",\n" +
                        "      \"homeworld\": \"http://swapi.dev/api/planets/1/\",\n" +
                        "      \"films\": [\n" +
                        "        \"http://swapi.dev/api/films/1/\",\n" +
                        "        \"http://swapi.dev/api/films/2/\",\n" +
                        "        \"http://swapi.dev/api/films/3/\",\n" +
                        "        \"http://swapi.dev/api/films/4/\",\n" +
                        "        \"http://swapi.dev/api/films/5/\",\n" +
                        "        \"http://swapi.dev/api/films/6/\"\n" +
                        "      ],\n" +
                        "      \"species\": [\n" +
                        "        \"http://swapi.dev/api/species/2/\"\n" +
                        "      ],\n" +
                        "      \"vehicles\": [],\n" +
                        "      \"starships\": [],\n" +
                        "      \"created\": \"2014-12-10T15:10:51.357000Z\",\n" +
                        "      \"edited\": \"2014-12-20T21:17:50.309000Z\",\n" +
                        "      \"url\": \"http://swapi.dev/api/people/2/\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"name\": \"R2-D2\",\n" +
                        "      \"height\": \"96\",\n" +
                        "      \"mass\": \"32\",\n" +
                        "      \"hair_color\": \"n/a\",\n" +
                        "      \"skin_color\": \"white, blue\",\n" +
                        "      \"eye_color\": \"red\",\n" +
                        "      \"birth_year\": \"33BBY\",\n" +
                        "      \"gender\": \"n/a\",\n" +
                        "      \"homeworld\": \"http://swapi.dev/api/planets/8/\",\n" +
                        "      \"films\": [\n" +
                        "        \"http://swapi.dev/api/films/1/\",\n" +
                        "        \"http://swapi.dev/api/films/2/\",\n" +
                        "        \"http://swapi.dev/api/films/3/\",\n" +
                        "        \"http://swapi.dev/api/films/4/\",\n" +
                        "        \"http://swapi.dev/api/films/5/\",\n" +
                        "        \"http://swapi.dev/api/films/6/\"\n" +
                        "      ],\n" +
                        "      \"species\": [\n" +
                        "        \"http://swapi.dev/api/species/2/\"\n" +
                        "      ],\n" +
                        "      \"vehicles\": [],\n" +
                        "      \"starships\": [],\n" +
                        "      \"created\": \"2014-12-10T15:11:50.376000Z\",\n" +
                        "      \"edited\": \"2014-12-20T21:17:50.311000Z\",\n" +
                        "      \"url\": \"http://swapi.dev/api/people/3/\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")
        )

        characterNetworkProvider.fetchRemoteCharacters(1)
            .test()
            .assertNoErrors()
            .assertValue { it.firstOrNull()?.name == "Luke Skywalker" }
            .assertComplete()
    }

    @Test
    fun should_response_empty_list_of_products() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody("{\"results\":[]}")
        )

        characterNetworkProvider.fetchRemoteCharacters(1)
            .test()
            .assertNoErrors()
            .assertValue { it.isEmpty() }
            .assertComplete()
    }

    @Test
    fun should_response_bad_request() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(400)
                .setBody("Bad Request")
        )

        characterNetworkProvider.fetchRemoteCharacters(1)
            .test()
            .assertError {
                it == RemoteProviderError.HttpError(400, "Bad Request")
            }
    }

    @Test
    fun should_response_server_error() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(500)
                .setBody("Server Error")
        )

        characterNetworkProvider.fetchRemoteCharacters(1)
            .test()
            .assertError { it == RemoteProviderError.HttpError(500, "Server Error") }
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Throws(IOException::class)
    fun getJson(path : String) : String {
        val uri = this.javaClass.classLoader.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }
}
