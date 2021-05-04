package tddmicroexercises.leaderboard

import org.junit.Test

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

import tddmicroexercises.leaderboard.TestData.driver_Nico_Rosberg
import tddmicroexercises.leaderboard.TestData.driver_Lewis_Hamilton
import tddmicroexercises.leaderboard.TestData.driver_Sebastian_Vettel

class LeaderboardTest {

    @Test
    fun itShouldSumThePoints() {
        val sampleLeaderboard1 = Leaderboard(TestData.race1, TestData.race2, TestData.race3)
        val results = sampleLeaderboard1.driverResults()
        assertTrue("results $results", results.containsKey("Lewis Hamilton"))
        assertEquals((18 + 18 + 25).toLong(), (results.get("Lewis Hamilton") as Int).toLong())
    }

    @Test
    fun isShouldFindTheWinner() {
        val sampleLeaderboard1 = Leaderboard(TestData.race1, TestData.race2, TestData.race3)
        val result = sampleLeaderboard1.driverRankings()
        assertEquals("Lewis Hamilton", result.get(0))
    }

    @Test
    fun testRacesThatIncludeSelfDrivingCar() {
        val selfDrivingCar: SelfDrivingCar = SelfDrivingCar("1.2", "Acme")
        val race1 = Race("Australian Grand Prix", selfDrivingCar, driver_Lewis_Hamilton, driver_Sebastian_Vettel)
        val race2 = Race("Malaysian Grand Prix", selfDrivingCar, driver_Sebastian_Vettel, driver_Lewis_Hamilton)
        val race3 = Race("Chinese Grand Prix", driver_Lewis_Hamilton, selfDrivingCar, driver_Sebastian_Vettel)
        val sampleLeaderboard1 = Leaderboard(race1, race2, race3)
        val result = sampleLeaderboard1.driverRankings()
        assertEquals("Self Driving Car - Acme (1.2)", result[0])
    }

    @Test
    fun testSelfDrivingAlgorithmChangedOk() {
        val selfDrivingCar: SelfDrivingCar = SelfDrivingCar("1.2", "Acme")
        selfDrivingCar.algorithmVersion = "1.3"
        val race1 = Race("Australian Grand Prix", selfDrivingCar, driver_Lewis_Hamilton, driver_Sebastian_Vettel)
        val race2 = Race("Malaysian Grand Prix", selfDrivingCar, driver_Sebastian_Vettel, driver_Lewis_Hamilton)
        val race3 = Race("Chinese Grand Prix", driver_Lewis_Hamilton, selfDrivingCar, driver_Sebastian_Vettel)
        val sampleLeaderboard1 = Leaderboard(race1, race2, race3)
        val result = sampleLeaderboard1.driverRankings()
        assertEquals("Self Driving Car - Acme (1.3)", result.get(0))
    }

    @Test
    fun itShouldKeepAllDriversWhenSamePoints() {
        val winDriver1 = Race("Australian Grand Prix", driver_Nico_Rosberg, driver_Lewis_Hamilton, driver_Sebastian_Vettel)
        val winDriver2 = Race("Malaysian Grand Prix", driver_Lewis_Hamilton, driver_Nico_Rosberg, driver_Sebastian_Vettel)

        val exEquoLeaderBoard = Leaderboard(winDriver1, winDriver2)

        val expectedDrivers = arrayListOf(driver_Nico_Rosberg.name, driver_Lewis_Hamilton.name, driver_Sebastian_Vettel.name)
        val rankings = exEquoLeaderBoard.driverRankings()
        assertEquals(expectedDrivers.size, rankings.size)
        assertTrue(rankings.containsAll(expectedDrivers))
        // note: the order of driver1 and driver2 is JDK/platform dependent
    }

    @Test
    fun topTwoDriverShouldHaveSamePoint() {
        val winDriver1 = Race("Australian Grand Prix", driver_Nico_Rosberg, driver_Lewis_Hamilton, driver_Sebastian_Vettel)
        val winDriver2 = Race("Malaysian Grand Prix", driver_Lewis_Hamilton, driver_Nico_Rosberg, driver_Sebastian_Vettel)

        val exEquoLeaderBoard = Leaderboard(winDriver1, winDriver2)

        val result = exEquoLeaderBoard.driverRankings()
        val driverResults = exEquoLeaderBoard.driverResults()
        val firstDriverName = result[0];
        val secondDriverName = result[1];

        assertTrue(driverResults.get(firstDriverName) == driverResults.get(secondDriverName))
    }

    @Test
    fun topTwoDriverShouldBeSortedInTheRightOrder() {
        val winDriver1 = Race("Australian Grand Prix", driver_Nico_Rosberg, driver_Lewis_Hamilton, driver_Sebastian_Vettel)
        val winDriver2 = Race("Malaysian Grand Prix", driver_Lewis_Hamilton, driver_Nico_Rosberg, driver_Sebastian_Vettel)

        val exEquoLeaderBoard = Leaderboard(winDriver1, winDriver2)
        val result = exEquoLeaderBoard.driverRankings()
        val firstDriverName = result[0];
        val secondDriverName = result[1];

        assertEquals( driver_Nico_Rosberg.name,  firstDriverName)
        assertEquals( driver_Lewis_Hamilton.name,  secondDriverName)

        // check drivers order is still correct when race order reversed
        val exEquoLeaderBoard2 = Leaderboard(winDriver2, winDriver1)
        val result2 = exEquoLeaderBoard2.driverRankings()
        val firstDriverName2 = result2[0];
        val secondDriverName2 = result2[1];

        assertEquals( driver_Nico_Rosberg.name,  firstDriverName2)
        assertEquals( driver_Lewis_Hamilton.name,  secondDriverName2)
    }


    @Test(expected = NoRacesException::class)
    fun noDriverInRaceShouldThrowException(){
        val leaderboard = Leaderboard()
        assertEquals(0, leaderboard.driverRankings().size)
    }

}
