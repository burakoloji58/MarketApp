package com.example.marketapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marketapp.Adapter.CatagoryAdapter
import com.example.marketapp.Adapter.PopularAdapter
import com.example.marketapp.Domain.CatagoryDomain
import com.example.marketapp.Domain.FoodDomain
import com.example.marketapp.Helper.CartFoodDomainDao
import com.example.marketapp.Helper.CatagoryDomainDao
import com.example.marketapp.Helper.FoodDomainDao
import com.example.marketapp.Helper.PizzaDomainDao
import com.example.marketapp.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    
    //   BURAK    KARAGÖZ

    private lateinit var ulas : ActivityMainBinding
    private lateinit var adapter : CatagoryAdapter
    private lateinit var adapter2 : PopularAdapter
    private lateinit var CatagoryList : ArrayList<CatagoryDomain>
    private lateinit var FoodList : ArrayList<FoodDomain>
    private lateinit var refCatagory : DatabaseReference
    val db = FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        ulas = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(ulas.root)

        //PizzaDomainDao().insertFood("Pepperoni pizza","pop_1","slices ,pepperoni ,mozerella cheese,fresh oregano,ground black pepper, pizza sauce",9.76,0)
        //PizzaDomainDao().insertFood("Vegetable pizza","pop_3","olive oil ,vegetable oil ,pitted kalamata, chery tomatoes,fresh oregano,basil",8.86,0)

        recyclerView()
        recyclerView2()

        ulas.basketBtn.setOnClickListener {
            val intent = Intent(this,CartListActivity::class.java)
            startActivity(intent)
        }

    }

    fun recyclerView(){
        ulas.recyclerView.setHasFixedSize(true)
        ulas.recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        /*
        CatagoryDomainDao().insertCatagory("Pizza","cat_1")
        CatagoryDomainDao().insertCatagory("Burger","cat_2")
        CatagoryDomainDao().insertCatagory("HotDog","cat_3")
        CatagoryDomainDao().insertCatagory("Drink","cat_4")
        CatagoryDomainDao().insertCatagory("Donut","cat_5")
         */

        CatagoryList = ArrayList()

        adapter = CatagoryAdapter(this,CatagoryList)
        ulas.recyclerView.adapter = adapter

        getAllCatagory()
    }

    fun recyclerView2(){
        ulas.recyclerView2.setHasFixedSize(true)
        ulas.recyclerView2.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        /*
        FoodDomainDao().insertFood("Pepperoni pizza","pop_1","slices ,pepperoni ,mozerella cheese,fresh oregano,ground black pepper, pizza sauce",9.76,0)
        FoodDomainDao().insertFood("Cheese Burger","pop_2","beef ,Gouda Cheese, special sauce, Lettuce , tomato",8.79,0)
        FoodDomainDao().insertFood("Vegetable pizza","pop_3","olive oil ,vegetable oil ,pitted kalamata, chery tomatoes,fresh oregano,basil",8.86,0)
        */

        FoodList = ArrayList()

        adapter2 = PopularAdapter(this,FoodList)
        ulas.recyclerView2.adapter = adapter2

        getAllFood()
    }

    fun getAllCatagory(){
        val refCatagory = db.getReference("Catagory")

        refCatagory.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(ds: DataSnapshot) {
                CatagoryList.clear()

                for (c in ds.children){
                    val catagory = c.getValue(CatagoryDomain::class.java)

                    if (catagory!= null){
                        catagory.id = c.key
                        CatagoryList.add(catagory)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun getAllFood(){
        val refFood = db.getReference("Food")

        refFood.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(ds: DataSnapshot) {
                FoodList.clear()

                for (c in ds.children){
                    val food = c.getValue(FoodDomain::class.java)

                    if (food != null){
                        food.id = c.key
                        FoodList.add(food)
                    }
                }
                adapter2.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}