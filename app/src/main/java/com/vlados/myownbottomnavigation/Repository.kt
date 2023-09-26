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

        const val ANIMALS_CONTENT = 1
        const val WORKERS_CONTENT = 2
    }

    private var zooList = mutableListOf<ZooItem>()

    init {
        addItem(generateItem(ANIMALS_CONTENT))
        addItem(generateItem(WORKERS_CONTENT))
    }

    fun getItems(contentId: Int): List<ZooItem> {
        return when (contentId) {
            ANIMALS_CONTENT -> zooList.filter { item -> item is AnimalItem }
            WORKERS_CONTENT -> zooList.filter { item -> item is WorkerItem }
            else -> zooList
        }
    }

    fun addItem(item: ZooItem) {
        zooList.add(item)
    }

    fun getListItems(count: Int, contentType: Int): List<ZooItem> {
        return when (contentType) {
            ANIMALS_CONTENT -> AnimalFactory().createMultipleAnimals(count)
            WORKERS_CONTENT -> WorkerFactory().createMultipleWorkers(count)
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
