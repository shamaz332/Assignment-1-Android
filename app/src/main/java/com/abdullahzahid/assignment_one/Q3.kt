package com.abdullahzahid.assignment_one

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Q3 : AppCompatActivity() {
    private lateinit var sensorManager: SensorManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_q3)

        // Initialize the variable sensorManager
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // getSensorList(Sensor.TYPE_ALL) lists all the sensors present in the device
        val deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)

        // Text View that shall display this list
        val textView = findViewById<TextView>(R.id.tv)

        // Converting List to String and displaying
        // every sensor and its information on a new line
        for (sensors in deviceSensors) {
            textView.append(sensors.name.toString() + "\n\n")
        }
    }
}