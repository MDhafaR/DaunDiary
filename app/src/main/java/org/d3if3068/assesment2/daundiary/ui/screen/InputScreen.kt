package org.d3if3068.assesment2.daundiary.ui.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3068.assesment2.daundiary.R
import org.d3if3068.assesment2.daundiary.database.BukuDb
import org.d3if3068.assesment2.daundiary.model.InputViewModel
import org.d3if3068.assesment2.daundiary.model.Warna
import org.d3if3068.assesment2.daundiary.navigation.Screen
import org.d3if3068.assesment2.daundiary.ui.theme.Coklat
import org.d3if3068.assesment2.daundiary.ui.theme.CoklatTua
import org.d3if3068.assesment2.daundiary.ui.theme.DarkPrimary
import org.d3if3068.assesment2.daundiary.ui.theme.DaunDiaryTheme
import org.d3if3068.assesment2.daundiary.ui.theme.HijauTua
import org.d3if3068.assesment2.daundiary.ui.theme.LightPrimary
import org.d3if3068.assesment2.daundiary.ui.theme.MerahMuda
import org.d3if3068.assesment2.daundiary.ui.theme.Orange
import org.d3if3068.assesment2.daundiary.ui.theme.PinkMuda
import org.d3if3068.assesment2.daundiary.util.SettingsDataStore
import org.d3if3068.assesment2.daundiary.util.ViewModelFactory
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
        nama = "coklat tua",
        isSelected = false,
        warna = CoklatTua
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

//const val KEY_ID_BUKU_BARU = "idBukuBaru"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen(
    onNavigateToScreen: (String, String, String, Int) -> Unit,
    navController: NavHostController,
    id: Int? = null
) {
    val dataStore = SettingsDataStore(LocalContext.current)
    val isLight by dataStore.layoutFlow.collectAsState(initial = true)

    val context = LocalContext.current
    val db = BukuDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel: InputViewModel = viewModel(factory = factory)

    var judul by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }
    var pengarang by remember { mutableStateOf("") }
    var isi by remember { mutableStateOf("") }
    var warnaBuku by remember { mutableStateOf(Color.Unspecified) }

    var showDialog by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true) {
        if (id == null) return@LaunchedEffect
        val data = viewModel.getBook(id) ?: return@LaunchedEffect
        judul = data.judul
        deskripsi = data.deskripsi
        pengarang = data.pengarang
        warnaBuku = Color(data.warnaBuku)
    }

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
                    containerColor = if (isLight) LightPrimary else DarkPrimary,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            modifier = Modifier.size(35.dp),
                            tint = Color.White,
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali)
                        )
                    }
                },
//                actions = {
//                    if (id != null) {
//                        Menu(update = {showDialog = true}, delete = {showDialog = true})
//                        KonfirmasiHapus(
//                            openDialog = showDialog,
//                            onDismissRequest = { showDialog = false }) {
//                            showDialog = false
//                            viewModel.delete(id)
//                            navController.popBackStack()
//                        }
//                    }
//                }
            )
        },
    ) { padding ->
        InputContent(
            dataJudul = judul,
            onTitleChange = { judul = it },
            dataDeskripsi = deskripsi,
            onDescChange = { deskripsi = it },
            dataPengarang = pengarang,
            onAuthorChange = { pengarang = it },
            dataWarna = warnaBuku,
            onColorChange = { warnaBuku = it },
            isi = isi,
            onIsiChange = { isi = it },
            modifier = Modifier.padding(padding),
            contextnya = context,
            idNya = id,
            viewModel = viewModel,
            navController = navController,
            isLight,
            onNavigateToScreen
        )
    }
}

