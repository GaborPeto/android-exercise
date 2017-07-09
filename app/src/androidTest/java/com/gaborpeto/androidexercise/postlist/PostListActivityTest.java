package com.gaborpeto.androidexercise.postlist;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.gaborpeto.androidexercise.R;
import com.gaborpeto.androidexercise.postdetails.PostDetailsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.*;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.gaborpeto.androidexercise.RecyclerViewMatcher.atPosition;
import static com.gaborpeto.androidexercise.presentation.TestPostListViewPresenter.*;

@RunWith(AndroidJUnit4.class)
public class PostListActivityTest {

    @Rule
    public ActivityTestRule<PostListActivity> activityRule =
            new ActivityTestRule<>(PostListActivity.class);

    @Test
    public void activityShouldShowPostList_andLaunchesDetailsActivityOnPostSelection() throws InterruptedException {
        assertPostItemWith(TITLE);
        assertPostItemWith(BODY);

        Intents.init();
        onView(withId(R.id.post_list)).perform(actionOnItemAtPosition(0, click()));
        intended(hasComponent(PostDetailsActivity.class.getName()));
        Intents.release();

    }

    private void assertPostItemWith(String text) {
        onView(withId(R.id.post_list))
                .check(matches(atPosition(0, hasDescendant(withText(text)))));
    }

}
