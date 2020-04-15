package com.kotlinpractice.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.rangeTo
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectBirthDateBtn.setOnClickListener{
            clickDatePicker(it)
        }
    }
    private fun clickDatePicker (view : View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

       val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->

                    val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                    selectedDateTv.text = selectedDate
                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                    val theDate = sdf.parse(selectedDate)

                    val selectedDateInMinutes = theDate!!.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    val currentDateToMinutes = currentDate!!.time / 60000
                    val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes

                    val selectedDateInDays =theDate!!.time/24/60/60000
                    val currentDateInDays =currentDate!!.time/24/60/60000
                    val differenceInDays = currentDateInDays - selectedDateInDays
                    ageInMinutesTv.text = differenceInMinutes.toString()
                    ageInDaysTv.text = differenceInDays.toString()
        }
            ,year
            ,month
            ,day)
        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }

}

