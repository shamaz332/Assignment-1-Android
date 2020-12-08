package com.abdullahzahid.assignment_one

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.CalendarContract
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Toast

class Q2 : AppCompatActivity() {
    val REQUEST_CONTACT=1
    var contactGot=0
    lateinit var contactUri: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_q2)


    }

    fun createNewAlarm(view: View) {
//        Log.d("createNewAlarm","createNewAlarm mess")
        val intent = Intent(AlarmClock.ACTION_SET_ALARM)
        intent.putExtra(AlarmClock.EXTRA_MESSAGE,"Test Alarm")
        intent.putExtra(AlarmClock.EXTRA_HOUR,19)
        intent.putExtra(AlarmClock.EXTRA_MINUTES,0)
        intent.putExtra(AlarmClock.EXTRA_DAYS, arrayListOf(2,3,4))

        startActivity(intent)
    }

    fun createCountDownTimer(view: View) {
        val intent = Intent(AlarmClock.ACTION_SET_TIMER)
        intent.putExtra(AlarmClock.EXTRA_MESSAGE,"Test Timer")
        intent.putExtra(AlarmClock.EXTRA_LENGTH,10)
        intent.putExtra(AlarmClock.EXTRA_SKIP_UI,false)

        startActivity(intent)
    }

    fun showListOfAlarm(view: View) {
        val intent = Intent(AlarmClock.ACTION_SHOW_ALARMS)
        startActivity(intent)
    }

    fun addNewEventCalender(view: View) {
        val intent = Intent(Intent.ACTION_INSERT)
            .setData(CalendarContract.Events.CONTENT_URI)
            .putExtra(CalendarContract.Events.TITLE,"Test Event")
            .putExtra(CalendarContract.Events.EVENT_LOCATION,"Here")
            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,System.currentTimeMillis())
            .putExtra(CalendarContract.EXTRA_EVENT_END_TIME,System.currentTimeMillis()+(60*60*1000))

        startActivity(intent)
    }

    fun selectContact(view: View) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = ContactsContract.Contacts.CONTENT_TYPE
        if(intent.resolveActivity(packageManager) != null)
        {
            startActivityForResult(intent,REQUEST_CONTACT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==REQUEST_CONTACT && resultCode== Activity.RESULT_OK)
        {
            contactUri = data!!.data!!
            contactGot=1

        }
    }

    fun viewContact(view: View) {
        if (contactGot == 1){
            val intent = Intent(Intent.ACTION_VIEW,contactUri)
            if(intent.resolveActivity(packageManager) != null)
            {
                startActivity(intent)
            }
        }
        else
        {
            val toast = Toast.makeText(this, "No Contact Selected", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    fun insertContact(view: View) {
        val intent = Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI).apply {
            type = ContactsContract.RawContacts.CONTENT_TYPE
            putExtra(ContactsContract.Intents.Insert.NAME,
                "Test")
            putExtra(ContactsContract.Intents.Insert.PHONE,
                "3007558091")
        }
        startActivity(intent)
    }

}