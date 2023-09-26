package com.vlados.myownbottomnavigation

class Repository private constructor() {
    companion object {
        private var repository: Repository? = null
        fun getInstance(): Repository {
            if (repository == null) {
                repository = Repository()
                return repository!!
            }
            return repository!!
        }
    }

    private var zooList = mutableListOf<ZooItem>()

    init {
        addItem(generateItem(ZooListContentType.ANIMALS_CONTENT))
        addItem(generateItem(ZooListContentType.WORKERS_CONTENT))
    }

    fun getItems(contentId: Int): List<ZooItem> {
        return when (contentId) {
            ZooListContentType.ANIMALS_CONTENT -> zooList.filter { item -> item is AnimalItem }
            ZooListContentType.WORKERS_CONTENT -> zooList.filter { item -> item is WorkerItem }
            else -> zooList
        }
    }

    fun addItem(item: ZooItem) {
        zooList.add(item)
    }

    fun getListItems(count: Int, contentType: Int): List<ZooItem> {
        return when (contentType) {
            ZooListContentType.ANIMALS_CONTENT -> AnimalFactory().createMultipleAnimals(count)
            ZooListContentType.WORKERS_CONTENT -> WorkerFactory().createMultipleWorkers(count)
            else -> ZooFactory().createMultipleItems(count)
        }
    }

    fun generateItem(contentType: Int): ZooItem {
        return getListItems(20, contentType).random()
    }

    fun deleteItem(item: ZooItem): List<ZooItem> {
        zooList.remove(item)
        return zooList
    }
}
