package com.technokratos.itisapp.user.list

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skyhope.eventcalenderlibrary.CalenderEvent
import com.skyhope.eventcalenderlibrary.model.Event
import com.technokratos.itisapp.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject lateinit var userListViewModel: UserListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val event = Event(0, "Test")
// to set desire day time milli second in first parameter
//or you set color for each event
// to set desire day time milli second in first parameter
//or you set color for each event


//        calendarEvent.setOnClickListener {
//            val event = Event(0, "Test", Color.RED)
//            calendarEvent.addEvent(event)
//        }



//        (application as App).appComponent.userListComponentFactory()
//            .create(this)
//            .inject(this)

//        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
//            val selectedDate = StringBuilder().append(month + 1)
//                .append("-").append(dayOfMonth).append("-").append(year)
//                .append(" ").toString()
//            Toast.makeText(applicationContext, selectedDate, Toast.LENGTH_LONG).show()
//        }


//        calendarView.setDateFormat("yyyy MMM")
//        calendarView.setPreventPreviousDate(true)
//        calendarView.setErrToastMessage("You can not select the previous date.")
////        calendarView.setOnDaySelectedListener { startDay, endDay ->
////            textView.text = startDay + " " + endDay
////        }
//        calendarView.buildCalendar()

//        calendar.setCalendarListener(object : CalendarListener {
//            override fun onFirstDateSelected(startDate: Calendar) {
//                Toast.makeText(
//                    this@MainActivity,
//                    "Start Date: " + startDate.time.toString(),
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//
//            override fun onDateRangeSelected(startDate: Calendar, endDate: Calendar) {
//                Toast.makeText(
//                    this@MainActivity,
//                    "Start Date: " + startDate.time.toString() + " End date: " + endDate.time.toString(),
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        })

//        val today = Date()
//        val nextYear: Calendar = Calendar.getInstance()
//        nextYear.add(Calendar.YEAR, 1)
//        val datePicker = calendar
//        datePicker.init(today, nextYear.getTime())
//            .inMode(CalendarPickerView.SelectionMode.RANGE)
//            .withSelectedDate(today)
//
//        datePicker.setOnDateSelectedListener(object : OnDateSelectedListener {
//            override fun onDateSelected(date: Date?) {
//                //String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(date);
//                val calSelected: Calendar = Calendar.getInstance()
//                calSelected.setTime(date)
//                val selectedDate = "" + calSelected.get(Calendar.DAY_OF_MONTH)
//                    .toString() + " " + (calSelected.get(Calendar.MONTH) + 1)
//                    .toString() + " " + calSelected.get(Calendar.YEAR)
//                Toast.makeText(this@MainActivity, selectedDate, Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onDateUnselected(date: Date?) {}
//        })
    }
}