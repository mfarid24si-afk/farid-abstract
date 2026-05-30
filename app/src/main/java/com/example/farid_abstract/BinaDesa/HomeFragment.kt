package com.example.farid_abstract.BinaDesa

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.farid_abstract.AuthActivity
import com.example.farid_abstract.Customer.Custom1Activity
import com.example.farid_abstract.Customer.Custom2Activity
import com.example.farid_abstract.RumusActivity
import com.example.farid_abstract.databinding.ActivityMainBinding // Sesuaikan jika nama file XML-nya berbeda
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Tombol rumus
        binding.btnRumus.setOnClickListener {
            val intent = Intent(requireContext(), RumusActivity::class.java)
            intent.putExtra("judul", "Rumus Bangun Ruang")
            intent.putExtra("deskripsi", "Halaman perhitungan bangun ruang")
            startActivity(intent)
        }
        // Tombol custom 1
        binding.btnCustom1.setOnClickListener {
            val intent = Intent(requireContext(), Custom1Activity::class.java)
            intent.putExtra("judul", "About Us")
            intent.putExtra("deskripsi", "Aplikasi ini dibuat untuk membantu pengguna menghitung bangun ruang dengan mudah dan cepat.")
            startActivity(intent)
        }

        // Tombol custom 2
        binding.btnCustom2.setOnClickListener {
            val intent = Intent(requireContext(), Custom2Activity::class.java)
            intent.putExtra("judul", "Information")
            intent.putExtra("deskripsi", "Halaman ini berisi informasi mengenai fitur dan cara penggunaan aplikasi.")
            startActivity(intent)
        }

        // Tombol logout
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya") { dialog, _ ->
                    dialog.dismiss()
                    val intent = Intent(requireContext(), AuthActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                    Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
