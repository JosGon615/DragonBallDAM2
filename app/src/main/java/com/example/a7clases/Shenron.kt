package com.example.a7clases

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.a7clases.databinding.ActivityShenronBinding
import com.google.gson.Gson

class Shenron : AppCompatActivity() {
    private lateinit var binding: ActivityShenronBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShenronBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val compartir = getSharedPreferences("Personaje", MODE_PRIVATE)
        val gson = Gson()
        var persString = compartir.getString("Personaje", "")
        val pers = gson.fromJson(persString, Personaje::class.java)

        binding.poder.setOnClickListener {
            pers.setFuerza(pers.getFuerza() * 2)
            Toast.makeText(this, "Tu fuerza ha aumnetado a ${pers.getFuerza()}", Toast.LENGTH_SHORT).show()

            val editor = compartir.edit()
            persString = gson.toJson(pers)
            editor.putString("Personaje", persString)
            editor.apply()

            val intent = Intent(this@Shenron, Dado::class.java)
            startActivity(intent)
        }

        binding.fortuna.setOnClickListener {
            pers.setDinero(pers.getDinero() + 1000)
            Toast.makeText(this, "Has ganado 1000 monedas", Toast.LENGTH_SHORT).show()

            val editor = compartir.edit()
            persString = gson.toJson(pers)
            editor.putString("Personaje", persString)
            editor.apply()

            val intent = Intent(this@Shenron, Dado::class.java)
            startActivity(intent)
        }
    }
}