package com.example.feedzaitest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.feedzaitest.ui.theme.FeedzaiTestTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FeedzaiTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainItemListLayout()
                }
            }
        }
    }
}

@Composable
fun MainItemListLayout(
    itemList: List<InfoListUIModel.InfoListItemModel> = emptyList(),
    viewModel: InfoListViewModel = koinViewModel(),
) {
    val scrollState = rememberLazyStaggeredGridState()
    val fetchedItems by viewModel.items.collectAsStateWithLifecycle()
    var items by rememberSaveable { mutableStateOf(itemList) }

    if (fetchedItems is InfoListUIModel.Content && items.isEmpty()) {
        items = (fetchedItems as InfoListUIModel.Content).items
    } else {
        viewModel.getItems()
    }

    if (fetchedItems is InfoListUIModel.NotFoundError
        || (fetchedItems is InfoListUIModel.Content
            && (fetchedItems as InfoListUIModel.Content).items.isEmpty())
    ) {
        EmptyLayout()
    } else {
        ItemListContentLayout(items, scrollState)
    }
}

@Composable
fun ItemListContentLayout(
    itemList: List<InfoListUIModel.InfoListItemModel>,
    scrollState: LazyStaggeredGridState,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalItemSpacing = 16.dp,
            state = scrollState,
        ) {
            items(itemList) { item ->
                InfoListItem(
                    InfoListUIModel.InfoListItemModel(item.key, item.value)
                )
            }
        }

    }
}

@Composable
fun EmptyLayout() {
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    FeedzaiTestTheme {
        //MainItemListLayout()
    }
}