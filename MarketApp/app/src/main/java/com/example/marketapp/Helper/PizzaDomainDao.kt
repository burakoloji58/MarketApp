package com.example.marketapp.Helper

import com.example.marketapp.Domain.FoodDomain
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PizzaDomainDao {

    private lateinit var refPizza : DatabaseReference
    val db = FirebaseDatabase.getInstance()

    fun insertFood(title:String,pic: String,description:String,fee:Double,numberInCart:Int){
        val refPizza = db.getReference("Pizza")

        val food = FoodDomain("",title,pic,description,fee,numberInCart)
        refPizza.push().setValue(food)
    }

    fun updateFood(id:String, title:String,pic: String,description:String,fee:Double,numberInCart:Int){
        val refPizza = db.getReference("Pizza")

        val info = HashMap<String,Any>()
        info.put("title",title)
        info.put("pic",pic)
        info.put("description",description)
        info.put("fee",fee)
        info.put("numberInCart",numberInCart)

        refPizza.child(id).updateChildren(info)
    }

    fun deleteFood(id:String){
        val refPizza = db.getReference("Pizza")

        refPizza.child(id).removeValue()

    }
}