package com.example.imc

// Importaciones necesarias para la funcionalidad de la actividad.
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge // Para activar bordes sin distracciones en la UI.
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat // Permite manejar ajustes visuales de vistas.
import androidx.core.view.WindowInsetsCompat // Maneja los márgenes del sistema (notificaciones, barra de navegación, etc.).

// Definición de la clase Resultado que extiende AppCompatActivity para manejar la actividad.
class Resultado : AppCompatActivity() {

    // Declaración de variables para los componentes de la interfaz gráfica.
    private lateinit var txt_card: TextView // Texto principal que mostrará un mensaje.
    private lateinit var txt_resultado_card: TextView // Texto para el resultado del IMC.
    private lateinit var txt_peso_card: TextView // Texto que mostrará el valor numérico del IMC.
    private var result: Double = 0.0 // Variable para almacenar el resultado del IMC.
    private lateinit var boton_recalcular: Button // Botón para recalcular el IMC.

    // Metodo que se ejecuta al crear la actividad.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Activa la configuración de bordes sin distracciones.
        enableEdgeToEdge()

        // Define el archivo de diseño para esta actividad.
        setContentView(R.layout.activity_resultado)

        // Ajusta automáticamente los márgenes para evitar superposición con barras del sistema.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()) // Obtiene los márgenes del sistema.
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom) // Ajusta los márgenes.
            insets // Devuelve los insets aplicados.
        }

        // Inicializa los componentes de la interfaz gráfica.
        initComponent()

        // Configura los listeners (eventos de clic).
        initListeners()

        // Obtiene el resultado enviado desde otra actividad usando el intent.
        result = intent.extras?.getDouble("Resultado") ?: -1.0 // Si no se encuentra el valor, usa -1.0 como predeterminado.

        // Llama al metodo para calcular y mostrar el resultado del IMC.
        calcular(result)
    }

    // Metodo para inicializar los componentes gráficos.
    fun initComponent() {
        txt_card = findViewById(R.id.txt_card) // Asocia la variable con el componente del layout.
        txt_resultado_card = findViewById(R.id.txt_resultado_card)
        txt_peso_card = findViewById(R.id.txt_peso_card)
        boton_recalcular = findViewById(R.id.btn_recalcular)
    }

    // Metodo para configurar los listeners de eventos.
    fun initListeners() {
        // Configura el evento de clic para el botón "Recalcular".
        boton_recalcular.setOnClickListener { recalcular() }
    }

    // Metodo para manejar la acción de recalcular.
    fun recalcular() {
        // Usa el dispatcher de retroceso para regresar a la actividad anterior.
        onBackPressedDispatcher.onBackPressed()
    }

    // Metodo para calcular el resultado y mostrar la clasificación del IMC.
    fun calcular(result: Double) {
        // Muestra el valor del IMC en el componente correspondiente.
        txt_peso_card.text = result.toString()

        // Clasifica el IMC usando un bloque `when`.
        when (result) {
            // Rango para peso bajo.
            in 0.00..18.49 -> {
                // Configura el texto y el color para peso bajo.
                txt_resultado_card.text = getString(R.string.peso_bajo) // Texto para peso bajo.
                txt_resultado_card.setTextColor(getResources().getColor(R.color.peso_bajo)) // Color asociado.
                txt_card.text = getString(R.string.peso_bajo_s) // Mensaje de detalle para peso bajo.
            }
            // Rango para peso normal.
            in 18.50..24.49 -> {
                txt_resultado_card.text = getString(R.string.peso_normal)
                txt_resultado_card.setTextColor(getResources().getColor(R.color.peso_normal))
                txt_card.text = getString(R.string.peso_normal_s)
            }
            // Rango para peso alto.
            in 24.50..29.99 -> {
                txt_resultado_card.text = getString(R.string.peso_alto)
                txt_resultado_card.setTextColor(getResources().getColor(R.color.peso_alto))
                txt_card.text = getString(R.string.peso_alto_s)
            }
            // Rango para sobrepeso.
            in 30.00..99.99 -> {
                txt_resultado_card.text = getString(R.string.peso_sobrepeso)
                txt_resultado_card.setTextColor(getResources().getColor(R.color.peso_sobre))
                txt_card.text = getString(R.string.peso_sobrepeso_s)
            }
        }
    }
}