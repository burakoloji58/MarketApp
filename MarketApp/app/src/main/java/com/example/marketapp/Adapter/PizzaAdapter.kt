package com.example.marketapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.marketapp.Activity.ShowDetailPizzaActivity
import com.example.marketapp.Domain.PizzaDomain
import com.example.marketapp.R

class PizzaAdapter(private val Mcontext: Context, private val CatagoryList : ArrayList<PizzaDomain>)
    :RecyclerView.Adapter<PizzaAdapter.PizzaAdapterViewHolder>(){

    inner class PizzaAdapterViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val catagoryInPic : ImageView
        val titleInCatagoryTxt : TextView
        val feeInCatagory : TextView
        val catagoryLayout : ConstraintLayout

        init {
            catagoryInPic = view.findViewById(R.id.catagoryInPic)
            titleInCatagoryTxt = view.findViewById(R.id.titleInCatagoryTxt)
            feeInCatagory = view.findViewById(R.id.feeInCatagory)
            catagoryLayout = view.findViewById(R.id.catagoryLayout)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaAdapterViewHolder {
        val view = LayoutInflater.from(Mcontext).inflate(R.layout.viewholder_in_catagory,parent,false)
        return PizzaAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return CatagoryList.size
    }

    override fun onBindViewHolder(holder: PizzaAdapterViewHolder, position: Int) {
        val catagory = CatagoryList[position]

        holder.titleInCatagoryTxt.text = catagory.title
        holder.feeInCatagory.text = catagory.fee.toString()
        holder.catagoryInPic.setImageResource(Mcontext.resources.getIdentifier(catagory.pic,"drawable",Mcontext.packageName))

        holder.catagoryLayout.setOnClickListener {
            val intent = Intent(Mcontext, ShowDetailPizzaActivity::class.java)
            intent.putExtra("PizzaInfo",catagory)
            Mcontext.startActivity(intent)
        }
    }

}