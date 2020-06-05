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
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LobbyFragmentTest {
    @Test
    fun testAddFriendButton() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.navigation)
        val scenario = launchFragmentInContainer<LobbyFragment>()
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.button_addfriend))
            .perform(click())
        assertEquals(navController.currentDestination?.id, R.id.addFriendFragment)
    }
    @Test
    fun testPlayWithFriendButton() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.navigation)
        val scenario = launchFragmentInContainer<LobbyFragment>()
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.button_playw_friend))
            .perform(click())
    }
    @Test
    fun testPlayWithRandomButton() {
        val scenario = launchFragmentInContainer<LobbyFragment>()
        onView(withId(R.id.button_playw_random))
            .perform(click())
    }
    @Test
    fun testShowStatisticsdButton() {
        val scenario = launchFragmentInContainer<LobbyFragment>()
        onView(withId(R.id.button_stats))
            .perform(click())
    }
    @Test
    fun testShowRulesButton() {
        val scenario = launchFragmentInContainer<LobbyFragment>()
        onView(withId(R.id.button_rules))
            .perform(click())
    }
}