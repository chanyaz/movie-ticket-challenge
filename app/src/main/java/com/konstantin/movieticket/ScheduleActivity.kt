package com.konstantin.movieticket

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

/**
 * Author: konstantin
 * Date: 17.07.17.
 */
class ScheduleActivity : AppCompatActivity() {

    val datesList: RecyclerView by lazy { findViewById<RecyclerView>(R.id.rv_dates) }
    val showtimesList: RecyclerView by lazy { findViewById<RecyclerView>(R.id.rv_showtimes) }
    val moviePoster: ImageView by lazy { findViewById<ImageView>(R.id.iv_movie_poster) }

    val datesAdapter: DatesAdapter by lazy { DatesAdapter() }
    val showtimesAdapter: ShowtimesAdapter by lazy { ShowtimesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        datesList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = datesAdapter
        }

        showtimesList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = showtimesAdapter
        }
    }

    class DatesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    class DatesAdapter : RecyclerView.Adapter<DatesViewHolder>() {
        override fun getItemCount(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onBindViewHolder(holder: DatesViewHolder?, position: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DatesViewHolder {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    class ShowtimesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    class ShowtimesAdapter : RecyclerView.Adapter<DatesViewHolder>() {
        override fun getItemCount(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onBindViewHolder(holder: DatesViewHolder?, position: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DatesViewHolder {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}
