package com.example.marketapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.marketapp.Domain.CartFoodDomain
import com.example.marketapp.Domain.FoodDomain
import com.example.marketapp.Helper.CartFoodDomainDao
import com.example.marketapp.databinding.ActivityShowDetailBinding

class ShowDetailActivity : AppCompatActivity() {
    private lateinit var ulas : ActivityShowDetailBinding
    private lateinit var CartList : ArrayList<CartFoodDomain>

    var numberOrder = 1
    var numberText = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        ulas = ActivityShowDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(ulas.root)

        CartList = ArrayList()
        getBundle()
    }

    fun getBundle(){

        val Fobject = intent.getSerializableExtra("Fobject") as FoodDomain

            ulas.picFood.setImageResource(this.resources.getIdentifier(Fobject.pic,"drawable",this.packageName))
            ulas.titleTxt.setText(Fobject.title)
            ulas.priceTxt.setText("$"+Fobject.fee)
            ulas.descriptionTxt.setText(Fobject.description)
            ulas.numberOrderTxt.setText(numberOrder.toString())

            ulas.plusBtn.setOnClickListener{
                numberOrder+=1
                ulas.numberOrderTxt.setText(numberOrder.toString())
            }

            ulas.minusBtn.setOnClickListener{
                if (numberOrder>1)
                {
                    numberOrder-=1
                }
                ulas.numberOrderTxt.setText(numberOrder.toString())
            }

            ulas.addToCartBtn.setOnClickListener {
                numberText= ulas.numberOrderTxt.text.toString().toInt()
                Fobject.numberInCart=numberText
                insertFood(Fobject)
            }
    }

    fun insertFood(item : FoodDomain){

            CartFoodDomainDao().insertFoodToCart(item.id.toString(),
                item.title.toString(),
                item.pic.toString(),
                item.description.toString(),
                item.fee!!.toDouble(),
                item.numberInCart!!.toInt())
            Toast.makeText(this, "eklendi", Toast.LENGTH_SHORT).show()
    }

}