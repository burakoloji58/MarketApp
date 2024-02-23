package com.example.marketapp.Domain

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class PizzaDomain(var id:String?="",
                       var title:String?="",
                       var pic:String?="",
                       var description:String?="",
                       var fee:Double?=0.0,
                       var numberInCart:Int?=0):Serializable {
}