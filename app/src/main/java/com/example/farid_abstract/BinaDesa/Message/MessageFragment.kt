package com.example.farid_abstract.BinaDesa.Message

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.farid_abstract.R
import com.example.farid_abstract.databinding.FragmentMessageBinding
import com.example.random.Message.MessageModel

class MessageFragment : Fragment() {
    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    private val messageList = listOf(
        MessageModel("Tentang Aplikasi (About)", "Informasi lengkap mengenai gerakan modernisasi administrasi Bina Desa.", R.drawable.ic_info),
        MessageModel("Kebijakan Privasi (Privacy Policy)", "Halaman mengenai cara aplikasi menjaga keamanan data digital warga.", R.drawable.ic_privacy),
        MessageModel("Syarat & Ketentuan", "Aturan dan prosedur peminjaman fasilitas bersama milik desa.", R.drawable.ic_terms),
        MessageModel("Pusat Bantuan", "Hubungi admin atau call center desa jika mengalami kendala sistem.", R.drawable.ic_help)
    )




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)

        // Hubungkan list data pesan Anda ke komponen ListView di XML
        val adapter = MessageAdapter(requireContext(), messageList)
        binding.listMessageItems.adapter = adapter

    }
    }
