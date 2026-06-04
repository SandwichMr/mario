package com.example.myapplication

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.myapplication.databinding.ActivityThirdBinding
class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_third)
        val binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Atrás"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Código del botón de llamada, toma el numero telefónico y hace

        binding.imageButtonPhone.setOnClickListener(object :
            View.OnClickListener {
            override fun onClick(v: View?) {
                // Instrucción para asignar el número telefonico escrito

                val phoneNumber = binding.editTextPhone.text.toString()
                // Código para preguntar si el cuadro de diálogo no está

                if (phoneNumber.isNotEmpty()) {
                    val intentCall = Intent(
                        Intent.ACTION_DIAL,
                        Uri.parse("tel:$phoneNumber")
                    )
                    startActivity(intentCall)
                } else
                    Toast.makeText(
                        this@ThirdActivity,
                        "Debes marcar un número, intenta nuevamente",
                        Toast.LENGTH_LONG
                    ).show()
            }
        })
        //Código botón busqueda de página Web
        binding.imageButtonWeb.setOnClickListener {
            val url = binding.editTextWeb.text.toString()
            val intentWeb = Intent()
            intentWeb.action = Intent.ACTION_VIEW
            intentWeb.data = Uri.parse("https://$url")
            startActivity(intentWeb)
        }
        //Código botón Escribeme un correo
        binding.buttonEmailMe.setOnClickListener {
            val mailto = "mailto:correoprueba_1@hotmail.com" + "?cc=" +
                    "correoprueba_2@gmail.com" + "&subject=" + Uri.encode("Asunto del correo") + "&body=" + Uri.encode(
                "Esta es una prueba..."
            )
            val emailIntent = Intent(
                Intent.ACTION_SENDTO,
                Uri.parse(mailto)
            )
            try {
                startActivity(Intent.createChooser(emailIntent, "Elige el cliente de correo..."))
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    this,
                    "No hay ningún cliente de correo electrónico instalado.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        //Código botón Contáctame
        binding.buttonContacPhone.setOnClickListener {
            val intentCall = Intent(
                Intent.ACTION_DIAL, Uri.parse(
                    "tel:2288302966"
                )
            )
            startActivity(intentCall)
        }
        //Código botón Cámara
        binding.imageButtonCamera.setOnClickListener {
            val intentCamera = Intent(
                "android.media.action.IMAGE_CAPTURE"
            )
            startActivity(intentCamera)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }

            R.id.menuContactos -> {
                val intentContactos = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("content://contacts/people")
                )
                startActivity(intentContactos)
                return true
            }

            R.id.menuSMS -> {
                val intentSMS = Intent().apply {
                    action = Intent.ACTION_SENDTO
                    data = Uri.parse("smsto:")
                    putExtra("address", "2288302966")
                    putExtra("sms_body", "Cuerpo del SMS desde un menú")
                }
                startActivity(intentSMS)
                return true
            }

            R.id.menuVideo -> {
                val intentVideo = Intent("android.media.action.VIDEO_CAPTURE")
                startActivity(intentVideo)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
