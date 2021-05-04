package tddmicroexercises.leaderboard

object TestData {

    var driver_Nico_Rosberg: Driver = Driver("Nico Rosberg", "DE")
    var driver_Lewis_Hamilton: Driver = Driver("Lewis Hamilton", "UK")
    var driver_Sebastian_Vettel: Driver = Driver("Sebastian Vettel", "DE")
    var selfDrivingCar_Acme: SelfDrivingCar = SelfDrivingCar("1.2", "Acme")

    var race1: Race = Race("Australian Grand Prix", driver_Nico_Rosberg, driver_Lewis_Hamilton, driver_Sebastian_Vettel)
    var race2: Race = Race("Malaysian Grand Prix", driver_Sebastian_Vettel, driver_Lewis_Hamilton, driver_Nico_Rosberg)
    var race3: Race = Race("Chinese Grand Prix", driver_Lewis_Hamilton, driver_Nico_Rosberg, driver_Sebastian_Vettel)
}
