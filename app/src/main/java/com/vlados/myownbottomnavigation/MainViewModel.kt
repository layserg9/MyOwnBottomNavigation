package com.vlados.myownbottomnavigation

import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {

    private val repository = Repository.getInstance()

    fun getButtonName(contentType: Int): String {
        return when (contentType) {
            ZooListContentType.ANIMALS_CONTENT -> {
                "Добавить животное"
            }
            ZooListContentType.WORKERS_CONTENT -> {
                "Добавить работника"
            }
            ZooListContentType.ALL_CONTENT -> {
                "Добавить существо"
            }
            else -> {
                "Ошибка в contentType"
            }
        }
    }

    fun getItems(contentType: Int): List<ZooItem> {
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