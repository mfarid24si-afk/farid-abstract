package com.example.farid_abstract.BinaDesa.Profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.farid_abstract.databinding.FragmentRiwayatBinding

class RiwayatFragment : Fragment() {

    private var _binding: FragmentRiwayatBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRiwayatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Panggil daftar kegiatan baru yang sudah sinkron dengan tema desa
        val dataKegiatan = KegiatanData.kegiatanList

        // 2. Pasang ke adapter visualnya
        val adapter = ProductAdapter(dataKegiatan) { kegiatanDipilih ->
            Toast.makeText(requireContext(), "Membuka detail: ${kegiatanDipilih.name}", Toast.LENGTH_SHORT).show()
        }

        // 3. Atur list jadi model kotak Grid 2 kolom yang rapi
        binding.rvKegiatanBinaDesa.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
