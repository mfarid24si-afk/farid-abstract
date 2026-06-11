package com.example.farid_abstract.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.farid_abstract.AuthActivity
import com.example.farid_abstract.R
import com.example.farid_abstract.databinding.ActivityOnboardingBinding

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var adapter: OnBoardingAdapter

    // ✅ KEY SharedPreferences — harus sama dengan yang dipakai di SplashScreenActivity
    companion object {
        const val PREF_NAME = "BinadesaOnBoarding"
        const val KEY_ONBOARDING_DONE = "onboarding_done"
    }

    private val slides = listOf(
        OnBoardingSlide(
            imageRes = android.R.drawable.ic_menu_compass, // Ganti dengan ilustrasi Anda sendiri
            title = "Selamat Datang di Binadesa 🌾",
            description = "Platform peminjaman modal usaha yang aman, transparan, dan mudah diakses oleh seluruh masyarakat desa."
        ),
        OnBoardingSlide(
            imageRes = android.R.drawable.ic_menu_agenda, // Ganti dengan ilustrasi Anda sendiri
            title = "Pinjaman Mudah & Terpercaya 📋",
            description = "Ajukan pinjaman modal usaha kapan saja. Proses cepat dengan bunga rendah dan cicilan fleksibel sesuai kemampuan Anda."
        ),
        OnBoardingSlide(
            imageRes = android.R.drawable.ic_menu_send, // Ganti dengan ilustrasi Anda sendiri
            title = "Bersama Membangun Desa 🤝",
            description = "Bergabunglah bersama ribuan warga desa yang telah merasakan manfaat pembiayaan usaha melalui Binadesa Peminjaman."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = OnBoardingAdapter(slides)
        binding.viewPager.adapter = adapter

        setupDotIndicators()
        setCurrentDotIndicator(0)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentDotIndicator(position)

                if (position == slides.size - 1) {
                    // Slide terakhir: ubah tombol jadi "Ayo Mulai"
                    binding.btnNext.text = "Ayo Mulai 🚀"
                    binding.btnSkip.visibility = View.GONE
                } else {
                    binding.btnNext.text = "Selanjutnya"
                    binding.btnSkip.visibility = View.VISIBLE
                }
            }
        })

        binding.btnNext.setOnClickListener {
            val current = binding.viewPager.currentItem
            if (current < slides.size - 1) {
                binding.viewPager.currentItem = current + 1
            } else {
                navigateToLogin()
            }
        }

        binding.btnSkip.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        // ✅ Simpan flag bahwa onboarding sudah selesai
        getSharedPreferences(PREF_NAME, MODE_PRIVATE)
            .edit()
            .putBoolean(KEY_ONBOARDING_DONE, true)
            .apply()

        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }

    private fun setupDotIndicators() {
        val dots = Array(slides.size) { ImageView(this) }
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply { setMargins(8, 0, 8, 0) }

        dots.forEach { dot ->
            dot.setImageDrawable(
                ContextCompat.getDrawable(this, android.R.drawable.presence_invisible)
            )
            dot.layoutParams = params
            binding.layoutDots.addView(dot)
        }
    }

    private fun setCurrentDotIndicator(position: Int) {
        val count = binding.layoutDots.childCount
        for (i in 0 until count) {
            val dot = binding.layoutDots.getChildAt(i) as ImageView
            dot.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    if (i == position) android.R.drawable.presence_online
                    else android.R.drawable.presence_invisible
                )
            )
        }
    }
}