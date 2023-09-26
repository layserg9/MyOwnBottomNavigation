package com.vlados.myownbottomnavigation

class Repository private constructor(){
    companion object{
        private var repository: Repository? = null
        fun getInstance(): Repository{
            if(repository == null){
                repository = Repository()
                return repository!!
            }
            return repository!!
        }
    }
    private var zooList = mutableListOf<ZooItem>()

    init {
        addItem(generateItem(1))
        addItem(generateItem(2))
    }

    fun getItems(contentId: Int): List<ZooItem> {
        return when (contentId) {
            1 -> zooList.filter { item -> item is AnimalItem }
            2 -> zooList.filter { item -> item is WorkerItem }
            else -> zooList
        }
    }

    fun addItem(item: ZooItem) {
        zooList.add(item)
    }

    fun getListItems(count: Int, contentType: Int): List<ZooItem> {
        if (contentType == 1) {
            return AnimalFactory().createMultipleAnimals(count)
        } else if (contentType == 2) {
            return WorkerFactory().createMultipleWorkers(count)
        } else return ZooFactory().createMultipleItems(count)
    }

    fun generateItem(contentType: Int): ZooItem {
        return getListItems(20, contentType).random()
    }

    fun deleteItem(item: ZooItem): List<ZooItem>{
        zooList.remove(item)
        return zooList
    }
}
