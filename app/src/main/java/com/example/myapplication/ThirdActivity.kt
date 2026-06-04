package com.example.myapplication

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.myapplication.databinding.ActivityThirdBinding
class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_third)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Atrás"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Código del botón de llamada, toma el numero telefónico y hace

        binding.imageButtonPhone.setOnClickListener {
            // Instrucción para asignar el número telefonico escrito

            val phoneNumber = binding.editTextPhone.text.toString().trim()
            // Código para preguntar si el cuadro de diálogo no está

            if (phoneNumber.isNotEmpty()) {
                val intentCall = Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:$phoneNumber")
                )
                startActivity(intentCall)
            } else {
                Toast.makeText(
                    this,
                    "Debes marcar un número, intenta nuevamente",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        //Código botón busqueda de página Web
        binding.imageButtonWeb.setOnClickListener {
            val rawUrl = binding.editTextWeb.text.toString().trim()

            if (rawUrl.isNotEmpty()) {
                val url = if (rawUrl.startsWith("http://") || rawUrl.startsWith("https://")) {
                    rawUrl
                } else {
                    "https://$rawUrl"
                }
                val intentWeb = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intentWeb)
            } else {
                Toast.makeText(this, "Escribe una página web", Toast.LENGTH_LONG).show()
            }
        }
        //Código botón Escribeme un correo
        binding.buttonEmailMe.setOnClickListener {
            val mailto = "mailto:${getString(R.string.email_to)}" +
                    "?cc=${getString(R.string.email_cc)}" +
                    "&subject=${Uri.encode("Asunto del correo")}" +
                    "&body=${Uri.encode(getString(R.string.default_message))}"
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
        binding.buttonContactPhone.setOnClickListener {
            val intentCall = Intent(
                Intent.ACTION_DIAL, Uri.parse(
                    "tel:${getString(R.string.contact_phone)}"
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
                    putExtra("address", getString(R.string.contact_phone))
                    putExtra("sms_body", getString(R.string.default_message))
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
