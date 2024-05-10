package org.d3if3068.assesment2.daundiary.ui.screen

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3068.assesment2.daundiary.R
import org.d3if3068.assesment2.daundiary.database.BukuDb
import org.d3if3068.assesment2.daundiary.model.InputViewModel
import org.d3if3068.assesment2.daundiary.navigation.Screen
import org.d3if3068.assesment2.daundiary.ui.theme.DarkPrimary
import org.d3if3068.assesment2.daundiary.ui.theme.DaunDiaryTheme
import org.d3if3068.assesment2.daundiary.ui.theme.LightPrimary
import org.d3if3068.assesment2.daundiary.util.SettingsDataStore
import org.d3if3068.assesment2.daundiary.util.ViewModelFactory
import org.d3if3068.assesment2.daundiary.widgets.KonfirmasiHapus
import java.text.SimpleDateFormat
import java.util.Locale

const val KEY_ID_BUKU = "idBuku"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id: Int? = null) {
    val dataStore = SettingsDataStore(LocalContext.current)
    val isLight by dataStore.layoutFlow.collectAsState(initial = true)

    val context = LocalContext.current
    val db = BukuDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel: InputViewModel = viewModel(factory = factory)

    var judul by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }
    var pengarang by remember { mutableStateOf("") }
    var tanngalBuat by remember { mutableLongStateOf(0) }
    var tanngalEdit by remember { mutableLongStateOf(0) }
    var warnaBuku by remember { mutableStateOf(Color.Unspecified) }

    LaunchedEffect(true) {
        if (id == null) return@LaunchedEffect
        val data = viewModel.getBook(id) ?: return@LaunchedEffect
        judul = data.judul
        deskripsi = data.deskripsi
        pengarang = data.pengarang
        warnaBuku = Color(data.warnaBuku)
        tanngalBuat = data.tanggalBuat
        tanngalEdit = data.tanggalDiUbah
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
                }
            )
        },
    ) { padding ->
        DetailContent(
            dataJudul = judul,
            dataDeskripsi = deskripsi,
            dataPengarang = pengarang,
            dataWarna = warnaBuku,
            tanggalBuat = tanngalBuat,
            tanggalEdit = tanngalEdit,
            modifier = Modifier.padding(padding),
            idNya = id,
            viewModel = viewModel,
            navController = navController,
            isLight
        )
    }
}

@Composable
fun DetailContent(
    dataJudul: String,
    dataDeskripsi: String,
    dataPengarang: String,
    dataWarna: Color,
    tanggalBuat: Long,
    tanggalEdit: Long,
    modifier: Modifier,
    idNya: Int?,
    viewModel: InputViewModel,
    navController: NavHostController,
    isLight: Boolean
) {
    val dateFormat = remember { SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {
        TampilanBuku(
            dataJudul,
            dataDeskripsi,
            dataPengarang,
            dataWarna,
            id = idNya,
            viewModel = viewModel,
            navController = navController
        )

        Button(
            modifier = Modifier
                .padding(top = 58.dp)
                .width(267.dp),
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(if (isLight) LightPrimary else DarkPrimary)
        ) {
            Text(
                text = "Buka",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(text = "dibuat pada tanggal", fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
            Spacer(modifier = Modifier.width(33.dp))
            Text(text = ": ${dateFormat.format(tanggalBuat)}", fontWeight = FontWeight.Medium, fontSize = 20.sp)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "di update pada tanggal :",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
            Text(text = " ${dateFormat.format(tanggalEdit)}", fontWeight = FontWeight.Medium, fontSize = 20.sp)
        }
    }
}

@Composable
fun TampilanBuku(
    judul: String,
    deskripsi: String,
    pengarang: String,
    warna: Color,
    id: Int?,
    viewModel: InputViewModel,
    navController: NavHostController
) {
    var showDialog by remember {
        mutableStateOf(false)
    }

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
                .height(320.dp)
                .width(250.dp)
                .offset(x = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (id != null) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .padding(top = 24.dp, end = 18.dp)
                        .fillMaxWidth()
                        .size(33.dp)
                ) {
                    if (id != null) {
                        Menu(update = { navController.navigate(Screen.FormUbah.withId(id)) }, delete = { showDialog = true })
                        KonfirmasiHapus(
                            openDialog = showDialog,
                            onDismissRequest = { showDialog = false }) {
                            showDialog = false
                            viewModel.delete(id)
                            navController.popBackStack()
                        }
                    }
                }
                KonfirmasiHapus(
                    openDialog = showDialog,
                    onDismissRequest = { showDialog = false }) {
                    showDialog = false
                    viewModel.delete(id)
                    navController.popBackStack()
                }
            }
            Text(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .height(95.dp),
                textAlign = TextAlign.Center,
                text = judul,
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Text(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 5.dp, start = 15.dp, end = 15.dp)
                    .height(105.dp),
                textAlign = TextAlign.Center,
                text = "\"${deskripsi}\"",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(bottom = 24.dp, start = 15.dp, end = 15.dp),
                textAlign = TextAlign.Center,
                text = pengarang,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
    }
}

@Composable
fun Menu(update: () -> Unit, delete: () -> Unit) {
    var expended by remember {
        mutableStateOf(false)
    }

    IconButton(onClick = { expended = true }) {
        Icon(
            modifier = Modifier.size(33.dp),
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(R.string.lainnya),
            tint = Color.White
        )
        DropdownMenu(
            expanded = expended,
            onDismissRequest = { expended = false })
        {
            DropdownMenuItem(
                text = { Text(text = "Update") },
                onClick = {
                    expended = false
                    update()
                })
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.hapus)) },
                onClick = {
                    expended = false
                    delete()
                })
        }
    }
}

//@Preview
//@Composable
//fun TampilanBukuPreview() {
//    DaunDiaryTheme {
//        val context = LocalContext.current
//        val db = BukuDb.getInstance(context)
//        val bukuDao = db.dao
//
//        TampilanBuku(
//            judul = "28oooooooooooooooooooooooooook",
//            deskripsi = "“110aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaal”",
//            pengarang = "20yyyyyyyyyyyyyyyyyyyyyya",
//            warna = LightPrimary,
//            id = 1,
//            viewModel = InputViewModel(dao = bukuDao),
//            navController = rememberNavController()
//        )
//    }
//}

@Preview
@Composable
fun DetailPrev() {
    DaunDiaryTheme {
        DetailScreen(rememberNavController())
    }
}