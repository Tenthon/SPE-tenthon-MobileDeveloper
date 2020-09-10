package com.morbis.spe.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.morbis.spe.R
import kotlinx.android.synthetic.main.toolbar.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        btnBack.setOnClickListener { finish() }
        textTitleActionbar.setText("Profil")
    }
}