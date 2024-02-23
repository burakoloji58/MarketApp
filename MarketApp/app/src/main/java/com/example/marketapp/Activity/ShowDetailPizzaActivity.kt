package com.example.marketapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.marketapp.Domain.PizzaDomain
import com.example.marketapp.Helper.CartFoodDomainDao
import com.example.marketapp.databinding.ActivityShowDetailPizzaBinding

class ShowDetailPizzaActivity : AppCompatActivity() {
    private lateinit var ulas : ActivityShowDetailPizzaBinding

    var numberOrder = 1
    var numberText = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        ulas = ActivityShowDetailPizzaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(ulas.root)

        getBundle()
    }

    fun getBundle(){
        val PizzaInfo = intent.getSerializableExtra("PizzaInfo") as PizzaDomain

            ulas.picPFood.setImageResource(this.resources.getIdentifier(PizzaInfo.pic,"drawable",this.packageName))
            ulas.titlePTxt.setText(PizzaInfo.title)
            ulas.pricePTxt.setText("$"+PizzaInfo.fee)
            ulas.descriptionPTxt.setText(PizzaInfo.description)
            ulas.numberOrderPTxt.setText(numberOrder.toString())

            ulas.plusPBtn.setOnClickListener{
                numberOrder+=1
                ulas.numberOrderPTxt.setText(numberOrder.toString())
            }

            ulas.minusPBtn.setOnClickListener{
                if (numberOrder>1)
                {
                    numberOrder-=1
                }
                ulas.numberOrderPTxt.setText(numberOrder.toString())
            }

            ulas.addToCartPBtn.setOnClickListener {
                numberText= ulas.numberOrderPTxt.text.toString().toInt()
                PizzaInfo.numberInCart=numberText
                insertPizza(PizzaInfo)
            }

    }

    fun insertPizza(item : PizzaDomain){

        CartFoodDomainDao().insertFoodToCart(item.id.toString(),
            item.title.toString(),
            item.pic.toString(),
            item.description.toString(),
            item.fee!!.toDouble(),
            item.numberInCart!!.toInt())
        Toast.makeText(this, "eklendi", Toast.LENGTH_SHORT).show()
    }

}