package com.example.lawn_care;
import android.os.SystemClock;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.StringStartsWith.startsWith;


@RunWith(AndroidJUnit4.class)
public class SubmitTest {
    Random random = new Random();
    int randomInteger = random.nextInt(100);
    private String A= randomInteger + " Sto ct";
    private static String B="Can";
    private static String F= "480";
    private static String C="48189";
    private String owner_email = "a@a.com";
    private String owner_password = "a";

    @Rule
    public ActivityTestRule<SignIn> mActivityRule = new ActivityTestRule<>(SignIn.class);

    private void owner_sign_in() {
        onView(withId(R.id.ET_loginEmail)).perform(typeText(owner_email));
        onView(withId(R.id.ET_loginPassword)).perform(typeText(owner_password));
        onView(withId(R.id.BTN_login)).perform(click());
        SystemClock.sleep(1000);
    }

    @Test
    public void submitTest(){


        owner_sign_in();
        onView(withText("ADD A PROPERTY")).perform(click());



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

    }
}
