package dev.ricardoantolin.xceedtest.domain.characters

import dev.ricardoantolin.xceedtest.domain.executors.PostExecutionThread
import io.reactivex.Completable
import javax.inject.Inject

interface UpdateCharacterUseCase {
    fun execute(page: Int, name: String): Completable
}

class UpdateCharacter @Inject constructor(
    private val postExecutionThread: PostExecutionThread,
    private val repository: CharacterRepository
): UpdateCharacterUseCase {
    override fun execute(page: Int, name: String): Completable =
        repository.updateCharacters(page, name)
            .observeOn(postExecutionThread.getScheduler())
}