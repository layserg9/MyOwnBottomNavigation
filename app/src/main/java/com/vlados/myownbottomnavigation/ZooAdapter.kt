package com.vlados.myownbottomnavigation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ZooAdapter(private val deleteItem: (ZooItem) -> Boolean): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
   private var zooList = listOf<ZooItem>()

    companion object{
       private const val ANIMAL_VIEW_TYPE = 1
       private const val WORKER_VIEW_TYPE = 2
       private const val ANOTHER_VIEW_TYPE = 3
    }

    override fun getItemViewType(position: Int): Int {
        return when (val item = zooList[position]) {
            is AnimalItem -> ANIMAL_VIEW_TYPE
            is WorkerItem -> WORKER_VIEW_TYPE
            else -> ANOTHER_VIEW_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val animalView =
            LayoutInflater.from(parent.context).inflate(R.layout.animal_item, parent, false)
        val workerView =
            LayoutInflater.from(parent.context).inflate(R.layout.worker_item, parent, false)
        return if (viewType == ANIMAL_VIEW_TYPE) {
            AnimalViewHolder(animalView, deleteItem)
        } else if (viewType == WORKER_VIEW_TYPE) {
            WorkerViewHolder(workerView, deleteItem)
        } else AnimalViewHolder(View(parent.context), deleteItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        if (viewType == ANIMAL_VIEW_TYPE) {
            val correctViewHolder: AnimalViewHolder? =
                holder as? AnimalViewHolder
            val elementOfList = zooList[position] as AnimalItem
            correctViewHolder?.bind(elementOfList)
        } else if (viewType == WORKER_VIEW_TYPE) {
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