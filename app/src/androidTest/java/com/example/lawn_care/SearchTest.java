package com.example.lawn_care;

import android.app.Instrumentation;
import android.os.SystemClock;
import android.view.SearchEvent;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.lawn_care.ui.notifications.NotificationsFragment;
import com.example.lawn_care.ui.search.SearchFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotNull;


@RunWith(AndroidJUnit4.class)
public class SearchTest {

    @Rule
    public ActivityTestRule<SignIn> mActivityRule = new ActivityTestRule<>(SignIn.class);

    private void ownerSignInSearch() {
        onView(withId(R.id.ET_loginEmail)).perform(typeText("johnsmith@gmail.com"));
        onView(withId(R.id.ET_loginPassword)).perform(typeText("password"));
        onView(withId(R.id.BTN_login)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.navigation_jobList)).perform(click());
    }

    //test to ensure fields and buttons show up
    @Test
    public void OwnerSearchCreate(){
        ownerSignInSearch();
        onView(withId(R.id.ET_searchWorkerQuery)).inRoot(isPlatformPopup()).check(matches(isDisplayed()));
        onView(withId(R.id.BTN_submitSearchWorkerQuery)).check(matches(isDisplayed()));
        onView(withId(R.id.SP_WorkerFilter)).check(matches(not(isDisplayed())));

        onView(withId(R.id.SW_SearchFilterWorkers)).perform(click());
        onView(withId(R.id.SP_WorkerFilter)).check(matches(isDisplayed()));
        onView(withId(R.id.ET_searchWorkerQuery)).check(matches(not(isDisplayed())));
    }

    //testing a search for owners
    @Test
    public void OwnerSearch() {
        ownerSignInSearch();

        onView(withId(R.id.BTN_submitSearchWorkerQuery)).perform(click());
        onView(withText("Julius Hooper")).check(matches(isDisplayed()));
        onView(withText("Gregory Magnus")).check(matches(isDisplayed()));

        onView(withId(R.id.ET_searchWorkerQuery)).perform(typeText("Julius"));
        onView(withId(R.id.BTN_submitSearchWorkerQuery)).perform(click());
        onView(withText("Julius Hooper")).check(matches(isDisplayed()));
        onView(withText("Gregory Magnus")).check(doesNotExist());
    }

    //testing a filter for owners
    @Test
    public void OwnerFilter(){
        ownerSignInSearch();
        onView(withId(R.id.BTN_submitSearchWorkerQuery)).perform(click());
        SystemClock.sleep(300);
        onView(withId(R.id.SW_SearchFilterWorkers)).perform(click());
        onView(withId(R.id.SP_WorkerFilter)).perform(click());
        onView(withText("Mowing")).perform(click());
        onView(withId(R.id.BTN_submitSearchWorkerQuery)).perform(click());
        SystemClock.sleep(300);
        onView(withText("Gregory Magnus")).check(matches(isDisplayed()));
    }

    //test worker profile redirect for owner
    @Test
    public void OwnerProfileRedirect(){
        //create monitor to check current activity
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(addWorkerProfile.class.getName(), null, false);

        ownerSignInSearch();
        onView(withId(R.id.ET_searchWorkerQuery)).perform(typeText("Julius"));
        onView(withId(R.id.BTN_submitSearchWorkerQuery)).perform(click());
        onView(withText("Julius Hooper")).perform(click());

        //wait for 5 seconds
        addWorkerProfile workerProfile = (addWorkerProfile) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 2000);
        assertNotNull("Profile page not shown",workerProfile);
    }

    private void workerSignInSearch() {
        onView(withId(R.id.ET_loginEmail)).perform(typeText("jhooper@yahoo.com"));
        onView(withId(R.id.ET_loginPassword)).perform(typeText("jhooper313"));
        onView(withId(R.id.BTN_login)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.navigation_jobList)).perform(click());
    }

    //testing a search for workers
    @Test
    public void WorkerSearch() {
        workerSignInSearch();

        onView(withId(R.id.BTN_submitSearchPropertiesQuery)).perform(click());
        onView(withText(containsString("Moonlight"))).check(matches(isDisplayed()));

        onView(withId(R.id.ET_searchPropertiesQuery)).perform(typeText("Kalamazoo"));
        onView(withId(R.id.BTN_submitSearchPropertiesQuery)).perform(click());
        //onView(withText(containsString("Lima Avenue"))).check(matches(isDisplayed()));
        onView(withText(containsString("Moonlight"))).check(doesNotExist());
    }

    //testing a filter for owners
    @Test
    public void WorkerFilter(){
        workerSignInSearch();
        onView(withId(R.id.BTN_submitSearchPropertiesQuery)).perform(click());
        SystemClock.sleep(300);
        onView(withId(R.id.SW_SearchFilterJobs)).perform(click());
        onView(withId(R.id.SP_JobFilters)).perform(click());
        onView(withText("Aeration")).perform(click());
        onView(withId(R.id.BTN_submitSearchPropertiesQuery)).perform(click());
        SystemClock.sleep(300);
        onView(withText(containsString("Moonlight"))).check(matches(isDisplayed()));
        onView(withText(containsString("Lima Avenue"))).check(doesNotExist());
    }

    //test worker profile redirect for owner
    @Test
    public void WorkerProfileRedirect(){
        //create monitor to check current activity
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(PropertyPage.class.getName(), null, false);

        workerSignInSearch();
        onView(withId(R.id.ET_searchPropertiesQuery)).perform(typeText("Kalamazoo"));
        onView(withId(R.id.BTN_submitSearchPropertiesQuery)).perform(click());
        onView(withText(containsString("Lima"))).perform(click());

        //wait for 5 seconds
        PropertyPage propertyPage = (PropertyPage) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 2000);
        assertNotNull("Profile page not shown",propertyPage);
    }
}
