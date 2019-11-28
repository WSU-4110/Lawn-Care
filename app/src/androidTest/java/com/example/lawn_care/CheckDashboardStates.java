package com.example.lawn_care;

import android.os.SystemClock;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class CheckDashboardStates {
    @Rule
    public ActivityTestRule<SignIn> mActivityRule = new ActivityTestRule<>(SignIn.class);

    private void loginOwner(){
        onView(withId(R.id.ET_loginEmail)).perform(typeText("johnsmith@gmail.com"));
        onView(withId(R.id.ET_loginPassword)).perform(typeText("password"));
        onView(withId(R.id.BTN_login)).perform(click());
        SystemClock.sleep(1200);
    }

    @Test
    public void dashOwnerStateTest(){
        loginOwner();
        onView(withId(R.id.btn_addYourData)).check(matches(withText("Add a property")));
        onView(withId(R.id.btn_viewYourData)).check(matches(withText("View your properties")));
        onView(withId(R.id.navigation_jobList)).perform(click());
        SystemClock.sleep(1200);
        onView(withId(R.id.text_jobText)).check(matches(withText("Search Workers")));
        onView((withId(R.id.BTN_submitSearchPropertiesQuery))).check(doesNotExist());
        onView(withId(R.id.BTN_submitSearchWorkerQuery)).perform(click());
        SystemClock.sleep(1200);
        onView(allOf(withText("Delete User"))).check(doesNotExist());
    }

    private void loginWorker(){
        onView(withId(R.id.ET_loginEmail)).perform(typeText("jhooper@yahoo.com"));
        onView(withId(R.id.ET_loginPassword)).perform(typeText("jhooper313"));
        onView(withId(R.id.BTN_login)).perform(click());
        SystemClock.sleep(1200);
    }

    @Test
    public void dashWorkerStateTest(){
        loginWorker();
        onView(withId(R.id.btn_addYourData)).check(matches(withText("Add your worker profile")));
        onView(withId(R.id.btn_viewYourData)).check(matches(withText("View your worker profile")));
        onView(withId(R.id.navigation_jobList)).perform(click());
        SystemClock.sleep(1200);
        onView(withId(R.id.text_jobText)).check(matches(withText("Search Jobs")));
        onView((withId(R.id.BTN_submitSearchWorkerQuery))).check(doesNotExist());
    }

    private void loginAdmin(){
        onView(withId(R.id.ET_loginEmail)).perform(typeText("admin@lawncare.com"));
        onView(withId(R.id.ET_loginPassword)).perform(typeText("admin"));
        onView(withId(R.id.BTN_login)).perform(click());
        SystemClock.sleep(1200);
    }

    @Test
    public void dashAdminStateTest(){
        loginAdmin();
        onView(withId(R.id.btn_addYourData)).check(matches(withText("View All Listings")));
        onView(withId(R.id.btn_viewYourData)).check(matches(withText("View All users")));
        onView(withId(R.id.navigation_jobList)).perform(click());
        SystemClock.sleep(1200);
        onView(withId(R.id.text_jobText)).check(matches(withText("Search Workers")));
        onView((withId(R.id.BTN_submitSearchPropertiesQuery))).check(doesNotExist());
        onView(withId(R.id.BTN_submitSearchWorkerQuery)).perform(click());
        SystemClock.sleep(1200);
        onView(allOf(withText("Delete User") ,isDisplayed()));
    }
}
