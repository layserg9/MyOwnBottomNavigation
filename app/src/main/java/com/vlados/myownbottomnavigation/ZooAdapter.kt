package com.vlados.myownbottomnavigation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ZooAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val zooList = ArrayList<ZooItem>()
    val callback = object : ILongClickListener{
        override fun onItemLongClick(random: ZooItem): Boolean {
            zooList.remove(random)
            notifyDataSetChanged()
            return true
        }
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
            AnimalViewHolder(animalView)
        } else if (viewType == WORKER_VIEW_TYPE) {
            WorkerViewHolder(workerView)
        } else AnimalViewHolder(View(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        if (viewType == ANIMAL_VIEW_TYPE) {
            val correctViewHolder: AnimalViewHolder? =
                holder as? AnimalViewHolder
            val elementOfList = zooList[position] as AnimalItem
            correctViewHolder?.bind(elementOfList, callback)
        } else if (viewType == WORKER_VIEW_TYPE) {
            val correctViewHolder: WorkerViewHolder? =
                holder as? WorkerViewHolder
            val elementOfList = zooList[position] as WorkerItem
            correctViewHolder?.bind(elementOfList, callback)
        }
    }

    override fun getItemCount(): Int {
        return zooList.size
    }

    fun addZooListItem(random: ZooItem) {
        zooList.add(random)
        notifyDataSetChanged()
    }

    fun addZooListItems(listRandoms: List<ZooItem>) {
        zooList.addAll(listRandoms)
        notifyDataSetChanged()
    }
}

interface ILongClickListener {
    fun onItemLongClick(random: ZooItem): Boolean
}

const val ANIMAL_VIEW_TYPE = 1
const val WORKER_VIEW_TYPE = 2
const val ANOTHER_VIEW_TYPE = 3