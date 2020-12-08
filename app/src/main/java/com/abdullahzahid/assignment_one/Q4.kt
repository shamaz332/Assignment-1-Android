package com.abdullahzahid.assignment_one

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class Q4 : AppCompatActivity(), SensorEventListener {
    private lateinit var mSensorManager : SensorManager
    private var mAccelerometer : Sensor ?= null
    private var resume = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_q4)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        this.resume = true

        findViewById<TextView>(R.id.sensor_value).text = "portrait"


    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val imageView = findViewById<ImageView>(R.id.imageView2)

        this.resume = true
        if (event != null && resume) {
            if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                var pVal = 9.81
                var nVal = -9.81
                if(event.values[0] == pVal.toFloat() || event.values[0] == nVal.toFloat())
                {

                    findViewById<TextView>(R.id.sensor_value).text = "landscape"
                    imageView.setBackgroundResource(R.drawable.image1)
                }
                else
                {
//                    findViewById<TextView>(R.id.sensor_value).text = event.values[0].toString()
                    findViewById<TextView>(R.id.sensor_value).text = "portrait"
                    imageView.setBackgroundResource(R.drawable.image2)
                }

            }
        }
    }

    override fun onResume() {
        super.onResume()
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        mSensorManager.unregisterListener(this)
    }


}