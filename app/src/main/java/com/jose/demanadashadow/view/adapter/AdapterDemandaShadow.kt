package com.jose.demanadashadow.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jose.demanadashadow.R
import com.jose.demanadashadow.model.DemandaShadowModel
import kotlinx.android.synthetic.main.fragment_item_list.view.*

class AdapterDemandaShadow(
    private var list: List<DemandaShadowModel>
) : RecyclerView.Adapter<FragmentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item_list, parent, false)
        return FragmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: FragmentViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
       return list.size
    }
}

class FragmentViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView){

    val tvName: TextView =itemView.findViewById(R.id.tvName)
    val tvEmail: TextView = itemView.findViewById(R.id.tvEmail)

    fun bind(data:DemandaShadowModel){
        tvName.text = "Nome: " + data.name
        tvEmail.text = "E-mail: " + data.email
    }

}