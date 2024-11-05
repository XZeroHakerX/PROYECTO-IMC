package com.example.imc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Resultado : AppCompatActivity() {


    private lateinit var txt_card: TextView
    private lateinit var txt_resultado_card: TextView
    private lateinit var txt_peso_card: TextView
    private var result: Double = 0.0
    private lateinit var boton_recalcular: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponent()
        initListeners()

        result = intent.extras?.getDouble(MainActivity.RESULT) ?: -1.0

        calcular(result)

    }

    fun initComponent(){

        txt_card = findViewById(R.id.txt_card)
        txt_resultado_card = findViewById(R.id.txt_resultado_card)
        txt_peso_card = findViewById(R.id.txt_peso_card)
        boton_recalcular = findViewById(R.id.btn_recalcular)

    }

    fun initListeners(){
        boton_recalcular.setOnClickListener{ recalcular() }
    }

    fun recalcular(){
        val intento = Intent(this, MainActivity::class.java)
        startActivity(intento)
    }

    fun calcular(result: Double) {
        when(result){
            in 0.00 .. 18.49 ->{
                txt_resultado_card.text = getString(R.string.peso_bajo)
                txt_resultado_card.setTextColor(getResources().getColor(R.color.peso_bajo))
                txt_peso_card.text = result.toString()
                txt_card.text = getString(R.string.peso_bajo_s)

            }

            in 18.50 .. 24.49-> {
                txt_resultado_card.text = getString(R.string.peso_normal)
                txt_resultado_card.setTextColor(getResources().getColor(R.color.peso_normal))
                txt_peso_card.text = result.toString()
                txt_card.text = getString(R.string.peso_normal_s)


            }

            in 24.50 .. 29.99-> {
                txt_resultado_card.text = getString(R.string.peso_alto)
                txt_resultado_card.setTextColor(getResources().getColor(R.color.peso_alto))
                txt_peso_card.text = result.toString()
                txt_card.text = getString(R.string.peso_alto_s)

            }

            in 30.00 .. 99.99-> {
                txt_resultado_card.text = getString(R.string.peso_sobrepeso)
                txt_resultado_card.setTextColor(getResources().getColor(R.color.peso_sobre))
                txt_peso_card.text = result.toString()
                txt_card.text = getString(R.string.peso_sobrepeso_s)


            }
        }
    }

    private fun textView() = txt_card
}