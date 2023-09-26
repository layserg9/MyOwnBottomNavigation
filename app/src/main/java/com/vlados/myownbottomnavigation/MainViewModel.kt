package com.vlados.myownbottomnavigation

import androidx.lifecycle.ViewModel


class MainViewModel: ViewModel() {
    companion object{
        const val ANIMALS_CONTENT = 1
        const val WORKERS_CONTENT = 2
        const val ALL_CONTENT = 3
    }

    private val repository = Repository.getInstance()

    fun getButtonName(contentType: Int): String {
        return when (contentType) {
            ANIMALS_CONTENT -> {
                 "Добавить животное"
            }
            WORKERS_CONTENT -> {
                "Добавить работника"
            }
            ALL_CONTENT -> {
                "Добавить существо"
            }
            else -> {
                "Ошибка в contentType"
            }
        }
    }

    fun getItems(contentType: Int): List<ZooItem>{
        return repository.getItems(contentType)
    }

    fun addItem(contentType: Int): List<ZooItem> {
        val newItem = repository.generateItem(contentType)
        repository.addItem(newItem)
        return repository.getItems(contentType)
    }

    fun deleteItem(item: ZooItem, contentType: Int): List<ZooItem> {
        repository.deleteItem(item)
        return repository.getItems(contentType)
    }
}