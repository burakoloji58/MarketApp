package com.example.marketapp.Domain

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class CatagoryDomain(var id:String?="",
                          var title: String?="",
                          var pic: String?="") : Serializable{

}