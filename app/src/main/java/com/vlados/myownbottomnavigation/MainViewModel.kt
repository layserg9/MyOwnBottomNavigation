package com.vlados.myownbottomnavigation

import androidx.lifecycle.ViewModel


class MainViewModel: ViewModel() {
    private val repository = Repository.getInstance()

    fun getButtonName(contentType: Int): String {
        return when (contentType) {
            1 -> {
                 "Добавить животное"
            }
            2 -> {
                "Добавить работника"
            }
            3 -> {
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