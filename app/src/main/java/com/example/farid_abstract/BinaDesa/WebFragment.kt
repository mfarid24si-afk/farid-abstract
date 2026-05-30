package com.example.farid_abstract.BinaDesa

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.farid_abstract.AuthActivity
import com.example.farid_abstract.R
import com.example.farid_abstract.databinding.FragmentWebBinding

class WebFragment : Fragment() {
    private var _binding: FragmentWebBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 3. Konfigurasi internal mesin WebView lokal
        binding.webView.apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            webViewClient = WebViewClient()
        }

        // 4. Pengaktifan fungsi klik tombol muat tautan web
        binding.btnWebView.setOnClickListener {
            binding.webView.loadUrl("https://farid-peminjaman.alwaysdata.net/login")
        }

        binding.btnLogout.setOnClickListener {
            // Pastikan memanggil wadah "user_pref" yang sama agar datanya terhapus bersih saat keluar
            val sharedPref =
                requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putBoolean("isLogin", false) // Reset status menjadi false
            editor.apply()

            // Kembalikan user ke halaman login
            val intent = Intent(requireContext(), AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Mengosongkan binding saat view dihancurkan
    }
}