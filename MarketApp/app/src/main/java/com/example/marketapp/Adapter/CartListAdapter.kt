package com.example.marketapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marketapp.Domain.CartFoodDomain
import com.example.marketapp.Helper.CartFoodDomainDao
import com.example.marketapp.R

class CartListAdapter(private val Mcontext: Context,private val cartFoodList: ArrayList<CartFoodDomain>)
    : RecyclerView.Adapter<CartListAdapter.CartViewHolder>() {

    inner class CartViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val picCart : ImageView
        val titleTxt1 : TextView
        val minusCartBtn : ImageView
        val numberItemTxt : TextView
        val plusCardBtn : ImageView
        val feeEachItem : TextView
        val totalEachItem : TextView

        init {
            picCart = view.findViewById(R.id.picCart)
            titleTxt1 = view.findViewById(R.id.titleTxt1)
            minusCartBtn = view.findViewById(R.id.minusCartBtn)
            numberItemTxt = view.findViewById(R.id.numberItemTxt)
            plusCardBtn = view.findViewById(R.id.plusCardBtn)
            feeEachItem = view.findViewById(R.id.feeEachItem)
            totalEachItem = view.findViewById(R.id.totalEachItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(Mcontext).inflate(R.layout.viewholder_cart,parent,false)
        return CartViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cartFoodList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {

        val cartFood = cartFoodList[position]

        if (cartFood.numberInCart==0){
            CartFoodDomainDao().deleteFoodToCart(cartFood.id.toString())
        }

        holder.titleTxt1.setText(cartFood.title)
        holder.numberItemTxt.setText(cartFood.numberInCart.toString())
        holder.picCart.setImageResource(Mcontext.resources.getIdentifier(cartFood.pic,"drawable",Mcontext.packageName))
        holder.feeEachItem.setText(cartFood.fee.toString())

        val total = formattedNumber(cartFood.fee!! * cartFood.numberInCart!!)
        holder.totalEachItem.setText(total) // $x.xx

        holder.plusCardBtn.setOnClickListener {
            cartFood.numberInCart = cartFood.numberInCart!! + 1
            CartFoodDomainDao().updateFoodToCart(cartFood.id.toString(),
                cartFood.title.toString(),
                cartFood.pic.toString(),
                cartFood.description.toString(),
                cartFood.fee!!,
                cartFood.numberInCart!!)

            holder.numberItemTxt.setText(cartFood.numberInCart.toString())
        }

        holder.minusCartBtn.setOnClickListener {
            if (cartFood.numberInCart!! >0)
            {
                cartFood.numberInCart = cartFood.numberInCart!! - 1
                CartFoodDomainDao().updateFoodToCart(cartFood.id.toString(),
                    cartFood.title.toString(),
                    cartFood.pic.toString(),
                    cartFood.description.toString(),
                    cartFood.fee!!,
                    cartFood.numberInCart!!)
            }
            holder.numberItemTxt.setText(cartFood.numberInCart.toString())
        }

    }

    fun formattedNumber(number: Double): String{
        val formattedNumber = String.format("%.2f", number)
        val split = formattedNumber.split(".");
        val str = StringBuilder(split[1])
        return "${split[0]}.${str}"
    }
}