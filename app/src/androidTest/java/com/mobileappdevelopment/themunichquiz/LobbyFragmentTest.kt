package com.mobileappdevelopment.themunichquiz

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LobbyFragmentTest {
    @Test
    fun testAddFriendButton() {
        val scenario = launchFragmentInContainer<LobbyFragment>()
        assertTrue(false)
        onView(withId(R.id.button_addfriend))
            .perform(click())
    }
    @Test
    fun testPlayWithFriendButton() {
        val scenario = launchFragmentInContainer<LobbyFragment>()
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