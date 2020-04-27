package dev.ricardoantolin.xceedtest.domain.characters

import dev.ricardoantolin.xceedtest.domain.executors.PostExecutionThread
import io.reactivex.Flowable
import javax.inject.Inject

interface BindCharacterUseCase {
    fun execute(searchQuery: String): Flowable<List<Character>>
}

class BindCharacter @Inject constructor(
    private val postExecutionThread: PostExecutionThread,
    private val repository: CharacterRepository
): BindCharacterUseCase {
    override fun execute(searchQuery: String): Flowable<List<Character>> =
        repository.bindCharacters(searchQuery)
            .observeOn(postExecutionThread.getScheduler())
}