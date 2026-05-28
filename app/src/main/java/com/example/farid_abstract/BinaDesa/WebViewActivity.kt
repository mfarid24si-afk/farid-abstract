package com.example.farid_abstract.BinaDesa

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.farid_abstract.AuthActivity
import com.example.farid_abstract.BinaDesa.Message.MessageFragment
import com.example.farid_abstract.R
import com.example.farid_abstract.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // SETUP TOOLBAR
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Portal Bina Desa"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // ─── PINDAH KE SINI DAN PERIKSA BARIS INI ───
        // PENTING: Jika aplikasi baru dibuka, baris ini wajib ada untuk memasukkan HomeFragment ke FrameLayout
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, HomeFragment())
                .commit()
        }

        // LOGIKA DIKLIK UNTUK BOTTOM NAVIGATION
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.nav_home -> {
                    supportActionBar?.title = "Portal Bina Desa"
                    selectedFragment = HomeFragment()
                }
                R.id.nav_about -> {
                    supportActionBar?.title = "About Bina Desa"
                    selectedFragment = AboutFragment()
                }
                R.id.nav_profile -> {
                    supportActionBar?.title = "Developer Profile"
                    selectedFragment = ProfileFragment()
                }
                R.id.nav_message -> {
                    supportActionBar?.title = "Setting"
                    selectedFragment = MessageFragment()
                }
            }
            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, selectedFragment)
                    .commit()
            }
            true
        }
    }


    // FUNGSI TRANSAKSI UNTUK MENIMPA FRAGMENT KE DALAM FRAME LAYOUT
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
