package org.d3if3068.assesment2.daundiary.widgets

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

@Composable
fun KonfirmasiHapus(
    openDialog: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = {
                Text(text = "Apakah anda yakin ingin menghapus data ini?", textAlign = TextAlign.Center)
            },
            confirmButton = {
                TextButton(onClick = onConfirmation) {
                    Text(text = "Hapus")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissRequest) {
                    Text(text = "Batal")
                }
            }
        )
    }
}