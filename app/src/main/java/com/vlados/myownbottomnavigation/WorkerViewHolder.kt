package com.vlados.myownbottomnavigation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vlados.myownbottomnavigation.databinding.AnimalItemBinding
import com.vlados.myownbottomnavigation.databinding.WorkerItemBinding

class WorkerViewHolder(item: View, private val deleteItem: (ZooItem) -> Boolean): RecyclerView.ViewHolder(item) {
    private var viewBinding = WorkerItemBinding.bind(item)
    val imageView: ImageView = viewBinding.imageViewWorker
    val classTextView: TextView = viewBinding.classTextViewWorker
    val eyeColorTextView: TextView = viewBinding.eyeColorTextViewWorker

    fun bind(worker: WorkerItem) {
        imageView.setImageResource(worker.imageId)
        classTextView.text = worker.className
        eyeColorTextView.text = worker.eyeColor
        itemView.setOnLongClickListener{deleteItem(worker)}
    }
}

