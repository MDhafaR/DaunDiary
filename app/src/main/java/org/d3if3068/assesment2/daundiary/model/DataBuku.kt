package org.d3if3068.assesment2.daundiary.model

import androidx.compose.ui.graphics.Color
import org.d3if3068.assesment2.daundiary.ui.theme.MerahMuda
import org.d3if3068.assesment2.daundiary.ui.theme.Orange

data class DataBuku(
    val id: Int,
    val judul: String,
    val deskripsi: String,
    val pengarang: String,
    val warnaBuku: Color
)

object DataDummy {
    val data = listOf(
        DataBuku(
            1,
            "Hujan",
            "Novel yang menceritakan tentang hujan hujan hujan hujan hujan hujan hujan hujan hujan hujan",
            "Tere Liye",
            Color.Black
        ),
        DataBuku(2, "Bumi", "Novel tentang kehidupan di bumi", "Tere Liye", Color.Blue),
        DataBuku(3, "Matahari", "Novel tentang perjalanan matahari", "Tere Liye", Color.Red),
        DataBuku(4, "Angin", "Novel tentang angin dan petualangannya", "Tere Liye", Color.Green),
        DataBuku(5, "Laut", "Novel yang mengangkat cerita laut", "Tere Liye", Color.Yellow),
        DataBuku(6, "Bintang", "Novel yang menceritakan tentang bintang", "Tere Liye", Color.Cyan),
        DataBuku(7, "Awan", "Novel tentang awan dan perjalannya di langit", "Tere Liye", Color.Magenta),
        DataBuku(8, "Api", "Novel yang mengisahkan tentang api", "Tere Liye", Color.Gray),
        DataBuku(9, "Tanah", "Novel tentang kehidupan di tanah", "Tere Liye", Orange),
        DataBuku(10, "Pohon", "Novel yang mengangkat kisah pohon", "Tere Liye", MerahMuda)
    )
}