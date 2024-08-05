package com.example.kmmapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kmmapp.data.MobileItem
import com.example.kmmapp.viewmodel.MobileDataState
import com.example.kmmapp.viewmodel.MobileListViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MobileListViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            ), title = {
                                Text(text = "Product List", color = Color.White)
                            })
                    }) { contentPadding ->
                    MobileListView(modifier = Modifier.padding(contentPadding), viewModel)
                }
            }
        }
    }
}

@Composable
fun MobileListView(modifier: Modifier, viewModel: MobileListViewModel) {
    LaunchedEffect("FETCH DATA") {
        viewModel.getMobilesFake()
    }

    val listItemState = viewModel.mobileDataState.collectAsState()
    val listItems = listItemState.value

    if (listItems is MobileDataState.Success) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
        ) {
            items(listItems.data) { mobileDetail ->
                MobileRow(mobileItem = mobileDetail)
            }
        }
    }
}

@Composable
fun MobileRow(modifier: Modifier = Modifier, mobileItem: MobileItem) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(modifier = modifier.padding(10.dp)) {
            Text(text = mobileItem.id)
            Text(text = mobileItem.name)
        }
    }
}