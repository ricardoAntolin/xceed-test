package dev.ricardoantolin.xceedtest.scenes.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import dev.ricardoantolin.xceedtest.domain.characters.BindCharacterUseCase
import dev.ricardoantolin.xceedtest.domain.characters.UpdateCharacterUseCase
import dev.ricardoantolin.xceedtest.scenes.common.ErrorUIModel
import dev.ricardoantolin.xceedtest.scenes.common.base.BaseViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(
    private val updateCharacterUseCase: UpdateCharacterUseCase,
    private val bindCharacterUseCase: BindCharacterUseCase
): BaseViewModel() {

    fun bindState(
        pageUpdateObservable: Observable<Int>,
        searchQueryObservable: Observable<String>
    ): LiveData<CharacterListViewState> =
        LiveDataReactiveStreams.fromPublisher(
            Flowable.merge(
                handlePageUpdateObservable(pageUpdateObservable, searchQueryObservable),
                handleSearchObservable(searchQueryObservable)
            ).throttleLast(1, TimeUnit.SECONDS)
        )

    private fun handleSearchObservable(
        searchQueryObservable: Observable<String>
    ): Flowable<CharacterListViewState> =
        searchQueryObservable
            .toFlowable(BackpressureStrategy.BUFFER).flatMap { search ->
            bindCharacterUseCase.execute(search)
                .map<CharacterListViewState> { CharacterListViewState.Listing(it.mapToUI()) }
                .startWith(CharacterListViewState.Loading())
        }

    private fun handlePageUpdateObservable(
        pageUpdateObservable: Observable<Int>,
        searchQueryObservable: Observable<String>
    ): Flowable<CharacterListViewState> =
        Observable.combineLatest(
            searchQueryObservable,
            pageUpdateObservable,
            BiFunction{search:String, page:Int -> page to search}
        ).flatMapCompletable { updateCharacterUseCase.execute(it.first, it.second) }
            .andThen<CharacterListViewState>(Observable.empty())
            .toFlowable(BackpressureStrategy.BUFFER)
            .onErrorReturn { CharacterListViewState.Error(ErrorUIModel(title = it.message ?: "")) }
}