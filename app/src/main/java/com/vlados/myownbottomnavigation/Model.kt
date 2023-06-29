package com.vlados.myownbottomnavigation


open class Animal(
    val numberOfPaws: Int,
    var hasHooves: Boolean,
): IRunnable, IFeedable {
    override fun run() {
        println("${this.javaClass.simpleName} умеет бегать")
    }
    override fun feed() {
        println("${this.javaClass.simpleName} можно накормить")
    }
}

open class Bird(
    val beakColor: String,
    var canFly: Boolean): Animal(2, false), IFlyable {
    override fun fly() {
        println("${this.javaClass.simpleName} умеет летать")
    }
    override fun run() {
        println("${this.javaClass.simpleName} НЕ умеет бегать")
    }
}


open class Worker(
    eyeColor: String,
    heirColor: String,
    val numberOfFingers: Int,
    hasBeard: Boolean,
    clothingSize: Int
): Man(eyeColor, heirColor, hasBeard, clothingSize), IWorkable {
    open fun doSpecificWork() {}
    override fun work(){
        println("${this.javaClass.simpleName} работает")
        doSpecificWork()
    }
}


open class Man(
    val eyeColor: String,
    val heirColor: String,
    var hasBeard: Boolean,
    val clothingSize: Int): ITalkable, IRunnable {
    override fun talk() {
        println("${this.javaClass.simpleName} умеет говорить")    }
    override fun run() {
        println("${this.javaClass.simpleName} умеет бегать")
    }
}






//---------------------- Фабрика работников
class WorkerFactory constructor(){

    fun createWorker(name: String): Worker {
        if (name == "Security") {
            return Security("Красные глаза", "222", 3, true, 50)
        } else if (name == "Caretaker") {
            return Caretaker("Заботливые глаза", "222", 3, true, 50)
        } else if (name == "Trainer") {
            return Trainer("Сердитые глаза", "222", 3, true, 50)
        } else return Worker("Хитрые глаза", "222", 3, true, 50)
    }

    fun createMultipleWorkers(count: Int): List<WorkerItem> {

        val resultList = mutableListOf<WorkerItem>()
        val randomNames = listOf("Security", "Caretaker", "Trainer", "some name")
        for (i in 0..count) {
            val worker: Worker = createWorker(randomNames.random())
            val imageId = getWorkerImage(worker.javaClass.simpleName)
            val workerItem = WorkerItem(imageId, worker.javaClass.simpleName, worker.eyeColor)
            resultList.add(workerItem)
        }
        return resultList
    }

    private fun getWorkerImage(name: String): Int {
        if (name == "Security") {
            return R.drawable.security
        } else if (name == "Caretaker") {
            return R.drawable.care_taker
        } else if (name == "Trainer") {
            return R.drawable.trainer
        } else return R.drawable.other_guy
    }
}

//----------------------Фабрика животных
class AnimalFactory constructor(){

    fun createAnimal(name: String): Animal{
        if (name == "Elephant"){
            return Elephant(4, false)
        } else if (name == "Wolf"){
            return Wolf(4, false)
        } else if (name == "Eagle"){
            return Eagle("grey", true)
        } else if (name == "Pigeon"){
            return Bird("orange", true)
        } else return Animal(4, true)
    }
    fun createMultipleAnimals(count: Int): List<AnimalItem> {

        val resultList = mutableListOf<AnimalItem>()
        val randomNames = listOf("Elephant", "Wolf", "Eagle", "Pigeon", "someName")
        for (i in 0..count) {
            val animal: Animal = createAnimal(randomNames.random())
            val imageId = getAnimalImage(animal.javaClass.simpleName)
            val animalItem = AnimalItem(imageId, animal.javaClass.simpleName, animal.numberOfPaws.toString())
            resultList.add(animalItem)
        }
        return resultList
    }
    private fun getAnimalImage(name: String): Int {
        if (name == "Elephant") {
            return R.drawable.elephant
        } else if (name == "Eagle") {
            return R.drawable.eagle
        } else if (name == "Pigeon") {
            return R.drawable.pigeon
        } else if (name == "Wolf") {
            return R.drawable.wolf }
        else return R.drawable.random_animal
    }
}

////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
/////////////////ФАБРИКА РАНДОМОВ///////////////////////////////
class RandomFactory constructor(){
    private val animalFactory = AnimalFactory()
    private val workersFactory = WorkerFactory()
    fun createMultipleItems (count: Int): List<RandomItem>{
        val randomList = mutableListOf<RandomItem>()
        val animalsList = animalFactory.createMultipleAnimals(count)
        val workersList = workersFactory.createMultipleWorkers(count)
        randomList.addAll(animalsList)
        randomList.addAll(workersList)
        randomList.shuffle()
        return randomList.take(count)
    }

}



////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////



//_____________________ ЖИВОТНЫЕ

class Elephant constructor(
    numberOfPaws: Int,
    hasHooves: Boolean
) : Animal
    (numberOfPaws, hasHooves) {
}

class Wolf constructor(
    numberOfPaws: Int,
    hasHooves: Boolean
) : Animal
    (numberOfPaws, hasHooves) {
    fun pacan(){
        println("${this.javaClass.simpleName} знает пацанские цитаты")
    }
}

class Eagle constructor(beakColor: String, canFly: Boolean)
    : Bird(beakColor, canFly){
}


//__________ Работники
class Security constructor(
    eyeColor: String,
    heirColor: String,
    numberOfFingers: Int,
    hasBeard: Boolean,
    clothingSize: Int
) : Worker(eyeColor, heirColor, numberOfFingers, hasBeard, clothingSize) {
    override fun doSpecificWork () { println("${this.javaClass.simpleName} охраняет")
    }
}

class Caretaker constructor(
    eyeColor: String,
    heirColor: String,
    numberOfFingers: Int,
    hasBeard: Boolean,
    clothingSize: Int
) : Worker(eyeColor, heirColor, numberOfFingers, hasBeard, clothingSize) {
    override fun doSpecificWork () { println("${this.javaClass.simpleName} делает заботливые штуки")
    }
}

class Trainer constructor(
    eyeColor: String,
    heirColor: String,
    numberOfFingers: Int,
    hasBeard: Boolean,
    clothingSize: Int
) : Worker(eyeColor, heirColor, numberOfFingers, hasBeard, clothingSize) {
    override fun doSpecificWork() {
        println("${this.javaClass.simpleName} дрессирует львов, у него $numberOfFingers пальцев")
    }
}


interface IWorkable {
    fun work()
}

interface ITalkable {
    fun talk()
}

interface IRunnable {
    fun run()
}

interface IFeedable {
    fun feed()
}
interface  IFlyable {
    fun fly()
}

