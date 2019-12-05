package com.example.lawn_care;

import android.accessibilityservice.AccessibilityService;
import android.app.Instrumentation;
import android.content.Context;
import android.os.SystemClock;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


@RunWith(AndroidJUnit4.class)
public class SignInTest {

    private static String email="johnsmith@gmail.com";
    private static String password="password";

    @Rule
    public ActivityTestRule<SignIn> mActivityRule = new ActivityTestRule<>(SignIn.class);

    //testing on create
    @Test
    public void onCreateTest(){
        //check if the email field works
        onView(withId(R.id.ET_loginEmail)).perform(typeText(email));
        onView(withId(R.id.ET_loginEmail)).check(matches(withText(email)));
        //check if the password field works
        onView(withId(R.id.ET_loginPassword)).perform(typeText(password));
        onView(withId(R.id.ET_loginPassword)).check(matches(withText(password)));
    }

    //testing login attempt
    @Test
    public void LoginTest() {
        //create monitor to check current activity
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(dash.class.getName(), null, false);
        //bad attempt
        onView(withId(R.id.ET_loginEmail)).perform(typeText(email));
        onView(withId(R.id.ET_loginPassword)).perform(typeText(password+"x"));
        onView(withId(R.id.BTN_login)).perform(click());

        //wait for fail
        SystemClock.sleep(1200);
        //try again
        InstrumentationRegistry.getInstrumentation().getUiAutomation()
                .performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
        SystemClock.sleep(100);
        onView(withId(R.id.ET_loginEmail)).perform(clearText());
        onView(withId(R.id.ET_loginPassword)).perform(clearText());

        //good attempt
        onView(withId(R.id.ET_loginEmail)).perform(typeText(email));
        onView(withId(R.id.ET_loginPassword)).perform(typeText(password));
        onView(withId(R.id.BTN_login)).perform(click());
        //wait for 5 seconds
        dash dashActivity = (dash) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 2000);
        assertNotNull("Login failed",dashActivity);
    }

    //testing sign up redirection
    @Test
    public void SignUpRedirectTest(){
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(SignUp.class.getName(), null, false);

        onView(withId(R.id.textView)).inRoot(isPlatformPopup()).perform(click());

        SignUp signUp= (SignUp) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 500);
        assertNotNull("Login Failed",signUp);
    }
}
