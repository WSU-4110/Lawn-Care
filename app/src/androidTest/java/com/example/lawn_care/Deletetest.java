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
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class Deletetest {

    private String owner_email = "a@a.com";
    private String owner_password = "a";
    private String worker_email = "g@g.com";
    private String worker_password = "g";

    @Rule
    public ActivityTestRule<SignIn> mActivityRule = new ActivityTestRule<>(SignIn.class);

    private void owner_sign_in(){
        onView(withId(R.id.ET_loginEmail)).perform(typeText(owner_email));
        onView(withId(R.id.ET_loginPassword)).perform(typeText(owner_password));
        onView(withId(R.id.BTN_login)).perform(click());
        SystemClock.sleep(1000);
    }

    //Property owner can delete their property
    @Test
    public void onDeleteTest()
    {
        owner_sign_in();
        onView(withText("VIEW YOUR PROPERTIES")).perform(click());
    onView(withText("DELETE0")).perform(click());

    }
}
