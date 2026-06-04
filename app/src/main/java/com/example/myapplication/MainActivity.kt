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
        supportActionBar?.setIcon(R.mipmap.ic_launcher)

        // ViewBinding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Botón calcular edad
        binding.btncalcular.setOnClickListener {

            val texto = binding.edtxtanionac.text.toString()

            if (texto.isNotEmpty()) {

                val anioNac = texto.toInt()
                val anioActual = Calendar.getInstance().get(Calendar.YEAR)
                val miEdad = anioActual - anioNac

                binding.txtedad.text = "Tu edad es $miEdad años"

            } else {
                Toast.makeText(
                    this,
                    "Escribe tu año de nacimiento",
                    Toast.LENGTH_SHORT
                ).show()
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
