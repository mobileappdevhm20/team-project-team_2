package com.mobileappdevelopment.themunichquiz

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mobileappdevelopment.themunichquiz.fragment.StartFragment
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StartFragmentTest {
    lateinit var navController: TestNavHostController
    @Before
    fun initialize() {
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.navigation)
        val scenario = launchFragmentInContainer<StartFragment>()
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }
    @Test
    fun testStartButton() {
        Espresso.onView(ViewMatchers.withId(R.id.button_start))
            .perform(ViewActions.click())
        Assert.assertEquals(navController.currentDestination?.id, R.id.loginFragment)
    }
}