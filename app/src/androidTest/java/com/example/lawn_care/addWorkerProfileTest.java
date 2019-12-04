package com.example.lawn_care;

import android.os.SystemClock;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class addWorkerProfileTest {

    private String owner_email = "a@a.com";
    private String owner_password = "a";
    private String worker_email = "g@g.com";
    private String worker_password = "g";

    @Rule
    public ActivityTestRule<SignIn> mActivityRule = new ActivityTestRule<>(SignIn.class);
//    public ActivityTestRule<workOffered> mRule = new ActivityTestRule<>(workOffered.class);

    // log in as worker
    private void worker_sign_in(){
        onView(withId(R.id.ET_loginEmail)).perform(typeText(worker_email));
        onView(withId(R.id.ET_loginPassword)).perform(typeText(worker_password));
        onView(withId(R.id.BTN_login)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.btn_addYourData)).perform(click());
    }

    private void owner_sign_in(){
        onView(withId(R.id.ET_loginEmail)).perform(typeText(owner_email));
        onView(withId(R.id.ET_loginPassword)).perform(typeText(owner_password));
        onView(withId(R.id.BTN_login)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.navigation_jobList)).perform(click());
        onView(withId(R.id.ET_searchWorkerQuery)).perform(typeText("Gregory"));
        onView(withId(R.id.BTN_submitSearchWorkerQuery)).perform(click());
        onView(withText("Gregory Magnus")).perform(click());
    }


    //Submit Worker Review
    @Test
    public void submitWorkerReviewTest(){
        owner_sign_in();
        onView(withId(R.id.BTN_Review)).perform(click());
        onView(withId(R.id.BTN_addReview)).perform(click());
        onView(withId(R.id.ET_workerReviewDescription)).perform(typeText("Great Job"));
        onView(withId(R.id.BTN_addReview)).perform(click());
    }

    // Owner Can go Add Review to worker
    @Test
    public void addWorkerReview() {
        owner_sign_in();
        onView(withId(R.id.BTN_Review)).perform(click());
        onView(withId(R.id.BTN_addReview)).perform(click());
    }

    //Owner can see worker Review
    @Test
    public void workerReviewByOwner(){
        owner_sign_in();
        onView(withId(R.id.BTN_Review)).perform(click());
    }

    //worker can see their types of job
    @Test
    public void workerWorkOfferedTest(){
        worker_sign_in();
        onView(withText("Mowing")).check(matches(isDisplayed()));
    }

    // When worker login they can see their review
    @Test
    public void workerReviewViewTest(){
        worker_sign_in();
        onView(withId(R.id.BTN_Review)).perform(click());
        onView(withText("dghfghghfg")).check(matches(isDisplayed()));
    }

    // When worker login they can't add review
    @Test
    public void workerReviewButtonTest(){
        worker_sign_in();
        onView(withId(R.id.BTN_addReview)).check(matches(not(isDisplayed())));
    }

    //worker can edit their Profile details; like Their Description, Website
    @Test
    public void workerProfileEditButtonTest(){
        String description = "I have been a self-employed lawn Care specialist for the past 20 years. I have to of the line lawn mowing equipment and have mowed over 1000 lawns in my life";
        worker_sign_in();
        onView(withId(R.id.IV_Edit)).perform(click());
        onView(withId(R.id.ET_description)).perform(typeText(description));
        onView(withId(R.id.BTN_submit)).perform(click());

        onView(withId(R.id.TI_firstName)).check(matches(not(isDisplayed())));
        onView(withId(R.id.TI_lastName)).check(matches(not(isDisplayed())));
        onView(withId(R.id.TI_email)).check(matches(not(isDisplayed())));
        onView(withId(R.id.TI_Phone)).check(matches(not(isDisplayed())));
    }
}