@Composable
fun InputContent(
    dataJudul: String, onTitleChange: (String) -> Unit,
    dataDeskripsi: String, onDescChange: (String) -> Unit,
    dataPengarang: String, onAuthorChange: (String) -> Unit,
    dataWarna: Color, onColorChange: (Color) -> Unit,
    isi: String, onIsiChange: (String) -> Unit,
    modifier: Modifier,
    contextnya: Context,
    idNya: Int?,
    viewModel: InputViewModel,
    navController: NavHostController,
    isLight: Boolean,
    onNavigateScreen: (String, String, String, Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {
        BukuInput(
            dataJudul,
            onTitleChange,
            dataDeskripsi,
            onDescChange,
            dataPengarang,
            onAuthorChange,
            dataWarna
        )
        Text(
            modifier = Modifier.padding(top = 19.dp, bottom = 4.dp),
            text = stringResource(R.string.warna),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = if (isLight) LightPrimary else DarkPrimary
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 13.dp),
            color = if (isLight) LightPrimary else DarkPrimary,
            thickness = 1.dp
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(4)
        ) {
            itemWarna.forEach { warnaNya ->
                item {
                    PilihanWarna(
                        warna = warnaNya.warna,
                        isSelected = dataWarna == warnaNya.warna,
                        perubahanWarna = { onColorChange(warnaNya.warna) }
                    )
                }
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 13.dp),
            color = if (isLight) LightPrimary else DarkPrimary,
            thickness = 1.dp
        )
        if (idNya == null) {
            Button(
                modifier = Modifier
                    .padding(top = 13.dp)
                    .width(267.dp),
                onClick = {
                    if (dataJudul == "" || dataDeskripsi == "" || dataPengarang == "" || dataWarna.hashCode() == 16) {
                        Toast.makeText(contextnya, "Invalid", Toast.LENGTH_LONG).show()
                        return@Button
                    }

                    onNavigateScreen(dataJudul, dataDeskripsi, dataPengarang, dataWarna.hashCode())

//
//                navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(if (isLight) LightPrimary else DarkPrimary)
            ) {
                Text(text = "Lanjutkan", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        } else {
            Button(
                modifier = Modifier
                    .padding(top = 13.dp)
                    .width(267.dp),
                onClick = {
                    if (dataJudul == "" || dataDeskripsi == "" || dataPengarang == "" || dataWarna.hashCode() == 16) {
                        Toast.makeText(contextnya, "Invalid", Toast.LENGTH_LONG).show()
                        return@Button
                    }
                    val tanggalSaatIni: Long = System.currentTimeMillis()

                    viewModel.updateCover(dataJudul, dataDeskripsi, dataPengarang, dataWarna.hashCode(),tanggalSaatIni, idNya)
                navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(if (isLight) LightPrimary else DarkPrimary)
            ) {
                Text(text = "Simpan", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun BukuInput(
    judul: String,
    perubahanJudul: (String) -> Unit,
    deskripsi: String,
    perubahanDeskrips: (String) -> Unit,
    pengarang: String,
    perubahanPengarang: (String) -> Unit,
    warna: Color,
) {
    Box(
        modifier = Modifier
            .width(280.dp)
            .offset(x = (-15).dp)
    ) {
        Icon(
            modifier = Modifier.size(320.dp),
            painter = painterResource(id = R.drawable.inputbook),
            tint = warna,
            contentDescription = stringResource(R.string.buku_masukkan)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = judul,
                onValueChange = { perubahanJudul(it.take(28)) },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .width(160.dp)
                    .height(60.dp),
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                ),
                placeholder = { Text(text = stringResource(R.string.judul)) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            TextField(
                value = deskripsi,
                onValueChange = { perubahanDeskrips(it.take(110)) },
                modifier = Modifier
                    .padding(16.dp)
                    .width(160.dp)
                    .height(130.dp),
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                ),
                placeholder = { Text(text = stringResource(R.string.deskripsi)) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            TextField(
                value = pengarang,
                onValueChange = { perubahanPengarang(it.take(20)) },
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .width(160.dp)
                    .height(60.dp),
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                ),
                placeholder = { Text(text = stringResource(R.string.pengarang)) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
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
        InputScreen(onNavigateToScreen = { judul, deskripsi, pengarang, warna ->
            println("Judul: $judul, Deskripsi: $deskripsi, Pengarang: $pengarang")
        }, rememberNavController())
    }
}