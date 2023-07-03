package com.vlados.myownbottomnavigation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WorkerAdapter(): RecyclerView.Adapter<WorkerAdapter.WorkerHolder>() {
    val workerList = ArrayList<WorkerItem>()
    val callback = object : Listener {
        override fun onClick(random: RandomItem): Boolean {
            workerList.remove(random)
            notifyDataSetChanged()
            return true
        }
    }

    class WorkerHolder(item: View): RecyclerView.ViewHolder(item) {
        val imageView: ImageView = item.findViewById(R.id.image_view_worker)
        val classTextView: TextView = item.findViewById(R.id.class_text_view_worker)
        val eyeColorTextView: TextView = item.findViewById(R.id.eye_color_text_view_worker)

        fun create(worker: WorkerItem, callback: Listener) {
            imageView.setImageResource(worker.imageId)
            classTextView.text = worker.className
            eyeColorTextView.text = worker.eyeColor
            itemView.setOnClickListener{callback.onClick(worker)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.worker_item, parent, false)
        return WorkerHolder(view)
    }

    override fun onBindViewHolder(holder: WorkerHolder, position: Int) {
        holder.create(workerList[position], callback)
    }

    override fun getItemCount(): Int {
        return workerList.size
    }

    fun addWorker(worker: WorkerItem) {
        workerList.add(worker)
        notifyDataSetChanged()
    }

    fun addWorkers(listWorkers: List<WorkerItem>) {
        workerList.addAll(listWorkers)
        notifyDataSetChanged()
    }
}


