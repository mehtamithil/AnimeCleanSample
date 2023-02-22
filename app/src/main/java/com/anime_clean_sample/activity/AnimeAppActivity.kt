package com.anime_clean_sample.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anime_clean_sample.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimeAppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}