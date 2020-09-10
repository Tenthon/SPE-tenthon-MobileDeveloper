package com.morbis.spe.view.activity

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.bumptech.glide.Glide
import com.morbis.spe.R
import com.morbis.spe.data.remote.baseImage
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.toolbar.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        btnBack.setOnClickListener { finish() }
        textTitleActionbar.setText("Detail Informasi")
        textNamaDokter.text = "${intent.getStringExtra("nama")}"
        textItemJam.text = "${intent.getStringExtra("praktek")}"
        textItemKehadiran.text = "${intent.getStringExtra("hadir")}"
        textItemPoli.text = "${intent.getStringExtra("poli")}"

        Glide.with(this).load("$baseImage${intent.getStringExtra("foto")}")
            .placeholder(R.drawable.ic_dokter_praktik).into(imgDokterfull)
    }
}