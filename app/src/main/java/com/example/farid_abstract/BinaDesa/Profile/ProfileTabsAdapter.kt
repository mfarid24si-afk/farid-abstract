package com.example.farid_abstract.BinaDesa.Profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProfileTabsAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2 // Kita buat 2 Tab dulu (Biodata & Riwayat)

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BiodataFragment() // Tab Pertama memanggil Biodata
            1 -> RiwayatFragment() // Tab Kedua memanggil Riwayat
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}
