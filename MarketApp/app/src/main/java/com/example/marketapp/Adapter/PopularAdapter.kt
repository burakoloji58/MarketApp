package com.example.marketapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marketapp.Activity.ShowDetailActivity
import com.example.marketapp.Domain.FoodDomain
import com.example.marketapp.R

class PopularAdapter(private val mContext : Context, private val FoodList:ArrayList<FoodDomain>)
    :RecyclerView.Adapter<PopularAdapter.PopularAdapterTutucu>() {

    inner class PopularAdapterTutucu(Tasarim:View): RecyclerView.ViewHolder(Tasarim){

        val title : TextView
        val fee : TextView
        val addBtn : TextView
        val pic : ImageView

        init {
            title = Tasarim.findViewById(R.id.title)
            fee = Tasarim.findViewById(R.id.fee)
            addBtn = Tasarim.findViewById(R.id.addBtn)
            pic = Tasarim.findViewById(R.id.pic)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapterTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.viewholder_popular,parent,false)
        return PopularAdapterTutucu(tasarim)
    }

    override fun getItemCount(): Int {
        return FoodList.size
    }

    override fun onBindViewHolder(holder: PopularAdapterTutucu, position: Int) {
        val food = FoodList[position]

        holder.title.text=food.title
        holder.fee.text=food.fee.toString()
        holder.pic.setImageResource(mContext.resources.getIdentifier(food.pic,"drawable",mContext.packageName))


        holder.addBtn.setOnClickListener{
            val intent = Intent(mContext,ShowDetailActivity::class.java)
            intent.putExtra("Fobject",food)
            mContext.startActivity(intent)
        }

    /*
        val drawableResId = mContext.resIdByName(picUrl, "drawable")
        Glide.with(mContext).load(drawableResId).into(holder.catagoryPic)
    */
    }

}