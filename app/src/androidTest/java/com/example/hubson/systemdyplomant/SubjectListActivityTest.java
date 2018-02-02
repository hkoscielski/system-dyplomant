package com.example.hubson.systemdyplomant;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.example.hubson.systemdyplomant.utils.SessionManager;
import com.example.hubson.systemdyplomant.view.declaration.DeclarationActivity;
import com.example.hubson.systemdyplomant.view.subjects.SubjectListActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SubjectListActivityTest {

    private static final int ITEM_POSITION = 10;

    @Rule
    public IntentsTestRule<SubjectListActivity> mActivityRule = new IntentsTestRule<>(SubjectListActivity.class);

    @Test
    public void chooseSubjectWhenUserLogIn() {
        SessionManager.getInstance(mActivityRule.getActivity()).setUserId(2);
        onView(withId(R.id.subject_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(ITEM_POSITION,
                                new ViewAction() {
                                    @Override
                                    public Matcher<View> getConstraints() {
                                        return null;
                                    }

                                    @Override
                                    public String getDescription() {
                                        return "Click on specific button";
                                    }

                                    @Override
                                    public void perform(UiController uiController, View view) {
                                        View button = view.findViewById(R.id.btn_choose);
                                        button.performClick();
                                    }
                                })
                );
        onView(withText("Wybór tematu")).check(matches(isDisplayed()));
        onView(withText("Tak")).perform(click());
        intended(hasComponent(DeclarationActivity.class.getName()));
    }
}
