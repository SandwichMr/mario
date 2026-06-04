package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_second)
        //Instrucciones para inflar o ejecutar la pantalla
        val binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewIntent.text = "Mario was here"

        //Código del botón siguiente
        binding.btnsiguiente2.setOnClickListener {
            startActivity(this, ThirdActivity::class.java )
        }
    }
    //Código de la función starActivity qye envía de la pantalla actual a la siguiente
    fun startActivity(actividadactual: Activity, actividadnext:
    Class<*>){
        val intentmio = Intent(actividadactual,actividadnext)
        //intentmio.putExtra("saludo",saludo)
        actividadactual.startActivity(intentmio)
        //La siguiente instrucción se utiliza para que al seleccionar regresar se finalice la aplicación
        //actividadactual.finish()
    }
}
