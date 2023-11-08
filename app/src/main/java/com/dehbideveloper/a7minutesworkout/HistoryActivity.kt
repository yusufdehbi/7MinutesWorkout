package com.dehbideveloper.a7minutesworkout

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.dehbideveloper.a7minutesworkout.databinding.ActivityHistoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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

        val dao = (application as WorkoutApp).db.historyDao()
        getAllCompletedDates(dao)
    }


    private fun getAllCompletedDates(historyDao: HistoryDao){
        lifecycleScope.launch {
            historyDao.fetchAllDates().collect{datesList ->
                for (i in datesList){
                    Log.e("Date: ", ""+i)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}