package com.example.marketapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marketapp.Adapter.CartListAdapter
import com.example.marketapp.Domain.CartFoodDomain
import com.example.marketapp.databinding.ActivityCartListBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CartListActivity : AppCompatActivity() {
    private lateinit var ulas : ActivityCartListBinding
    private lateinit var CartList : ArrayList<CartFoodDomain>
    private lateinit var adapter : CartListAdapter
    private lateinit var refCatagory : DatabaseReference
    val db = FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        ulas = ActivityCartListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(ulas.root)


        ulas.recyclerViewList.setHasFixedSize(true)
        ulas.recyclerViewList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        CartList = ArrayList()

        adapter = CartListAdapter(this, CartList)
        ulas.recyclerViewList.adapter = adapter

        getAllFoodToCart()

        ulas.homeBtn.setOnClickListener {
            startActivity(Intent(this@CartListActivity,MainActivity::class.java))
            onBackPressed()
        }

    }

    fun formattedNumber(number: Double): String{
        val formattedNumber = String.format("%.2f", number)
        val split = formattedNumber.split(".");
        val str = StringBuilder(split[1])
        return "${split[0]}.${str}"
    }

    fun getAllFoodToCart() {
        val refCart = db.getReference("Cart")

        refCart.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(ds: DataSnapshot) {
                CartList.clear()
                var totalFee =0.0
                val delivery = 10.0
                val tax = 2.0
                for (c in ds.children){
                    val cart = c.getValue(CartFoodDomain::class.java)

                    if (cart != null){
                        cart.id = c.key
                        CartList.add(cart)

                        totalFee = totalFee + (cart.fee!! * cart.numberInCart!!)
                        totalFee = formattedNumber(totalFee).toDouble()
                    }
                }
                adapter.notifyDataSetChanged()

                ulas.totalFeeTxt.text ="$${totalFee}"
                ulas.deliveryTxt.text= "$${delivery}"
                ulas.taxTxt.text = "$${tax}"
                val price = totalFee+delivery+tax
                ulas.totalTxt.text="$${formattedNumber(price)}"
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }

}