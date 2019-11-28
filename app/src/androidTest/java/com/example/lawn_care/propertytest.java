package com.example.lawn_care;

import android.accessibilityservice.AccessibilityService;
import android.app.Instrumentation;
import android.os.SystemClock;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;


@RunWith(AndroidJUnit4ClassRunner.class)
public class propertytest {


    private static String A = "1234 aws";
    private static String B = "Can";
    private static String C = "123";

    @Rule
    public ActivityTestRule<addProperty> mActivityRule = new ActivityTestRule<>(addProperty.class);

    //testing on create
    // test reset method.
    @Test
    public void ResetTest() {
        //create monitor to check current activity
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(dash.class.getName(), null, false);
        //bad attempt
        onView(withId(R.id.ET_address)).perform(typeText(A));
        onView(withId(R.id.ET_zipCode)).perform(typeText(C));
        onView(withId(R.id.ET_city)).perform(typeText(B));
        onView(withId(R.id.btn_reset)).perform(click());

    //wait for fail
        SystemClock.sleep(1200);
} //try again



}
