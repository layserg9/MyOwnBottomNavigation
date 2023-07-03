package com.vlados.myownbottomnavigation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnimalAdapter() : RecyclerView.Adapter<AnimalAdapter.AnimalHolder>(){
    val animalList = ArrayList<AnimalItem>()
    val callback = object : Listener{
        override fun onClick(random: RandomItem): Boolean {
            animalList.remove(random)
            notifyDataSetChanged()
            return true
        }
    }
    class AnimalHolder(item: View): RecyclerView.ViewHolder(item) {
        val imageView: ImageView = item.findViewById(R.id.image_view_animal)
        val classNameTextView: TextView = item.findViewById(R.id.class_text_view_animal)
        val pawsNameTextView: TextView = item.findViewById(R.id.paws_text_view_animal)
        fun create(animal: AnimalItem, callback: Listener){
            imageView.setImageResource(animal.imageId)
            classNameTextView.text = animal.className
            pawsNameTextView.text = animal.numberOfPaws
            itemView.setOnLongClickListener{callback.onClick(animal)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.animal_item, parent, false)
        return AnimalHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalHolder, position: Int) {
        holder.create(animalList[position], callback)
    }

    override fun getItemCount(): Int {
        return animalList.size
    }

    fun addAnimal(animal:AnimalItem){
        animalList.add(animal)
        notifyDataSetChanged()
    }

    fun addAnimals(listAnimal: List<AnimalItem>){
        animalList.addAll(listAnimal)
        notifyDataSetChanged()
    }
}
