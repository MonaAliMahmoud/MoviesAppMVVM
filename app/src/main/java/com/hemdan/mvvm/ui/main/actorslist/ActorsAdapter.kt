package com.hemdan.mvvm.ui.main.actorslist

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hemdan.mvvm.R
import com.hemdan.mvvm.data.model.PopularInfo
import java.util.ArrayList

class ActorsAdapter(private val info: ArrayList<PopularInfo>, private var context: Context)
    : RecyclerView.Adapter<ActorsAdapter.ListViewHolder>() {

    private var inflater: LayoutInflater? = null
    private var popularInfo: PopularInfo? = null
    private var imgPath = "https://image.tmdb.org/t/p/w500/"
    private var popImg: ImageView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        inflater = LayoutInflater.from(parent.context)
        val listItem = inflater!!.inflate(R.layout.list_item, parent, false)
        return ListViewHolder(listItem)    }

    override fun getItemCount(): Int {
        return info.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        popularInfo = info[position]
        holder.popName.text = popularInfo!!.name
        holder.popDepart.text = popularInfo!!.known_for_department

        Glide.with(this.context)
            .load(imgPath + popularInfo!!.profile_path)
            .placeholder(R.drawable.ic_launcher_background)
            .into(popImg!!)

        holder.bindData(popularInfo!!)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var popName: TextView
        internal var popDepart: TextView

        init {
            popImg = itemView.findViewById<View>(R.id.imgActor) as ImageView
            this.popName = itemView.findViewById<View>(R.id.txtActorName) as TextView
            Log.i("Name", popName.toString())
            popDepart = itemView.findViewById<View>(R.id.txtActorDepart) as TextView
        }

        fun bindData(popularInf: PopularInfo) {
            itemView.setOnClickListener {
            }
        }
    }
}