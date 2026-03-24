package com.example.farid_abstract

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etAlas = findViewById<EditText>(R.id.etAlas)
        val etTinggi = findViewById<EditText>(R.id.etTinggi)
        val etSisi = findViewById<EditText>(R.id.etSisi)

        val btnSegitiga = findViewById<Button>(R.id.btnSegitiga)
        val btnKubus = findViewById<Button>(R.id.btnKubus)

        val tvHasil = findViewById<TextView>(R.id.tvHasil)

        btnSegitiga.setOnClickListener {
            val alas = etAlas.text.toString().toDouble()
            val tinggi = etTinggi.text.toString().toDouble()

            val luas = 0.5 * alas * tinggi
            tvHasil.text = "Luas Segitiga: $luas"
        }

        btnKubus.setOnClickListener {
            val sisi = etSisi.text.toString().toDouble()

            val volume = sisi * sisi * sisi
            tvHasil.text = "Volume Kubus: $volume"
        }
    }
}