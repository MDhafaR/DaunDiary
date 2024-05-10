package org.d3if3068.assesment2.daundiary.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import org.d3if3068.assesment2.daundiary.R
import org.d3if3068.assesment2.daundiary.database.BukuDb
import org.d3if3068.assesment2.daundiary.model.InputViewModel
import org.d3if3068.assesment2.daundiary.ui.theme.DarkPrimary
import org.d3if3068.assesment2.daundiary.ui.theme.LightPrimary
import org.d3if3068.assesment2.daundiary.util.SettingsDataStore
import org.d3if3068.assesment2.daundiary.util.ViewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IsiBuku(
    judul: String,
    deskripsi: String,
    pengarang: String,
    warna: Int,
    navController: NavHostController,
    id: Int? = null
) {

    val dataStore = SettingsDataStore(LocalContext.current)
    val isLight by dataStore.layoutFlow.collectAsState(initial = true)

    val context = LocalContext.current
    val db = BukuDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel: InputViewModel = viewModel(factory = factory)

    var isi by remember { mutableStateOf("") }

    var edit by remember {
        mutableStateOf(false)
    }

    val tanggalSaatIni: Long = System.currentTimeMillis()

    LaunchedEffect(true) {
        if (id == null) return@LaunchedEffect
        val data = viewModel.getBook(id) ?: return@LaunchedEffect
        isi = data.isi
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
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            modifier = Modifier.size(35.dp),
                            tint = Color.White,
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali)
                        )
                    }
                },
                actions = {
                    if (id == null) {
                        IconButton(
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(40.dp),
                            onClick = {

                                viewModel.insert(
                                    judul,
                                    deskripsi,
                                    pengarang,
                                    Color(warna),
                                    tanggalSaatIni,
                                    tanggalSaatIni,
                                    isi
                                )
                                navController.popBackStack(
                                    navController.graph.startDestinationId,
                                    false
                                )
                            }) {
                            Icon(
                                modifier = Modifier.size(35.dp),
                                tint = Color.White,
                                painter = painterResource(id = R.drawable.saved),
                                contentDescription = "save Isi"
                            )
                        }
                    } else if (edit) {
                        IconButton(
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(40.dp),
                            onClick = {

                                viewModel.updateIsi(isi, id)
                                navController.popBackStack(
                                    navController.graph.startDestinationId,
                                    false
                                )
                            }) {
                            Icon(
                                modifier = Modifier.size(35.dp),
                                tint = Color.White,
                                painter = painterResource(id = R.drawable.saved),
                                contentDescription = "save Isi"
                            )
                        }
                    }
                    else {
                        IconButton(
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(40.dp),
                            onClick = {
                                    edit = true
                            }) {
                            Icon(
                                modifier = Modifier.size(35.dp),
                                tint = Color.White,
                                painter = painterResource(id = R.drawable.edited),
                                contentDescription = "edit isi"
                            )
                        }
                    }
                }
            )
        },
    ) { padding ->
        IsiContent(
            modifier = Modifier.padding(padding),
            dataIsi = isi,
            onIsiChange = { isi = it },
            id = id,
            edited = edit
        )
    }
}

@Composable
fun IsiContent(
    modifier: Modifier,
    dataIsi: String,
    onIsiChange: (String) -> Unit,
    id: Int?,
    edited : Boolean
) {
    if (id == null || edited) {
        OutlinedTextField(
            value = dataIsi,
            onValueChange = { onIsiChange(it) },
            modifier = modifier
                .padding(16.dp)
                .fillMaxSize(),
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            ),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
            )
        )
    } else {
        Text(
            modifier = modifier
                .padding(16.dp)
                .fillMaxSize(),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            text = dataIsi
        )
    }

}


//@Preview
//@Composable
//fun IsiPrev() {
//    DaunDiaryTheme {
//        IsiBuku(navController = rememberNavController())
//    }
//}


