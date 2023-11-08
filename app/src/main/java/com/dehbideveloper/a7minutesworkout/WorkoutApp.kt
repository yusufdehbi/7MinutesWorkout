package com.dehbideveloper.a7minutesworkout

import android.app.Application

class WorkoutApp: Application() {
    val db by lazy {
        HistoryDatabase.getInstance(this)
    }
}