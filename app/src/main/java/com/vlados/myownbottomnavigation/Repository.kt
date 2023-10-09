package com.vlados.myownbottomnavigation

class Repository() {

    private var zooList = mutableListOf<ZooItem>()

    init {
        addItem(generateItem(ZooListContentType.ANIMALS_CONTENT))
        addItem(generateItem(ZooListContentType.WORKERS_CONTENT))
    }

    fun getItems(contentId: ZooListContentType): List<ZooItem> {
        return when (contentId) {
            ZooListContentType.ANIMALS_CONTENT -> zooList.filter { item -> item is AnimalItem }
            ZooListContentType.WORKERS_CONTENT -> zooList.filter { item -> item is WorkerItem }
            else -> zooList
        }
    }

    fun addItem(item: ZooItem) {
        zooList.add(item)
    }

    fun getListItems(count: Int, contentType: ZooListContentType): List<ZooItem> {
        return when (contentType) {
            ZooListContentType.ANIMALS_CONTENT -> AnimalFactory().createMultipleAnimals(count)
            ZooListContentType.WORKERS_CONTENT -> WorkerFactory().createMultipleWorkers(count)
            else -> ZooFactory().createMultipleItems(count)
        }
    }

    fun generateItem(contentType: ZooListContentType): ZooItem {
        return getListItems(20, contentType).random()
    }

    fun deleteItem(item: ZooItem): List<ZooItem> {
        zooList.remove(item)
        return zooList
    }
}
