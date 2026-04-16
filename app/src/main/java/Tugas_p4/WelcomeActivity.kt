package Tugas_p4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.farid_abstract.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("EMAIL")

        binding.tvWelcome.text = "Selamat Datang, $email"
    }
}