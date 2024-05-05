package org.d3if3068.assesment2.daundiary.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.d3if3068.assesment2.daundiary.model.Warna
import org.d3if3068.assesment2.daundiary.ui.theme.DaunDiaryTheme
import org.d3if3068.assesment2.daundiary.ui.theme.LightPrimary

@Composable
fun PilihanWarna(item: Warna) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .border(
                1.dp,
                if (item.isSelected) Color.Cyan else Color.Transparent,
                RoundedCornerShape(17.dp))
            .width(74.dp)
            .height(74.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable { item.isSelected = true }
                .width(62.dp)
                .height(62.dp)
                .clip(RoundedCornerShape(17.dp))
                .background(item.warna)
        ) {

        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun warnaPrev() {
//    DaunDiaryTheme {
//        PilihanWarna()
//    }
//}