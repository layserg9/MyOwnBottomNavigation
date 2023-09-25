package com.vlados.myownbottomnavigation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnimalViewHolder(item: View, private val deleteItem: (ZooItem) -> Boolean): RecyclerView.ViewHolder(item) {
    val imageView: ImageView = item.findViewById(R.id.image_view_animal)
    val classNameTextView: TextView = item.findViewById(R.id.class_text_view_animal)
    val pawsNameTextView: TextView = item.findViewById(R.id.paws_text_view_animal)
    val contentType = 1

    fun bind(animal: AnimalItem){
        imageView.setImageResource(animal.imageId)
        classNameTextView.text = animal.className
        pawsNameTextView.text = animal.numberOfPaws
        itemView.setOnLongClickListener{deleteItem(animal)}
    }
}
