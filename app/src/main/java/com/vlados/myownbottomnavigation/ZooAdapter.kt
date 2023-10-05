package com.vlados.myownbottomnavigation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ZooAdapter(private val deleteItem: (ZooItem) -> Boolean): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
   private var zooList = listOf<ZooItem>()

    companion object {
        private val ANIMALS_CONTENT = 1
        private val WORKERS_CONTENT = 2
        private val ALL_CONTENT = 3
    }

    override fun getItemViewType(position: Int): Int {
        return when (val item = zooList[position]) {
            is AnimalItem -> ANIMALS_CONTENT
            is WorkerItem -> WORKERS_CONTENT
            else -> ALL_CONTENT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val animalView =
            LayoutInflater.from(parent.context).inflate(R.layout.animal_item, parent, false)
        val workerView =
            LayoutInflater.from(parent.context).inflate(R.layout.worker_item, parent, false)
        return if (viewType == ANIMALS_CONTENT) {
            AnimalViewHolder(animalView, deleteItem)
        } else if (viewType == WORKERS_CONTENT) {
            WorkerViewHolder(workerView, deleteItem)
        } else AnimalViewHolder(View(parent.context), deleteItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        if (viewType == ANIMALS_CONTENT) {
            val correctViewHolder: AnimalViewHolder? =
                holder as? AnimalViewHolder
            val elementOfList = zooList[position] as AnimalItem
            correctViewHolder?.bind(elementOfList)
        } else if (viewType == WORKERS_CONTENT) {
            val correctViewHolder: WorkerViewHolder? =
                holder as? WorkerViewHolder
            val elementOfList = zooList[position] as WorkerItem
            correctViewHolder?.bind(elementOfList)
        }
    }

    override fun getItemCount(): Int {
        return zooList.size
    }

    fun updateList(list: List<ZooItem>) {
        zooList = list
        notifyDataSetChanged()
    }
}