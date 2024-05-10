package org.d3if3068.assesment2.daundiary.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.d3if3068.assesment2.daundiary.R
import org.d3if3068.assesment2.daundiary.database.BukuDb
import org.d3if3068.assesment2.daundiary.model.InputViewModel
import org.d3if3068.assesment2.daundiary.model.MainViewModel
import org.d3if3068.assesment2.daundiary.navigation.Screen
import org.d3if3068.assesment2.daundiary.ui.theme.DarkPrimary
import org.d3if3068.assesment2.daundiary.ui.theme.DaunDiaryTheme
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
                    IconButton(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(40.dp),
                        onClick = {
                            if (id == null) {
                    viewModel.insert(
                        judul,
                        deskripsi,
                        pengarang,
                        Color(warna),
                        tanggalSaatIni,
                        tanggalSaatIni,
                        isi
                    )
                } else {

                }
                            navController.popBackStack(navController.graph.startDestinationId, false)
                        }) {
                        Icon(
                            modifier = Modifier.size(35.dp),
                            tint = Color.White,
                            painter = painterResource(id = R.drawable.saved),
                            contentDescription = "save Isi"
                        )
                    }
                }
            )
        },
    ) { padding ->
        IsiContent(
            modifier = Modifier.padding(padding),
            dataIsi = isi,
            onIsiChange = { isi = it },
            judul = judul,
            deskripsi = deskripsi,
            pengarang = pengarang
        )
    }
}

@Composable
fun IsiContent(
    modifier: Modifier,
    dataIsi: String,
    onIsiChange: (String) -> Unit,
    judul: String,
    deskripsi: String,
    pengarang: String
) {
    OutlinedTextField(
        value = dataIsi,
        onValueChange = { onIsiChange(it) },
        modifier = modifier
            .padding(top = 16.dp)
            .fillMaxSize(),
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        ),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )

}


//@Preview
//@Composable
//fun IsiPrev() {
//    DaunDiaryTheme {
//        IsiBuku(navController = rememberNavController())
//    }
//}


