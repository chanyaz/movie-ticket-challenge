package com.konstantin.movieticket

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setActionBar(toolbar)
        actionBar.setDisplayShowTitleEnabled(false)


        val list = getFakeData()
        val moviesAdapter = MoviesAdapter(list)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.run {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = moviesAdapter
            val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            decoration.setDrawable(resources.getDrawable(R.drawable.divider, theme))
            addItemDecoration(decoration)
        }
    }

    private fun getFakeData() = listOf(
            Movie(1, "Wonder Woman", 7.5f),
            Movie(2, "Spider-man Homecoming", 8.1f),
            Movie(3, "Justice League", 7.7f),
            Movie(4, "X-Men: Apocalypse", 9.5f),
            Movie(5, "Steel man", 6.2f)
    )

    class MoviesVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgPoster: ImageView by lazy { itemView.findViewById<ImageView>(R.id.img_movie_poster) }
        val txtTitle: TextView by lazy { itemView.findViewById<TextView>(R.id.txt_movie_title) }
        val txtRating: TextView by lazy { itemView.findViewById<TextView>(R.id.txt_movie_rating) }

        fun bind(movie: Movie) {
            imgPoster.setImageResource(R.drawable.poster_ww)
            txtTitle.text = movie.name
            txtRating.text = movie.rating.toString()
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, ScheduleActivity::class.java)
                intent.putExtra(ScheduleActivity.BUNDLE_MOVIE, movie)
                context.startActivity(intent)
            }
        }
    }

    class MoviesAdapter(val list: List<Movie>) : RecyclerView.Adapter<MoviesVH>() {
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MoviesVH {
            val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, parent, false)
            return MoviesVH(view)
        }

        override fun onBindViewHolder(holder: MoviesVH?, position: Int) {
            holder?.bind(list[position])
        }

        override fun getItemCount() = list.size
    }

    data class Movie(val id: Int, val name: String, val rating: Float) : Parcelable {
        // 1
        companion object {
            // 2
            @JvmField @Suppress("unused")
            val CREATOR = createParcel { Movie(it) } // 3
        }

        // 4
        protected constructor(parcelIn: Parcel) : this(parcelIn.readInt(), parcelIn.readString(), parcelIn.readFloat())

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeInt(id)
            dest.writeString(name)
            dest.writeFloat(rating)
        }

        override fun describeContents() = 0

    }
}
