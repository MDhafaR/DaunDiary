package org.d3if3068.assesment2.daundiary.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if3068.assesment2.daundiary.R
import org.d3if3068.assesment2.daundiary.model.Warna
import org.d3if3068.assesment2.daundiary.ui.theme.Coklat
import org.d3if3068.assesment2.daundiary.ui.theme.DaunDiaryTheme
import org.d3if3068.assesment2.daundiary.ui.theme.HijauTua
import org.d3if3068.assesment2.daundiary.ui.theme.LightPrimary
import org.d3if3068.assesment2.daundiary.ui.theme.MerahMuda
import org.d3if3068.assesment2.daundiary.ui.theme.Orange
import org.d3if3068.assesment2.daundiary.ui.theme.PinkMuda
import org.d3if3068.assesment2.daundiary.widgets.PilihanWarna

val itemWarna = listOf(
    Warna(
        nama = "kuning",
        isSelected = false,
        warna = LightPrimary
    ),
    Warna(
        nama = "merah",
        isSelected = false,
        warna = Color.Red
    ),
    Warna(
        nama = "hijau",
        isSelected = false,
        warna = Color.Green
    ),
    Warna(
        nama = "abu abu",
        isSelected = false,
        warna = Color.Gray
    ),
    Warna(
        nama = "biru tua",
        isSelected = false,
        warna = Color.Blue
    ),
    Warna(
        nama = "merah muda",
        isSelected = false,
        warna = MerahMuda
    ),
    Warna(
        nama = "hitam",
        isSelected = false,
        warna = Color.Black
    ),
    Warna(
        nama = "biru muda",
        isSelected = false,
        warna = Color.Cyan
    ),
    Warna(
        nama = "coklat",
        isSelected = false,
        warna = Coklat
    ),
    Warna(
        nama = "pink muda",
        isSelected = false,
        warna = PinkMuda
    ),
    Warna(
        nama = "orange",
        isSelected = false,
        warna = Orange
    ),
    Warna(
        nama = "hijau tua",
        isSelected = false,
        warna = HijauTua
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
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
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            modifier = Modifier.size(35.dp),
                            tint = Color.White,
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                }
            )
        },
    ) { padding ->
        InputContent(Modifier.padding(padding))
    }
}

@Composable
fun InputContent(modifier: Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {
        BukuInput()
        Text(
            modifier = Modifier.padding(top = 19.dp, bottom = 4.dp),
            text = "Warna",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = LightPrimary
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 13.dp),
            color = LightPrimary,
            thickness = 1.dp
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(4)) {
            itemsIndexed(itemWarna) {index, item ->
                PilihanWarna(item = item)
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 13.dp),
            color = LightPrimary,
            thickness = 1.dp
        )
        Button(
            modifier = Modifier
                .padding(top = 13.dp)
                .width(267.dp),
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(LightPrimary)
        ) {
            Text(text = "Simpan", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun BukuInput() {
    Box(
        modifier = Modifier
            .width(280.dp)
            .offset(x = (-15).dp)
    ) {
        Image(
            modifier = Modifier.size(279.dp),
            painter = painterResource(id = R.drawable.inputbook),
            contentDescription = "Buku Masukkan"
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = "Judul",
                onValueChange = { },
                modifier = Modifier
                    .padding(16.dp)
                    .width(160.dp)
                    .height(30.dp),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                ),
                placeholder = { Text(text = "Judul") },
                label = { Text(text = "Judul", color = Color.Gray) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {}),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            TextField(
                value = "Deskripsi",
                onValueChange = { },
                modifier = Modifier
                    .padding(16.dp)
                    .width(160.dp)
                    .height(120.dp),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                ),
                placeholder = { Text(text = "Deskripsi") },
                label = { Text(text = "Deskripsi", color = Color.Gray) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {}),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            TextField(
                value = "Pembuat",
                onValueChange = { },
                modifier = Modifier
                    .padding(16.dp)
                    .width(160.dp)
                    .height(30.dp),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                ),
                placeholder = { Text(text = "Pembuat") },
                label = { Text(text = "Pembuat", color = Color.Gray) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {}),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview
@Composable
fun InputPrev() {
    DaunDiaryTheme {
        InputScreen()
    }
}