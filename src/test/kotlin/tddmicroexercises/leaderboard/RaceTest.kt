package tddmicroexercises.leaderboard

import org.junit.Assert
import org.junit.Test

class RaceTest {

    @Test
    fun isShouldCalculateDriverPoints() {
        val race = Race("TestRace", TestData.driver_Nico_Rosberg,TestData.driver_Lewis_Hamilton,TestData.driver_Sebastian_Vettel)
        Assert.assertEquals(25, race.getPoints(TestData.driver_Nico_Rosberg))
        Assert.assertEquals(18, race.getPoints(TestData.driver_Lewis_Hamilton))
        Assert.assertEquals(15, race.getPoints(TestData.driver_Sebastian_Vettel))
    }

    @Test(expected = TooManyDriversException::class)
    fun tooManyDriverInRaceShouldThrowException(){
        val testRace = Race("testRace", TestData.driver_Nico_Rosberg, TestData.driver_Lewis_Hamilton, TestData.driver_Sebastian_Vettel, TestData.selfDrivingCar_Acme)
        Assert.assertEquals(4,testRace.results)
    }

    @Test(expected = NoDriversException::class)
    fun noDriverInRaceShouldThrowException(){
        val testRace = Race("testRace")
        Assert.assertEquals(0,testRace.results)
    }

    @Test
    fun testGetDriverPosition() {
        val race = Race("TestRace", TestData.driver_Nico_Rosberg,TestData.driver_Lewis_Hamilton,TestData.driver_Sebastian_Vettel)
        Assert.assertEquals(1, race.position(TestData.driver_Nico_Rosberg))
        Assert.assertEquals(2, race.position(TestData.driver_Lewis_Hamilton))
        Assert.assertEquals(3, race.position(TestData.driver_Sebastian_Vettel))
    }

    @Test
    fun driverNotInRaceHasNoPosition() {
        val race = Race("TestRace", TestData.driver_Nico_Rosberg,TestData.driver_Lewis_Hamilton,TestData.driver_Sebastian_Vettel)
        Assert.assertEquals(0, race.position(TestData.selfDrivingCar_Acme))
    }
}
