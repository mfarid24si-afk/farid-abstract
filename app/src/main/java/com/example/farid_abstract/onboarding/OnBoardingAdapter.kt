package com.example.farid_abstract.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.farid_abstract.databinding.ItemOnboardingSlideBinding

data class OnBoardingSlide(
    val imageRes: Int,
    val title: String,
    val description: String
)

class OnBoardingAdapter(private val slides: List<OnBoardingSlide>) :
    RecyclerView.Adapter<OnBoardingAdapter.SlideViewHolder>() {

    inner class SlideViewHolder(val binding: ItemOnboardingSlideBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideViewHolder {
        val binding = ItemOnboardingSlideBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SlideViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SlideViewHolder, position: Int) {
        val slide = slides[position]
        holder.binding.apply {
            ivSlideImage.setImageResource(slide.imageRes)
            tvSlideTitle.text = slide.title
            tvSlideDesc.text = slide.description
        }
    }

    override fun getItemCount() = slides.size
}