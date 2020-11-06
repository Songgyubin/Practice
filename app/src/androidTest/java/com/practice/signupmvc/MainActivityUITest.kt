package com.practice.signupmvc

import android.widget.DatePicker
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityUITest {

//    private lateinit var mDevice:UiDevice

    @get:Rule
    var myActivityActivityScenarioRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    /*@Before
    fun setUp(){
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }*/

    @Test
    fun start() {

        onView(withId(R.id.ed_email)).perform(typeText("th@naver.com"), closeSoftKeyboard())

        onView(withId(R.id.ed_pw)).perform(typeText("rbqls0427@S"), closeSoftKeyboard())

        onView(withId(R.id.ed_pw_check)).perform(typeText("rbqls0427@S"), closeSoftKeyboard())

        onView(withId(R.id.ed_nick)).perform(typeText("dkdkdkdkkddkdk"), closeSoftKeyboard())

        onView(withId(R.id.ed_birth)).perform(click())

        onView(withClassName(Matchers.equalTo(DatePicker::class.java.name))).check(matches(isDisplayed()))
        onView(withClassName(Matchers.equalTo(DatePicker::class.java.name))).perform(PickerActions.setDate(1953,4,12))
        onView(withText("OK")).perform(click())

        onView(withId(R.id.rb_man)).perform(click())

        onView(withId(R.id.cb_service)).perform(click())

        onView(withId(R.id.cb_marketing)).perform(click())

        onView(withId(R.id.btn_sign_up)).perform(click())
    }
}