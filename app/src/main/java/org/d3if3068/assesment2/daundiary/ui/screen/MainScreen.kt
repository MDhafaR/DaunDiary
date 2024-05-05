package org.d3if3068.assesment2.daundiary.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3068.assesment2.daundiary.R
import org.d3if3068.assesment2.daundiary.model.DataBuku
import org.d3if3068.assesment2.daundiary.model.DataDummy
import org.d3if3068.assesment2.daundiary.navigation.Screen
import org.d3if3068.assesment2.daundiary.ui.theme.DaunDiaryTheme
import org.d3if3068.assesment2.daundiary.ui.theme.LightPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
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
                    containerColor = LightPrimary,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            modifier = Modifier.size(26.dp),
                            tint = Color.White,
                            painter = painterResource(id = R.drawable.moon),
                            contentDescription = "bulan")
                    }
                }
            )
        },
        floatingActionButton = {
            IconButton(onClick = { navController.navigate(Screen.FormInput.route) }, Modifier.size(60.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.fab),
                    contentDescription = "fab",
                    Modifier.size(50.dp)
                )
            }
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier) {
    Box(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .size(400.dp)
                .offset(y = (-75).dp),
            painter = painterResource(id = R.drawable.lightback),
            contentDescription = "Pagi"
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
//                        IconButton(onClick = { /*TODO*/ }) {
//                            Image(
//                                modifier = Modifier
//                                    .padding(top = 8.dp)
//                                    .size(27.dp),
//                                painter = painterResource(id = R.drawable.bookfav),
//                                contentDescription = "buku favorit")
//                        }
                        TextField(
                            shape = RoundedCornerShape(17.dp),
                            leadingIcon = {
                                Image(
                                    modifier = Modifier
                                        .size(20.dp),
                                    painter = painterResource(id = R.drawable.search),
                                    contentDescription = "buku favorit"
                                )
                            },
                            value = "Search",
                            onValueChange = { },
                            modifier = Modifier
                                .padding(16.dp)
                                .width(143.dp)
                                .height(23.dp),
                            textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.White),
                            placeholder = { Text(text = "Search") },
                            label = {Text(text = "Search", color = Color.White)},
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                            keyboardActions = KeyboardActions(onSearch = {}),
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            )
                        )
                    }
//                    LazyVerticalGrid(
//                        columns = GridCells.Fixed(2),
//                        ) {
//                        itemsIndexed(DataDummy.data) {index, item ->
//                            Buku(item)
//                        }
//                        item {
//                            Spacer(modifier = Modifier.height(50.dp))
//                        }
//                    }
                    TampilanDataKosong()
                }
            }
        }
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
            contentDescription = "Empty Data"
        )
        Text(
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = LightPrimary,
            text = "Buat DiaryMu",
        )
    }
}

@Composable
fun Buku(dataBuku: DataBuku) {
    Box(
        modifier = Modifier
            .padding(bottom = 40.dp)
            .width(165.dp)
            .height(175.dp)
    ) {
        Icon(
            tint = dataBuku.warnaBuku,
            modifier = Modifier.size(279.dp),
            painter = painterResource(id = R.drawable.inputbook),
            contentDescription = "Buku Masukkan"
        )
        Column(
            modifier = Modifier
                .offset(x = 40.dp)
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
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 5.dp)
                    .height(90.dp)
                    .width(100.dp),
                text = dataBuku.deskripsi,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                textAlign = TextAlign.Center,
//                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = dataBuku.pengarang,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
            )
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