package com.example.lawn_care;

import android.os.SystemClock;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

public class viewYourWorkerProfileTest {
    private String owner_email = "a@a.com";
    private String owner_password = "a";
    private String worker_email = "g@g.com";
    private String worker_password = "g";

    @Rule
    public ActivityTestRule<SignIn> mActivityRule = new ActivityTestRule<>(SignIn.class);

    // log in as worker
    private void worker_sign_in(){
        onView(withId(R.id.ET_loginEmail)).perform(typeText(worker_email));
        onView(withId(R.id.ET_loginPassword)).perform(typeText(worker_password));
        onView(withId(R.id.BTN_login)).perform(click());
        SystemClock.sleep(1000);
    }

    @Test
    public void logOutTest(){
        worker_sign_in();
        onView(withId(R.id.BTN_logout)).perform(click());
    }

    @Test
    public void workerFirstNameTest(){
        worker_sign_in();
        onView(withText("VIEW YOUR WORKER PROFILE")).perform(click());
        onView(withId(R.id.TV_workerName)).check(matches(withText(containsString("Gregory"))));
    }

    @Test
    public void workerLastNameTest(){
        worker_sign_in();
        onView(withText("VIEW YOUR WORKER PROFILE")).perform(click());
        onView(withId(R.id.TV_workerName)).check(matches(withText(containsString("Magnus"))));
    }

    @Test
    public void workerPhoneNumberTest(){
        worker_sign_in();
        onView(withText("VIEW YOUR WORKER PROFILE")).perform(click());
        onView(withId(R.id.TV_phoneNum)).check(matches(withText(containsString("2489990000"))));
    }

    @Test
    public void workerDescriptionTest(){
        worker_sign_in();
        onView(withText("VIEW YOUR WORKER PROFILE")).perform(click());
        onView(withId(R.id.TV_description)).check(matches(withText(containsString("I have been a self-employed lawn Care specialist forI have been a self-employed lawI hI have been a self-employed lawn Care specialist for the past 20 years. I have to of the line lawn mowing equipmen"
        ))));
    }

    @Test
    public void workerWebsiteTest(){
        worker_sign_in();
        onView(withText("VIEW YOUR WORKER PROFILE")).perform(click());
        onView(withId(R.id.TV_website)).check(matches(withText(containsString("null"))));

    }


}
