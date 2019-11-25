package com.example.lawn_care;

import android.app.Instrumentation;
import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(AndroidJUnit4.class)
public class SignInTest {

    @Rule
    public ActivityTestRule<SignIn> mActivityRule = new ActivityTestRule<>(SignIn.class);

    @Test
    public void LoginTest() {
        //create monitor to check current activity
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(dash.class.getName(), null, false);
        onView(withId(R.id.ET_loginEmail)).perform(typeText("johnsmith@gmail.com"));
        onView(withId(R.id.ET_loginPassword)).perform(typeText("password"));
        onView(withId(R.id.BTN_login)).perform(click());
        //wait for 5 seconds
        dash dashActivity = (dash) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull("Login Failed",dashActivity);
    }
}
