package com.gaborpeto.androidexercise.postdetails;

import android.support.test.rule.ActivityTestRule;

import com.gaborpeto.androidexercise.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.gaborpeto.androidexercise.RecyclerViewMatcher.atPosition;
import static com.gaborpeto.androidexercise.presentation.TestPostDetailsViewPresenter.COMMENT_BODY;
import static com.gaborpeto.androidexercise.presentation.TestPostDetailsViewPresenter.COMMENT_EMAIL;
import static com.gaborpeto.androidexercise.presentation.TestPostDetailsViewPresenter.COMMENT_NAME;
import static com.gaborpeto.androidexercise.presentation.TestPostDetailsViewPresenter.POST_TITLE;
import static com.gaborpeto.androidexercise.presentation.TestPostDetailsViewPresenter.POST_BODY;


public class PostDetailsActivityTest {

    @Rule
    public ActivityTestRule<PostDetailsActivity> activityRule =
            new ActivityTestRule<>(PostDetailsActivity.class);

    @Test
    public void testActivity() throws InterruptedException {

        onView(withId(R.id.post_title)).check(matches(withText(POST_TITLE)));
        onView(withId(R.id.post_body)).check(matches(withText(POST_BODY)));

        assertCommentItemWith(COMMENT_NAME);
        assertCommentItemWith(COMMENT_EMAIL);
        assertCommentItemWith(COMMENT_BODY);
    }

    private void assertCommentItemWith(String text) {
        onView(withId(R.id.comment_list))
                .check(matches(atPosition(0, hasDescendant(withText(text)))));
    }

}
