package com.example.marketapp.Helper

import com.example.marketapp.Domain.CatagoryDomain
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CatagoryDomainDao {

    private lateinit var refCatagory : DatabaseReference
    val db = FirebaseDatabase.getInstance()

    fun insertCatagory(title: String,pic: String){
        val refCatagory = db.getReference("Catagory")

        val catagory = CatagoryDomain("",title,pic)
        refCatagory.push().setValue(catagory)
    }

    fun updateCatagory(id:String,title: String,pic: String){
        val refCatagory = db.getReference("Catagory")

        val info = HashMap<String,Any>()
        info.put("title",title)
        info.put("pic",pic)

        refCatagory.child(id).updateChildren(info)
    }

    fun deleteCatagory(id:String){
        val refCatagory = db.getReference("Catagory")

        refCatagory.child(id).removeValue()
    }


    fun searchCatagory(){

    }


}