package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private val saludo = "¡Hola desde el Activity Main / Primer pantalla!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Orientación vertical
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        // Icono en barra superior
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.kasane_teto_logo)

        // ViewBinding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Botón calcular edad
        binding.btncalcular.setOnClickListener {

            val anioNac = binding.edtxtanionac.text.toString().toIntOrNull()
            val anioActual = Calendar.getInstance().get(Calendar.YEAR)

            when {
                anioNac == null -> {
                    Toast.makeText(
                        this,
                        "Escribe tu año de nacimiento",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                anioNac !in 1900..anioActual -> {
                    Toast.makeText(
                        this,
                        "Escribe un año válido",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    val miEdad = anioActual - anioNac
                    binding.txtedad.text = "Tu edad es $miEdad años"
                }
            }
        }

        // Botón siguiente
        binding.btnsiguiente.setOnClickListener {
            startActivity(this, SecondActivity::class.java)
        }
    }

    // Función para cambiar de Activity
    fun startActivity(
        actividadactual: Activity,
        actividadnext: Class<*>
    ) {
        val intentmio = Intent(actividadactual, actividadnext)
        intentmio.putExtra("saludo", saludo)
        actividadactual.startActivity(intentmio)
    }

    override fun onStop() {
        super.onStop()

        Toast.makeText(
            this,
            "Mensaje o notificación onStop",
            Toast.LENGTH_LONG
        ).show()
    }
}
