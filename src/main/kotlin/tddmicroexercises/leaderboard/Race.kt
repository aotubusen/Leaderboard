package tddmicroexercises.leaderboard

/**
 * Current implementation and points system only allows max of 3 drivers to participate in the race
 * it also assumes the the drivers are added to the race in the order in which they finished
 */

class Race(val name: String, vararg drivers: Driver){
    val results: List<Driver> //not removing, it could be in use by external client code
    private val driverNamePointMap: Map<Driver, Int>
    val raceResult : List<Pair<Driver, Int>> //Introduced to expose drivers finishing point

    init {
        if(drivers.isEmpty()) throw NoDriversException()
        if(drivers.size > POINTS.size) throw TooManyDriversException()

        this.results = arrayListOf(*drivers)
        var i = 0
        this.driverNamePointMap = drivers.associate{it to POINTS[i++]}
        this.raceResult = driverNamePointMap.toList()
    }

    fun position(driver: Driver): Int {
        return this.driverNamePointMap.keys.indexOf(driver) + 1
    }

    fun getPoints(driver: Driver): Int? {
        return  this.driverNamePointMap[driver]
    }

    // The function should not be in this class
    // not removing, it could be in use by external client code
    fun getDriverName(driver: Driver): String {
        return driver.name
    }

    override fun toString(): String {
        return name
    }

    companion object {
        private val POINTS = arrayOf(25, 18, 15)
    }
}