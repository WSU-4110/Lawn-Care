package com.example.lawn_care;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class ResetTest {
    private static String A="123";
    private static String B="Detroit";


    @Rule
    public ActivityTestRule<addProperty> mActivityRule = new ActivityTestRule<>(addProperty.class);
    private String ViewAssertions‌​;

    //testing on create

    //testing login attempt
    @Test
    public void onCreateTest(){
        //check if the email field works
        onView(withId(R.id.ET_address)).perform(typeText(A));

        //check if the password field works
        onView(withId(R.id.ET_city)).perform(typeText(B));

        onView(withId(R.id.btn_reset)).perform(scrollTo(), click());
    }



}
