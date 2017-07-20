package com.konstantin.movieticket

import android.os.Parcel
import android.os.Parcelable
import java.util.*


/**
 * Author: konstantin
 * Date: 20.07.17.
 */
class Helper {

    companion object {
        fun generateCinemas() = listOf<ScheduleActivity.Cinema>(
                ScheduleActivity.Cinema("Cinestar Sony Center", "3 km"),
                ScheduleActivity.Cinema("CineStar CUBIX", "1.2 km"),
                ScheduleActivity.Cinema("Zoo Palast", "8 km"),
                ScheduleActivity.Cinema("Cineplex Titania Palast", "4.7 km")
        )


        fun getDateInTwoWeeks(startDate: Date): Date {
            val cal1 = Calendar.getInstance()
            cal1.time = startDate
            cal1.add(Calendar.DATE, 14)
            return cal1.time
        }

        fun getDates(startDate: Date, endDate: Date): List<Date> {
            val dates = ArrayList<Date>()

            val date1: Date? = startDate
            val date2: Date? = endDate

            val cal1 = Calendar.getInstance()
            cal1.time = date1


            val cal2 = Calendar.getInstance()
            cal2.time = date2

            while (!cal1.after(cal2)) {
                dates.add(cal1.getTime())
                cal1.add(Calendar.DATE, 1)
            }
            return dates
        }
    }

}

inline fun <reified T : Parcelable> createParcel(
        crossinline createFromParcel: (Parcel) -> T?): Parcelable.Creator<T> =
        object : Parcelable.Creator<T> {
            override fun createFromParcel(source: Parcel): T? = createFromParcel(source)
            override fun newArray(size: Int): Array<out T?> = arrayOfNulls(size)
        }
