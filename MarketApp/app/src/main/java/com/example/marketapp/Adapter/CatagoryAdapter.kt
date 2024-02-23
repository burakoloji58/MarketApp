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
import com.example.marketapp.Activity.PizzaActivity
import com.example.marketapp.Domain.CatagoryDomain
import com.example.marketapp.R

class CatagoryAdapter(private val mContext : Context ,private val CatagoryList:List<CatagoryDomain>)
    :RecyclerView.Adapter<CatagoryAdapter.CatagoryTasarimTutucu>() {

    inner class CatagoryTasarimTutucu(Tasarim:View): RecyclerView.ViewHolder(Tasarim){

        val catagoryName : TextView
        val catagoryPic : ImageView
        val catagoryCard : ConstraintLayout

        init {
            catagoryName = Tasarim.findViewById(R.id.catagoryName)
            catagoryPic = Tasarim.findViewById(R.id.catagoryPic)
            catagoryCard = Tasarim.findViewById(R.id.catagoryCard)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatagoryTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.viewholder_catagory,parent,false)
        return CatagoryTasarimTutucu(tasarim)
    }

    override fun getItemCount(): Int {
        return CatagoryList.size
    }

    override fun onBindViewHolder(holder: CatagoryTasarimTutucu, position: Int) {
        val catagory = CatagoryList.get(position)

        holder.catagoryName.text=catagory.title

        var picUrl = ""

        when(position){
            0-> {
                picUrl ="cat_1"
                holder.catagoryCard.setBackgroundResource(R.drawable.cat_background)

                holder.catagoryCard.setOnClickListener{
                    val intent = Intent(mContext, PizzaActivity::class.java)
                    mContext.startActivity(intent)
                }
            }
            1-> {
                picUrl ="cat_2"
                holder.catagoryCard.setBackgroundResource(R.drawable.cat_background)
            }
            2-> {
                picUrl ="cat_3"
                holder.catagoryCard.setBackgroundResource(R.drawable.cat_background)
            }
            3-> {
                picUrl ="cat_4"
                holder.catagoryCard.setBackgroundResource(R.drawable.cat_background)
            }
            4-> {
                picUrl ="cat_5"
                holder.catagoryCard.setBackgroundResource(R.drawable.cat_background)
            }
        }

        holder.catagoryPic.setImageResource(mContext.resources.getIdentifier(picUrl,"drawable",mContext.packageName))

    /*
        val drawableResId = mContext.resIdByName(picUrl, "drawable")
        Glide.with(mContext).load(drawableResId).into(holder.catagoryPic)
    */
    }

}