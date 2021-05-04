package tddmicroexercises.leaderboard

import org.junit.Assert
import org.junit.Test

class SelfDrivingCarTest{

    @Test
    fun testSelfDrivingCarCreateAsExpected(){
        val selfDrivingCar = SelfDrivingCar("1.2", "Acme")
        Assert.assertEquals("1.2", selfDrivingCar.algorithmVersion)
        Assert.assertEquals("Self Driving Car - Acme (1.2)", selfDrivingCar.name)
    }

    @Test
    fun testSelfDrivingCarAlgorithmChangedOk(){
        val selfDrivingCar = SelfDrivingCar("1.2", "Acme")
        selfDrivingCar.algorithmVersion = "1.3"
        Assert.assertEquals("1.3", selfDrivingCar.algorithmVersion)
        Assert.assertEquals("Self Driving Car - Acme (1.3)", selfDrivingCar.name)
    }

    @Test
    fun testSelfDrivingCarEqualsWorksAsExpected(){
        val selfDrivingCar = SelfDrivingCar("1.2", "Acme")
        val selfDrivingCar2 = SelfDrivingCar("1.2", "Acme")
        Assert.assertTrue(selfDrivingCar == selfDrivingCar2)
    }

}