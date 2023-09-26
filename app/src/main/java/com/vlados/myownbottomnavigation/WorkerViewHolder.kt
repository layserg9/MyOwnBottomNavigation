package com.vlados.myownbottomnavigation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vlados.myownbottomnavigation.databinding.AnimalItemBinding
import com.vlados.myownbottomnavigation.databinding.WorkerItemBinding

class WorkerViewHolder(item: View, private val deleteItem: (ZooItem) -> Boolean): RecyclerView.ViewHolder(item) {
    private val viewBinding = WorkerItemBinding.bind(item)

    fun bind(worker: WorkerItem) {
        viewBinding.imageViewWorker.setImageResource(worker.imageId)
        viewBinding.classTextViewWorker.text = worker.className
        viewBinding.eyeColorTextViewWorker.text = worker.eyeColor
        itemView.setOnLongClickListener{deleteItem(worker)}
    }
}

