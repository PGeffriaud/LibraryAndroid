package fr.pierreg.library.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.pierreg.library.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class BookListActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<BookListActivity> activityTestRule = new ActivityTestRule<>(BookListActivity.class);

    @Test
    public void should_open_book_detail_on_click() throws Exception {
        onView(withId(R.id.book_list)).check(matches(isDisplayed())).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.book_detail_container)).check(matches(isDisplayed()));
        onView(withId(R.id.book_title)).check(matches(isDisplayed()));
        onView(withId(R.id.book_price)).check(matches(isDisplayed()));
        onView(withId(R.id.book_cover)).check(matches(isDisplayed()));
        onView(withId(R.id.book_synopsis)).check(matches(isDisplayed()));
    }


}
