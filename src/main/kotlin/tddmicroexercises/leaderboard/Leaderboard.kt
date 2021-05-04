package tddmicroexercises.leaderboard

import kotlin.Comparator

class Leaderboard(vararg races: Race) {

    private val races: List<Race>

    init {
        if(races.isEmpty()) throw NoRacesException()
        this.races = arrayListOf(*races)
    }

    fun driverResults(): Map<String, Int> {
        return races.flatMap { it.raceResult }
            .groupBy { it.first.name }
            .mapValues { entry -> entry.value.sumBy { it.second } }
    }

    //equally ranked driver are sorted by name descending
    fun driverRankings(): List<String> {
        val results = driverResults()
        return results.keys.toList().sortedWith(DriverByPointsDescendingComparator(results))
    }

    private class DriverByPointsDescendingComparator constructor(private val results: Map<String, Int>) :
        Comparator<String> {
            override fun compare(driverName1: String, driverName2: String): Int = when {
                results[driverName1] != results[driverName2] -> results[driverName2]!!.compareTo(results[driverName1]!!)
                driverName1 != driverName2 -> driverName2.compareTo(driverName1)
                else -> 0
            }
    }

}
