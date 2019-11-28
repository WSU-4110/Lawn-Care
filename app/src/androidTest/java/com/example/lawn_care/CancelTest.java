package com.example.lawn_care;

import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertNotNull;


@RunWith(AndroidJUnit4.class)
public class CancelTest {

    @Rule
    public ActivityTestRule<addProperty> mActivityRule = new ActivityTestRule<>(addProperty.class);
    private Instrumentation.ActivityMonitor activityMonitor;

    @Test
    public void onCreateTest(){


        onView(withId(R.id.btn_reset)).perform(scrollTo(), click());
       dash dashActivity = (dash) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 2000);
        assertNotNull("attempt succsesful",dashActivity);
    }

}
