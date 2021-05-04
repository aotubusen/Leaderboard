package tddmicroexercises.leaderboard

import kotlin.properties.Delegates

class SelfDrivingCar(private val _algorithmVersion: String, val company: String) : Driver(_algorithmVersion, company) {

    var algorithmVersion : String by Delegates.observable(_algorithmVersion) {
            _, _, new -> this.name = getName(new)
    }
    override var name: String = getName(algorithmVersion)

    private fun getName (algoVersion : String): String = "Self Driving Car - $company ($algoVersion)"
}