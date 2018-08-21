package com.example.marcin.registrationvisitapp;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class AndroidFragmentAddVisit {
    @Rule public ActivityTestRule<MainActivity> mainActivityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void addVisitButton(){
        onView(withId(R.id.addVisit)).perform(click());
        onView(withId(R.id.dialog_view))
                .check(matches(isDisplayed()));
    }
}
