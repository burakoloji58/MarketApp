package com.example.marketapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marketapp.Adapter.PizzaAdapter
import com.example.marketapp.Domain.PizzaDomain
import com.example.marketapp.databinding.ActivityPizzaBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PizzaActivity : AppCompatActivity() {
    private lateinit var ulas : ActivityPizzaBinding
    private lateinit var PizzaList : ArrayList<PizzaDomain>
    private lateinit var adapter : PizzaAdapter
    private lateinit var refCatagory : DatabaseReference
    val db = FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ulas = ActivityPizzaBinding.inflate(layoutInflater)
        setContentView(ulas.root)

        ulas.rc.setHasFixedSize(true)
        ulas.rc.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        PizzaList = ArrayList()

        adapter = PizzaAdapter(this,PizzaList)
        ulas.rc.adapter = adapter

        getAllPizza()

    }

    fun getAllPizza() {
        val refPizza = db.getReference("Pizza")

        refPizza.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(ds: DataSnapshot) {
                PizzaList.clear()

                for (c in ds.children){
                    val pizza = c.getValue(PizzaDomain::class.java)

                    if (pizza != null){
                        pizza.id = c.key
                        PizzaList.add(pizza)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }

}