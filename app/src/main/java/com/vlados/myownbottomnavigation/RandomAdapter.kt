package com.vlados.myownbottomnavigation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class RandomAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val randomList = ArrayList<RandomItem>()

    override fun getItemViewType(position: Int): Int {
        return when (val item = randomList[position]) {
            is AnimalItem -> ANIMAL_VIEW_TYPE
            is WorkerItem -> WORKER_VIEW_TYPE
            else -> ANOTHER_VIEW_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val animalView = LayoutInflater.from(parent.context).inflate(R.layout.animal_item, parent, false)
        val workerView = LayoutInflater.from(parent.context).inflate(R.layout.worker_item, parent, false)
        return if (viewType == ANIMAL_VIEW_TYPE) {
            AnimalAdapter.AnimalHolder(animalView)
        } else if (viewType == WORKER_VIEW_TYPE){
            WorkerAdapter.WorkerHolder(workerView)
        } else AnimalAdapter.AnimalHolder(View(parent.context))
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        if ( viewType == ANIMAL_VIEW_TYPE) {
            val correctViewHolder: AnimalAdapter.AnimalHolder? = holder as? AnimalAdapter.AnimalHolder
            val elementOfList = randomList[position] as AnimalItem
            correctViewHolder?.create(elementOfList)
        } else if (viewType == WORKER_VIEW_TYPE){
            val correctViewHolder: WorkerAdapter.WorkerHolder? = holder as? WorkerAdapter.WorkerHolder
            val elementOfList = randomList[position] as WorkerItem
            correctViewHolder?.create(elementOfList)
           }
    }

    override fun getItemCount(): Int {
        return randomList.size
    }

    fun addRandom(random:RandomItem){
        randomList.add(random)
        notifyDataSetChanged()
    }
    fun addRandoms(listRandoms: List<RandomItem>){
        randomList.addAll(listRandoms)
        notifyDataSetChanged()
    }
}


const val ANIMAL_VIEW_TYPE = 1
const val WORKER_VIEW_TYPE = 2
const val ANOTHER_VIEW_TYPE = 3