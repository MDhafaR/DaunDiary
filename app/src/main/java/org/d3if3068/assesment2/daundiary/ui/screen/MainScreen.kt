package org.d3if3068.assesment2.daundiary.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3068.assesment2.daundiary.R
import org.d3if3068.assesment2.daundiary.database.BukuDb
import org.d3if3068.assesment2.daundiary.model.DataBuku
import org.d3if3068.assesment2.daundiary.model.MainViewModel
import org.d3if3068.assesment2.daundiary.navigation.Screen
import org.d3if3068.assesment2.daundiary.ui.theme.AbuAbu
import org.d3if3068.assesment2.daundiary.ui.theme.DarkPrimary
import org.d3if3068.assesment2.daundiary.ui.theme.DaunDiaryTheme
import org.d3if3068.assesment2.daundiary.ui.theme.LightPrimary
import org.d3if3068.assesment2.daundiary.util.SettingsDataStore
import org.d3if3068.assesment2.daundiary.util.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val dataStore = SettingsDataStore(LocalContext.current)
    val isLight by dataStore.layoutFlow.collectAsState(initial = true)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        modifier = Modifier.size(171.dp),
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = stringResource(
                            id = R.string.app_name
                        )
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = if (isLight) LightPrimary else DarkPrimary,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
//                actions = {
//                    IconButton(onClick = {
//                        CoroutineScope(Dispatchers.IO).launch {
//                            dataStore.saveLayout(!isLight)
//                        }
//                    }) {
//                        Icon(
//                            modifier = Modifier.size(26.dp),
//                            painter = painterResource(
//                                if (isLight) R.drawable.moon
//                                else R.drawable.sun
//                            ),
//                            contentDescription = stringResource(
//                                if (isLight) R.string.morning
//                                else R.string.night
//                            ),
//                            tint = Color.White,
//                        )
//                    }
//                }
            )
        },
        floatingActionButton = {
            IconButton(
                onClick = { navController.navigate(Screen.FormInput.route) },
                Modifier.size(60.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.fab),
                    tint = if (isLight) LightPrimary else DarkPrimary,
                    contentDescription = stringResource(R.string.fab),
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    ) { padding ->
        ScreenContent(dataStore, isLight, Modifier.padding(padding), navController)
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ScreenContent(
    dataStore: SettingsDataStore,
    isLight: Boolean,
    modifier: Modifier,
    navController: NavHostController
) {

    val context = LocalContext.current
    val db = BukuDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel: MainViewModel = viewModel(factory = factory)
    val data by viewModel.data.collectAsState()

    val searchQuery = viewModel.searchQuery.collectAsState().value

    Box(
        modifier = modifier
    ) {
        Image(
            modifier = if (isLight) Modifier
                .size(430.dp)
                .offset(y = (-100).dp)
            else
                Modifier
                    .size(430.dp)
                    .offset(y = (-50).dp),
            painter = if (isLight)
                painterResource(id = R.drawable.lightback_noicon)
            else painterResource(id = R.drawable.nighback_noicon),
            contentDescription = if (isLight) stringResource(R.string.pagi) else stringResource(
                R.string.malam
            )
        )
        LazyColumn {
            item {
                Column(
                    modifier = Modifier
                        .height(200.dp)
                ) {}
            }

            item {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 17.dp, topEnd = 17.dp))
                        .background(Color.White)
                        .height(660.dp)
                        .fillMaxWidth()
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy((-16).dp, Alignment.End)
                    ) {
                        IconButton(
                            modifier = Modifier
                                .padding(top = 23.dp, start = 8.dp, end = 8.dp)
                                .size(40.dp),
                            onClick = { /*TODO*/ }
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(30.dp),
                                painter = painterResource(id = R.drawable.bookfav),
                                contentDescription = "buku favorit"
                            )
                        }
                        TextField(
                            shape = RoundedCornerShape(17.dp),
                            leadingIcon = {
                                Image(
                                    modifier = Modifier
                                        .size(20.dp),
                                    painter = painterResource(id = R.drawable.search),
                                    contentDescription = stringResource(R.string.search)
                                )
                            },
                            value = searchQuery,
                            onValueChange = {
                                viewModel.setSearchQuery(it)
                            },
                            modifier = Modifier
                                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 4.dp)
                                .fillMaxWidth()
                                .height(49.dp),
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White
                            ),
                            placeholder = {
                                Text(
                                    text = stringResource(R.string.search),
                                    color = Color.White
                                )
                            },
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                            keyboardActions = KeyboardActions(onSearch = {
                                viewModel.setSearchQuery(searchQuery)
                            }),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = AbuAbu,
                                unfocusedContainerColor = AbuAbu,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            )
                        )
                    }
                    if (data.isEmpty()) {
                        TampilanDataKosong()
                    } else {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                        ) {
                            item {
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                            item {
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                            itemsIndexed(data) { _, item ->
                                Buku(item) {
                                    navController.navigate(Screen.FormUbah.withId(item.id))
                                }
                            }
                            item {
                                Spacer(modifier = Modifier.height(55.dp))
                            }
                            item {
                                Spacer(modifier = Modifier.height(55.dp))
                            }
                        }
                    }
                }
            }
        }
        Image(
            modifier = Modifier
                .padding(start = 15.dp, top = 24.dp)
                .clickable {
                    CoroutineScope(Dispatchers.IO).launch {
                        dataStore.saveLayout(!isLight)
                    }
                }
                .size(75.dp),
            painter = if (isLight) painterResource(id = R.drawable.sunicon)
            else painterResource(id = R.drawable.moonicon),
            contentDescription = if (isLight) "Icon Matahari" else "Icon Bulan"
        )
    }
}

@Composable
fun TampilanDataKosong() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .size(163.dp),
            painter = painterResource(id = R.drawable.emptyimage),
            contentDescription = stringResource(R.string.empty_data)
        )
        Text(
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = LightPrimary,
            text = stringResource(R.string.buat_diarymu),
        )
    }
}

@Composable
fun Buku(dataBuku: DataBuku, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(bottom = 40.dp)
            .width(165.dp)
            .height(175.dp)
    ) {
        Icon(
            tint = Color(dataBuku.warnaBuku),
            modifier = Modifier.size(279.dp),
            painter = painterResource(id = R.drawable.inputbook),
            contentDescription = stringResource(R.string.buku_masukkan)
        )
        Column(
            modifier = Modifier
                .offset(x = 40.dp)
                .clickable { onClick() }
                .width(135.dp)
                .height(175.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = dataBuku.judul,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                textAlign = TextAlign.Center,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp).fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                IconButton(
                    modifier = Modifier.size(17.dp),
                    onClick = { /*TODO*/ }) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.unfavorite),
                        contentDescription = "Bukan favorite",
                        tint = Color.White
                    )
                }
                Text(
                    text = dataBuku.pengarang,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                )
            }
        }
    }
}

//@Preview
//@Composable
//fun BukuPrev() {
//    DaunDiaryTheme {
//        Buku()
//    }
//}

@Preview(showBackground = true)
@Composable
fun MainPrev() {
    DaunDiaryTheme {
        MainScreen(rememberNavController())
    }
}