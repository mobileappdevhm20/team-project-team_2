package com.mobileappdevelopment.themunichquiz

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LobbyFragmentTest {
    lateinit var navController: TestNavHostController
    @Before
    fun initialize() {
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.navigation)
        navController.setCurrentDestination(R.id.lobbyFragment)
        val scenario = launchFragmentInContainer<LobbyFragment>()
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }
    @Test
    fun testAddFriendButton() {
        onView(withId(R.id.button_addfriend))
            .perform(click())
        assertEquals(navController.currentDestination?.id, R.id.addFriendFragment)
    }
    @Test
    fun testPlayWithFriendButton() {
        onView(withId(R.id.button_playw_friend))
            .perform(click())
        assertEquals(navController.currentDestination?.id, R.id.playWithFriendFragment)
    }
    @Test
    fun testPlayWithRandomButton() {
        onView(withId(R.id.button_playw_random))
            .perform(click())
        assertEquals(navController.currentDestination?.id, R.id.gamepage)
    }
    @Test
    fun testShowStatisticsButton() {
        onView(withId(R.id.button_stats))
            .perform(click())
        assertEquals(navController.currentDestination?.id, R.id.statisticsFragment)
    }
    @Test
    fun testShowRulesButton() {
        onView(withId(R.id.button_rules))
            .perform(click())
        assertEquals(navController.currentDestination?.id, R.id.rulesFragment)
    }
}