package com.example.farid_abstract.BinaDesa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.farid_abstract.databinding.FragmentAboutBinding
import com.google.android.material.chip.Chip


class AboutFragment : Fragment() {

    // 1. Siapkan variabel untuk View Binding
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 2. Ubah cara memanggil layout menggunakan Binding
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Gunakan 'context' bawaan fragment sebagai pengganti 'this' biar performanya sama persis dengan Activity
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)

                Toast.makeText(context, "Filter: ${chip.text}", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnKirimSaran.setOnClickListener {
            Toast.makeText(context, "Masukan Anda berhasil dikirim!", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        // 4. Bersihkan binding saat fragment dihancurkan agar hemat memori
        _binding = null
    }
}
