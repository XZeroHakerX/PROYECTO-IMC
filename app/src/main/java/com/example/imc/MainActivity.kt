package com.example.imc

import android.annotation.SuppressLint
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.transition.Slide
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {


    private lateinit var card_hombre:CardView
    private lateinit var card_mujer:CardView
    private lateinit var slider_altura: RangeSlider
    private lateinit var boton_calcular:Button
    private lateinit var boton_mas_peso:FloatingActionButton
    private lateinit var boton_menos_peso:FloatingActionButton
    private lateinit var boton_mas_edad:FloatingActionButton
    private lateinit var boton_menos_edad:FloatingActionButton

    private lateinit var texto_altura:TextView
    private lateinit var texto_edad:TextView
    private lateinit var texto_peso:TextView

    private var alturaActual:Int = 120





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        initComponent()
        initListeners()
        initPantalla()

    }



    private fun initComponent(){

        card_hombre = findViewById(R.id.CardHombre)
        card_mujer = findViewById(R.id.CardMujer)
        slider_altura = findViewById(R.id.barSAltura)
        boton_calcular = findViewById(R.id.btnCalcular)
        boton_mas_peso = findViewById(R.id.btnMaPeso)
        boton_menos_peso = findViewById(R.id.btnMenosPeso)
        boton_mas_edad = findViewById(R.id.botonMasEdad)
        boton_menos_edad = findViewById(R.id.botonMenosEdad)
        texto_altura = findViewById(R.id.txtNumeroAltura)
        texto_edad = findViewById(R.id.txtEdadNumero)
        texto_peso = findViewById(R.id.txtPesoNumero)
   }

    fun initListeners(){

        card_hombre.setOnClickListener{ seleccionHombre() }
        card_mujer.setOnClickListener{ seleccionMujer() }
        slider_altura.addOnChangeListener {slider,value,fromUser ->
            val df = DecimalFormat("#.##")
            alturaActual = df.format(value).toInt()
            texto_altura.text = alturaActual.toString() + " cm"
        }
        boton_calcular.setOnClickListener{ calcularImc()}
        boton_mas_peso.setOnClickListener{}
        boton_menos_peso.setOnClickListener{}
        boton_mas_edad.setOnClickListener{}
        boton_menos_edad.setOnClickListener{}

    }



    fun initPantalla() {

        card_hombre.setCardBackgroundColor(resources.getColor(R.color.seleccion_card,null))

    }

    fun seleccionHombre(){
        card_hombre.setCardBackgroundColor(resources.getColor(R.color.seleccion_card,null))
        card_mujer.setCardBackgroundColor(resources.getColor(R.color.azul,null))

    }

    fun seleccionMujer(){
        card_mujer.setCardBackgroundColor(resources.getColor(R.color.seleccion_card,null))
        card_hombre.setCardBackgroundColor(resources.getColor(R.color.azul,null))

    }

    fun calcularImc() {


    }





}