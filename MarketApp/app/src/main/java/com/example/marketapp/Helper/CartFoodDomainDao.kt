package com.example.marketapp.Helper

import com.example.marketapp.Domain.CartFoodDomain
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CartFoodDomainDao {

    private lateinit var refCart : DatabaseReference
    val db = FirebaseDatabase.getInstance()

    fun insertFoodToCart(id:String ,title:String,pic: String,description:String,fee:Double,numberInCart:Int){
        val refCart = db.getReference("Cart")

        val cart = CartFoodDomain(id,title,pic,description,fee,numberInCart)
        refCart.push().setValue(cart)
    }

    fun deleteFoodToCart(id:String){
        val refCart = db.getReference("Cart")

        refCart.child(id).removeValue()
    }

    fun updateFoodToCart(id:String, title:String,pic: String,description:String,fee:Double,numberInCart:Int){
        val refCart = db.getReference("Cart")

        val info = HashMap<String,Any>()
        info.put("title",title)
        info.put("pic",pic)
        info.put("description",description)
        info.put("fee",fee)
        info.put("numberInCart",numberInCart)

        refCart.child(id).updateChildren(info)
    }

}