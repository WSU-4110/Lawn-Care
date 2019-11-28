package com.example.lawn_care;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.StringStartsWith.startsWith;


@RunWith(AndroidJUnit4.class)
public class SubmitTest {
    private static String A="1234";
    private static String B="Detroit";
    private static String F= "12";
    private static String C="12345";
    //private static String D= "12";


    @Rule
    public ActivityTestRule<addProperty> mActivityRule = new ActivityTestRule<>(addProperty.class);
    private String ViewAssertions‌​;
    private Instrumentation.ActivityMonitor activityMonitor;

    //testing on create

    //testing login attempt
    @Test
    public void onCreateTest(){

        onView(withId(R.id.ET_address)).perform(typeText(A));




        onView(withId(R.id.ET_city)).perform(typeText(B));
        onView(withId(R.id.spinner2))
                .perform(click());

        onData(hasToString(startsWith("MI")))
                .perform(click());
        onView(withId(R.id.ET_zipCode)).perform(typeText(C));

        onView(withId(R.id.ET_PropertySize)).perform(typeText(F));
        onView(withId(R.id.CB_clippings))
                .perform(click());
        onView(withId(R.id.CB_Yes))
                .perform(scrollTo(),click());

        onView(withId(R.id.btn_submit)).perform(scrollTo(), click());

        dash dashActivity = (dash) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 2000);
        assertNotNull("attempt succsesful",dashActivity);
    }
}
