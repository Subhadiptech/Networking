package com.ersubhadip.networking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ApiAdapter(var list:List<ApiDataModel>):RecyclerView.Adapter<ApiAdapter.DataHolder>() {

    inner class DataHolder(items:View):RecyclerView.ViewHolder(items){
        //fid
        val text:TextView = items.findViewById(R.id.tvitem)
        fun setData(title:String){
            text.setText(title)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return DataHolder(view)

    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        holder.setData(list[position].title)
    }

    override fun getItemCount(): Int {
        return list.size
    }


}