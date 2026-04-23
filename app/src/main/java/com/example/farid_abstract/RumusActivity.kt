package com.example.farid_abstract

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Button
import android.widget.TextView
import com.google.android.material.appbar.MaterialToolbar

class RumusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rumus)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = intent.getStringExtra("judul")
//        supportActionBar?.title = "Rumus Bangun Ruang"

        val tvJudul = findViewById<TextView>(R.id.tvJudul)
        val tvDeskripsi = findViewById<TextView>(R.id.tvDeskripsi)

        val judul = intent.getStringExtra("judul")
        val deskripsi = intent.getStringExtra("deskripsi")

        tvJudul.text = judul
        tvDeskripsi.text = deskripsi

        val etAlas = findViewById<EditText>(R.id.etAlas)
        val etTinggi = findViewById<EditText>(R.id.etTinggi)
        val etSisi = findViewById<EditText>(R.id.etSisi)

        val btnSegitiga = findViewById<Button>(R.id.btnSegitiga)
        val btnKubus = findViewById<Button>(R.id.btnKubus)

        val tvHasil = findViewById<TextView>(R.id.tvHasil)

        btnSegitiga.setOnClickListener {
            val alas = etAlas.text.toString().toDoubleOrNull() ?: 0.0
            val tinggi = etTinggi.text.toString().toDoubleOrNull() ?: 0.0

            val luas = 0.5 * alas * tinggi
            tvHasil.text = "Luas Segitiga: $luas"
        }

        btnKubus.setOnClickListener {
            val sisi = etSisi.text.toString().toDoubleOrNull() ?: 0.0

            val volume = sisi * sisi * sisi
            tvHasil.text = "Volume Kubus: $volume"
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}