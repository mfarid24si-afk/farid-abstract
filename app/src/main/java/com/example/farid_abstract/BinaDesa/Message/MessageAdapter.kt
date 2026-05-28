package com.example.farid_abstract.BinaDesa.Message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.farid_abstract.databinding.ItemMessageBinding
import com.example.random.Message.MessageModel
import com.google.android.material.snackbar.Snackbar

class MessageAdapter(
    context: Context,
    private val Messages: List<MessageModel>
) : ArrayAdapter<MessageModel>(context, 0, Messages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Gunakan View Reusing (convertView) agar list tidak patah-patah dan hemat memori
        val binding: ItemMessageBinding
        val view: View

        if (convertView == null) {
            binding = ItemMessageBinding.inflate(LayoutInflater.from(context), parent, false)
            view = binding.root
            view.tag = binding
        } else {
            view = convertView
            binding = view.tag as ItemMessageBinding
        }

        val data = Messages[position]

        // Memuat gambar ikon ungunya dengan Glide
        Glide.with(context)
            .load(data.avatarUrl)
            .placeholder(android.R.drawable.ic_menu_gallery) // Gambar sementara saat loading
            .error(android.R.drawable.ic_delete)            // Gambar jika tautan gagal dimuat
            .into(binding.avatarImg)

        binding.textSender.text = data.senderName
        binding.textMessage.text = data.messageText

        view.setOnClickListener {
            Snackbar.make(
                parent,
                "Menu yang dipilih: ${data.senderName}",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        return view
    }
}
