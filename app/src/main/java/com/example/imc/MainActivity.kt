package com.example.imc

// Importaciones necesarias para la funcionalidad de la actividad.
import android.annotation.SuppressLint
import android.content.Intent // Permite navegar entre actividades.
import android.icu.text.DecimalFormat // Formatea números en formato decimal.
import android.os.Bundle
import android.transition.Slide // Proporciona transiciones visuales.
import android.widget.Button // Componente de botón.
import android.widget.TextView // Componente de texto.
import androidx.activity.enableEdgeToEdge // Configuración para bordes sin distracciones.
import androidx.annotation.ColorInt // Anotación para valores de color.
import androidx.appcompat.app.AppCompatActivity // Clase base para actividades.
import androidx.cardview.widget.CardView // Componente de tarjeta visual.
import androidx.core.view.ViewCompat // Permite manejar ajustes visuales de vistas.
import androidx.core.view.WindowInsetsCompat // Maneja los márgenes del sistema.
import com.google.android.material.floatingactionbutton.FloatingActionButton // Botón de acción flotante.
import com.google.android.material.slider.RangeSlider // Barra de rango deslizante.
import com.google.android.material.slider.Slider // Barra deslizante individual.

// Definición de la clase principal de la aplicación.
class MainActivity : AppCompatActivity() {

    // Declaración de variables para los componentes de la interfaz.
    private lateinit var card_hombre: CardView // Tarjeta para seleccionar hombre.
    private lateinit var card_mujer: CardView // Tarjeta para seleccionar mujer.
    private lateinit var slider_altura: RangeSlider // Barra deslizante para la altura.
    private lateinit var boton_calcular: Button // Botón para calcular IMC.
    private lateinit var boton_mas_peso: FloatingActionButton // Botón para aumentar peso.
    private lateinit var boton_menos_peso: FloatingActionButton // Botón para disminuir peso.
    private lateinit var boton_mas_edad: FloatingActionButton // Botón para aumentar edad.
    private lateinit var boton_menos_edad: FloatingActionButton // Botón para disminuir edad.
    private lateinit var texto_altura: TextView // Texto que muestra la altura actual.
    private lateinit var texto_edad: TextView // Texto que muestra la edad actual.
    private lateinit var texto_peso: TextView // Texto que muestra el peso actual.

    // Variables para almacenar los valores iniciales de altura, edad y peso.
    private var alturaActual: Int = 120 // Altura inicial.
    private var edadActual: Int = 30 // Edad inicial.
    private var pesoActual: Int = 60 // Peso inicial.

    // Metodo que se ejecuta al crear la actividad.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Habilita el modo de bordes sin distracciones.
        enableEdgeToEdge()

        // Define el archivo de diseño asociado a esta actividad.
        setContentView(R.layout.activity_main)

        // Ajusta automáticamente los márgenes para evitar superposición con barras del sistema.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()) // Obtiene los márgenes del sistema.
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom) // Aplica los márgenes.
            insets // Devuelve los insets aplicados.
        }

        // Inicializa los componentes gráficos y configuraciones iniciales.
        initComponent()
        initListeners()
        initPantalla()
    }

    // Metodo para inicializar los componentes gráficos.
    private fun initComponent() {
        // Asocia cada variable con su correspondiente vista del diseño.
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

    // Metodo para configurar los eventos de clic y cambios en los componentes.
    fun initListeners() {
        // Evento de clic para seleccionar la tarjeta de hombre.
        card_hombre.setOnClickListener { seleccionHombre() }

        // Evento de clic para seleccionar la tarjeta de mujer.
        card_mujer.setOnClickListener { seleccionMujer() }

        // Evento que escucha cambios en la barra de altura.
        slider_altura.addOnChangeListener { slider, value, fromUser ->
            val df = DecimalFormat("#.##") // Formato decimal para evitar números con muchos decimales.
            alturaActual = df.format(value).toInt() // Convierte el valor a entero.
            texto_altura.text = "$alturaActual cm" // Actualiza el texto de altura.
        }

        // Evento de clic para calcular el IMC.
        boton_calcular.setOnClickListener {
            irResultado(calcularImc()) // Calcula el IMC y navega a la actividad de resultado.
        }

        // Configura los eventos de clic para los botones flotantes.
        boton_mas_peso.setOnClickListener { masPeso() }
        boton_menos_peso.setOnClickListener { menosPeso() }
        boton_mas_edad.setOnClickListener { masEdad() }
        boton_menos_edad.setOnClickListener { menosEdad() }
    }

    // Metodo para incrementar el peso.
    fun masPeso() {
        pesoActual++
        texto_peso.text = pesoActual.toString()
    }

    // Metodo para disminuir el peso.
    fun menosPeso() {
        pesoActual--
        texto_peso.text = pesoActual.toString()
    }

    // Metodo para incrementar la edad.
    fun masEdad() {
        edadActual++
        texto_edad.text = edadActual.toString()
    }

    // Metodo para disminuir la edad.
    fun menosEdad() {
        edadActual--
        texto_edad.text = edadActual.toString()
    }

    // Metodo para configurar la pantalla inicial.
    fun initPantalla() {
        // Resalta la tarjeta de hombre como seleccionada por defecto.
        card_hombre.setCardBackgroundColor(resources.getColor(R.color.seleccion_card, null))

        // Muestra los valores iniciales de peso y edad en los textos correspondientes.
        texto_edad.text = edadActual.toString()
        texto_peso.text = pesoActual.toString()
    }

    // Metodo para resaltar la tarjeta de hombre.
    fun seleccionHombre() {
        card_hombre.setCardBackgroundColor(resources.getColor(R.color.seleccion_card, null)) // Cambia el color de la tarjeta de hombre.
        card_mujer.setCardBackgroundColor(resources.getColor(R.color.azul, null)) // Restaura el color de la tarjeta de mujer.
    }

    // Metodo para resaltar la tarjeta de mujer.
    fun seleccionMujer() {
        card_mujer.setCardBackgroundColor(resources.getColor(R.color.seleccion_card, null))
        card_hombre.setCardBackgroundColor(resources.getColor(R.color.azul, null))
    }

    // Metodo para calcular el IMC basado en el peso y la altura.
    fun calcularImc(): Double {
        val df = DecimalFormat("#.##") // Formatea el resultado del cálculo.
        val imc = pesoActual / ((alturaActual.toDouble() / 100) * (alturaActual.toDouble() / 100)) // Fórmula del IMC.
        return df.format(imc).replace(",", ".").toDouble() // Devuelve el IMC formateado.
    }

    // Metodo para navegar a la actividad de resultado.
    fun irResultado(resultado: Double) {
        val intento = Intent(this, Resultado::class.java) // Crea un intento para la actividad `Resultado`.
        intento.putExtra("Resultado", resultado) // Envía el resultado del IMC como parámetro.
        startActivity(intento) // Inicia la actividad.
    }
}