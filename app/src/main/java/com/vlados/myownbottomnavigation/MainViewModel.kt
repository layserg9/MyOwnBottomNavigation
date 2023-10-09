package com.vlados.myownbottomnavigation

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun getButtonName(contentType: ZooListContentType): String {
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

    fun getItems(contentType: ZooListContentType): List<ZooItem> {
        return repository.getItems(contentType)
    }

    fun addItem(contentType: ZooListContentType): List<ZooItem> {
        val newItem = repository.generateItem(contentType)
        repository.addItem(newItem)
        return repository.getItems(contentType)
    }

    fun deleteItem(item: ZooItem, contentType: ZooListContentType): List<ZooItem> {
        repository.deleteItem(item)
        return repository.getItems(contentType)
    }
}