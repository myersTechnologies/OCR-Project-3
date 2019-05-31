
package com.openclassrooms.entrevoisins.neighbour_list;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.FavoriteNeighbour;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.favorites_list.FavoritesFragment;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
    }

    @Test
    public void myNeighboursList_selectAction_shouldShowDetailsActivity(){
        // Given : check there is items on neighbours list
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        //When : perform a click on RecyclerView item position 0
        onView(allOf(isDisplayed(), withId(R.id.list_neighbours)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //Then : TextView for neighbour name in Details Activity is displayed
        onView(ViewMatchers.withId(R.id.user_details_name)).check(matches(isDisplayed()));
    }

    @Test
    public void neighboursInDetailActivityTextView_shouldShowNeighbourName(){
        // Given : check there is items on neighbours list
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        //When : perform a click on RecyclerView item position 0
        onView(allOf(isDisplayed(), withId(R.id.list_neighbours)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //Then : TextView for neighbour name is displayed and filled with neighbour name
        onView(ViewMatchers.withId(R.id.user_details_name)).check(matches(isDisplayed()));
        onView(ViewMatchers.withText("Caroline")).check(matches(withText("Caroline")));
    }

    @Test
    public void favoritesFragment_shouldOnlyShowFavoritesNeighbours() {
        //Given : Click on add to favorites floating action button to add a favorite neighbour
        onView(allOf(isDisplayed(), withId(R.id.list_neighbours)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.add_favorite_fab)).check(matches(isDisplayed()));
        onView(withId(R.id.add_favorite_fab)).perform(click());
        //When : press toolbar home button to parent activity and get favorite neighbour fragment
        pressBack();
        FragmentTransaction favoritesFragment = favoritesFragment();
        favoritesFragment.commit();
        //Then : check if Textview displayed and filled with favorite neighbour name
        onView(allOf(withId(R.id.item_list_name), withText("Caroline"),
                childAtPosition(childAtPosition(withId(R.id.list_favorite_neighbours),
                        0), 1), isDisplayed())).check(matches(withText("Caroline")));
    }

    private FragmentTransaction favoritesFragment(){
        FragmentTransaction transaction = mActivity.getSupportFragmentManager()
                .beginTransaction().replace(R.id.main_content, new FavoritesFragment());
        return transaction;
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

}