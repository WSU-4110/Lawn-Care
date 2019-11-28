package com.example.lawn_care;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class Deletetest {

    @Rule
    public ActivityTestRule<adminViewProperties> mActivityRule = new ActivityTestRule<>(adminViewProperties.class);
   @Test
           public void oncreateTest() {
       onView(withId(R.id.BTN_delete)).perform(click());
   }
}
