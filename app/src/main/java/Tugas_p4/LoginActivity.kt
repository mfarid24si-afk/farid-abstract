package Tugas_p4

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.farid_abstract.databinding.ActivityLoginBinding
import android.content.Intent
import com.example.farid_abstract.MainActivity
import com.example.farid_abstract.RumusActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()

            if (email.isNotEmpty()) {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putExtra("EMAIL", email)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}