package com.example.lawn_care;
import android.os.SystemClock;

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
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.StringStartsWith.startsWith;


@RunWith(AndroidJUnit4.class)
public class ZipCodeTest {
    private static String l="123567";
    private static String m="Det";
    private static String n= "120";

    private static String E="123";
    private static String o="12967";
    private String owner_email = "a@a.com";
    private String owner_password = "a";
    private String worker_email = "g@g.com";
    private String worker_password = "g";

    @Rule
    public ActivityTestRule<SignIn> mActivityRule = new ActivityTestRule<>(SignIn.class);

    private void owner_sign_in() {
        onView(withId(R.id.ET_loginEmail)).perform(typeText(owner_email));
        onView(withId(R.id.ET_loginPassword)).perform(typeText(owner_password));
        onView(withId(R.id.BTN_login)).perform(click());
        SystemClock.sleep(1000);
    }

    @Test
    public void onCreateTest(){


        owner_sign_in();
        onView(withText("ADD A PROPERTY")).perform(click());



        onView(withId(R.id.ET_address)).perform(typeText(l));




        onView(withId(R.id.ET_city)).perform(typeText(m));
        onView(withId(R.id.spinner2))
                .perform(click());

        onData(hasToString(startsWith("MI")))
                .perform(click());
        onView(withId(R.id.ET_zipCode)).perform(typeText( E+ "x"));

        onView(withId(R.id.ET_PropertySize)).perform(typeText(n));
        onView(withId(R.id.CB_clippings))
                .perform(click());
        onView(withId(R.id.CB_Yes))
                .perform(scrollTo(),click());

        onView(withId(R.id.btn_submit)).perform(scrollTo(), click());


    }

    @Test
    public void onZip_CodeTest(){


        owner_sign_in();

        onView(withText("ADD A PROPERTY")).perform(click());



        onView(withId(R.id.ET_address)).perform(typeText(l));




        onView(withId(R.id.ET_city)).perform(typeText(m));
        onView(withId(R.id.spinner2))
                .perform(click());

        onData(hasToString(startsWith("MI")))
                .perform(click());
        onView(withId(R.id.ET_zipCode)).perform(typeText(o));

        onView(withId(R.id.ET_PropertySize)).perform(typeText(n));
        onView(withId(R.id.CB_clippings))
                .perform(click());
        onView(withId(R.id.CB_Yes))
                .perform(scrollTo(),click());

        onView(withId(R.id.btn_submit)).perform(scrollTo(), click());


    }
}
