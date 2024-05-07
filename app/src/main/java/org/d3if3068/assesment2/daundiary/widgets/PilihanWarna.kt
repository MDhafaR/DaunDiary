package org.d3if3068.assesment2.daundiary.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun PilihanWarna(
    warna: Color,
    isSelected: Boolean,
    perubahanWarna: (Color) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .border(
                2.dp,
                if (isSelected) Color.Green else Color.Transparent,
                RoundedCornerShape(17.dp)
            )
            .width(74.dp)
            .height(74.dp)
    ) {
        Box {
            Column(
                modifier = Modifier
                    .selectable(
                        selected = false,
                        onClick = {perubahanWarna(warna)},
                        role = Role.RadioButton
                    )
                    .width(62.dp)
                    .height(62.dp)
                    .clip(RoundedCornerShape(17.dp))
                    .background(warna)
            ) {
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun warnaPrev() {
//    DaunDiaryTheme {
//        PilihanWarna(
//            LightPrimary,
//            true,
//
//        )
//    }
//}