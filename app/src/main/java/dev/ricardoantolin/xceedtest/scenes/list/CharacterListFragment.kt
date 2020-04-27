package dev.ricardoantolin.xceedtest.scenes.list

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.recyclerview.scrollEvents
import com.jakewharton.rxbinding3.widget.textChanges
import com.pedrogomez.renderers.ListAdapteeCollection
import dev.ricardoantolin.xceedtest.R
import dev.ricardoantolin.xceedtest.scenes.common.base.BaseFragment
import dev.ricardoantolin.xceedtest.scenes.common.base.ViewModelBindable
import dev.ricardoantolin.xceedtest.scenes.common.extensions.toggleVisibility
import io.reactivex.Observable
import kotlinx.android.synthetic.main.character_list_fragment.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CharacterListFragment: BaseFragment(R.layout.character_list_fragment), ViewModelBindable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: CharacterListViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setListener()
    }

    private fun setListener() {
        searchContainer.setOnClickListener {
            txtSearch.toggleVisibility()
        }
    }

    override fun bindViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CharacterListViewModel::class.java)

        val adapter = createCharacterListAdapter()
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        setUpRecycler(adapter, layoutManager)

        viewModel.bindState(
            pageUpdateObservable = getPageChangeObservable(layoutManager),
            searchQueryObservable = getSearchTextChangeObservable()
        ).observe(this, Observer {
                adapter.clear()
                adapter.addAll(it.mapToCharacterItemType())
                adapter.notifyDataSetChanged()
            })
    }

    private fun getSearchTextChangeObservable() =
        txtSearchInput.textChanges()
            .map { it.toString() }
            .throttleLast(1, TimeUnit.SECONDS)

    private fun getPageChangeObservable(layoutManager: LinearLayoutManager): Observable<Int> {
        val itemsPerPage = 10
        val pageOffset = 2
        val positionToLoadNextPage = 8
        return rcyCharacterList.scrollEvents()
            .flatMap {
                val lastItemPosition = layoutManager.findLastVisibleItemPosition()
                if (lastItemPosition % itemsPerPage == positionToLoadNextPage)
                    Observable.just(lastItemPosition.div(itemsPerPage) + pageOffset)
                else Observable.empty()
            }.startWith(1)
    }

    private fun setUpRecycler(
        adapter: UpdatableAdapter<CharacterItemType>,
        layoutManager: LinearLayoutManager
    ) {
        rcyCharacterList.layoutManager = layoutManager
        rcyCharacterList.adapter = adapter
    }


    private fun createCharacterListAdapter(): UpdatableAdapter<CharacterItemType> {
        val rendererBuilder = CharacterListRendererBuilder().withPrototypes(
            listOf(CharacterItemLoadingRenderer(), CharacterItemRenderer(onClick = ::onItemClick))
        )
        return UpdatableAdapter(rendererBuilder, ListAdapteeCollection())
    }

    private fun onItemClick(item: CharacterUIModel, position: Int) {
        activity?.goToDetailActivity(item)
    }
}