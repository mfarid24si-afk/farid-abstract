package com.example.farid_abstract.BinaDesa.Profile

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.farid_abstract.databinding.FragmentProfileBinding
import com.google.android.material.tabs.TabLayoutMediator

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Siapkan Adapter khusus untuk Fragment di dalam Fragment (pakai childFragmentManager)
        val tabsAdapter = ProfileTabsAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)
        binding.viewPagerProfile.adapter = tabsAdapter

        // 2. Hubungkan TabLayout dengan ViewPager2
        TabLayoutMediator(binding.tabLayoutProfile, binding.viewPagerProfile) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Biodata"
                    // Ganti 'this' menjadi 'requireContext()'
                    tab.icon = ContextCompat.getDrawable(requireContext(), android.R.drawable.ic_menu_myplaces)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                }
                1 -> {
                    tab.text = "Riwayat"
                    // Ganti 'this' menjadi 'requireContext()'
                    tab.icon = ContextCompat.getDrawable(requireContext(), android.R.drawable.ic_menu_recent_history)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                }
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}