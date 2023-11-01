package com.dehbideveloper.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dehbideveloper.a7minutesworkout.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    var binding: ActivityHistoryBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarHistoryActivity)
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        supportActionBar?.title = "History"
        binding?.toolbarHistoryActivity?.setNavigationOnClickListener {
            onBackPressed()
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}