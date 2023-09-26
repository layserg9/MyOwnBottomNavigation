package com.vlados.myownbottomnavigation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vlados.myownbottomnavigation.databinding.AnimalItemBinding

class AnimalViewHolder(item: View, private val deleteItem: (ZooItem) -> Boolean): RecyclerView.ViewHolder(item) {
    private var viewBinding = AnimalItemBinding.bind(item)
    val imageView: ImageView = viewBinding.imageViewAnimal
    val classNameTextView: TextView = viewBinding.classTextViewAnimal
    val pawsNameTextView: TextView = viewBinding.pawsTextViewAnimal

    fun bind(animal: AnimalItem){
        imageView.setImageResource(animal.imageId)
        classNameTextView.text = animal.className
        pawsNameTextView.text = animal.numberOfPaws
        itemView.setOnLongClickListener{deleteItem(animal)}
    }
}
