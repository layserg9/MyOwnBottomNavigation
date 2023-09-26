package com.vlados.myownbottomnavigation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vlados.myownbottomnavigation.databinding.AnimalItemBinding

class AnimalViewHolder(item: View, private val deleteItem: (ZooItem) -> Boolean): RecyclerView.ViewHolder(item) {
    private val viewBinding = AnimalItemBinding.bind(item)

    fun bind(animal: AnimalItem){
        viewBinding.imageViewAnimal.setImageResource(animal.imageId)
        viewBinding.classTextViewAnimal.text = animal.className
        viewBinding.pawsTextViewAnimal.text = animal.numberOfPaws
        itemView.setOnLongClickListener{deleteItem(animal)}
    }
}
