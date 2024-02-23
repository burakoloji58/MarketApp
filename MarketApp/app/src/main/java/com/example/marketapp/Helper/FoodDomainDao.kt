package com.example.marketapp.Helper

import com.example.marketapp.Domain.FoodDomain
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FoodDomainDao {

    private lateinit var refFood : DatabaseReference
    val db = FirebaseDatabase.getInstance()

    fun insertFood(title:String,pic: String,description:String,fee:Double,numberInCart:Int){
        val refFood = db.getReference("Food")

        val food = FoodDomain("",title,pic,description,fee,numberInCart)
        refFood.push().setValue(food)
    }

    fun updateFood(id:String, title:String,pic: String,description:String,fee:Double,numberInCart:Int){
        val refFood = db.getReference("Food")

        val info = HashMap<String,Any>()
        info.put("title",title)
        info.put("pic",pic)
        info.put("description",description)
        info.put("fee",fee)
        info.put("numberInCart",numberInCart)

        refFood.child(id).updateChildren(info)
    }

    fun deleteFood(id:String){
        val refFood = db.getReference("Food")

        refFood.child(id).removeValue()

    }

}