package com.vlados.myownbottomnavigation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WorkerViewHolder(item: View, private val deleteItem: (ZooItem) -> Boolean): RecyclerView.ViewHolder(item) {
    val imageView: ImageView = item.findViewById(R.id.image_view_worker)
    val classTextView: TextView = item.findViewById(R.id.class_text_view_worker)
    val eyeColorTextView: TextView = item.findViewById(R.id.eye_color_text_view_worker)

    fun bind(worker: WorkerItem) {
        imageView.setImageResource(worker.imageId)
        classTextView.text = worker.className
        eyeColorTextView.text = worker.eyeColor
        itemView.setOnLongClickListener{deleteItem(worker)}
    }
}

