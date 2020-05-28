package com.mobileappdevelopment.themunichquiz

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {
    @Test
    fun testSignupButton() {
        val scenario = launchFragmentInContainer<LoginFragment>()
        Espresso.onView(ViewMatchers.withId(R.id.button_signup))
            .perform(ViewActions.click())
    }
}