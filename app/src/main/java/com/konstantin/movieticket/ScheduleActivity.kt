package com.konstantin.movieticket

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Author: konstantin
 * Date: 17.07.17.
 */
class ScheduleActivity : AppCompatActivity() {

    companion object {
        val BUNDLE_MOVIE = "movie"
    }

    val datesList: RecyclerView by lazy { findViewById<RecyclerView>(R.id.rv_dates) }
    val cinemasList: RecyclerView by lazy { findViewById<RecyclerView>(R.id.rv_showtimes) }
    val moviePoster: ImageView by lazy { findViewById<ImageView>(R.id.iv_movie_poster) }

    val datesAdapter: DatesAdapter by lazy { DatesAdapter() }
    val cinemaAdapter: CinemaAdapter by lazy { CinemaAdapter() }
    val movie by lazy { intent.getParcelableExtra<MainActivity.Movie>(BUNDLE_MOVIE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)


        datesAdapter.apply {
            setHasStableIds(true)
            val today = Date()
            list = Helper.getDates(today, Helper.getDateInTwoWeeks(today))
        }

        datesList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = datesAdapter
        }

        cinemaAdapter.apply {
            setHasStableIds(true)
            list = Helper.generateCinemas()
        }

        cinemasList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = cinemaAdapter
        }
    }

    class DatesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dayOfMonth: TextView by lazy { itemView.findViewById<TextView>(R.id.txt_date_day_of_month) }
        val dayOfWeek: TextView by lazy { itemView.findViewById<TextView>(R.id.txt_date_day_of_week) }

    }

    class DatesAdapter() : RecyclerView.Adapter<DatesViewHolder>() {

        var list: List<Date> = ArrayList()
        val calendar: Calendar by lazy { Calendar.getInstance() }
        val weekdayNameFormat = SimpleDateFormat("EEE", Locale.getDefault())


        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: DatesViewHolder?, position: Int) {
            val date = list[position]
            calendar.time = date
            holder?.dayOfMonth?.text = calendar[Calendar.DAY_OF_MONTH].toString()
            holder?.dayOfWeek?.text = weekdayNameFormat.format(date)
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DatesViewHolder {
            val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie_date, parent, false)
            return DatesViewHolder(view)
        }

    }

    class CinemaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    data class Cinema(val name: String, val distance: String)

    class CinemaAdapter() : RecyclerView.Adapter<CinemaViewHolder>() {
        var list: List<Cinema> = ArrayList()

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: CinemaViewHolder?, position: Int) {

        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CinemaViewHolder {
            val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_cinema_schedule, parent, false)
            return CinemaViewHolder(view)
        }

    }
}
