package com.example.chatapp


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val appCompatEditText = onView(
            allOf(
                withId(R.id.tb_username),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("user"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.tb_password),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("user123"), closeSoftKeyboard())

        val materialButton = onView(
            allOf(
                withId(R.id.btn_login), withText("Log in"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val recyclerView = onView(
            allOf(
                withId(R.id.connections),
                childAtPosition(
                    withClassName(`is`("android.widget.LinearLayout")),
                    1
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(1, click()))

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.messageinput),
                childAtPosition(
                    allOf(
                        withId(R.id.inputpanel),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(replaceText("Poslata poruka..."), closeSoftKeyboard())

        val materialButton2 = onView(
            allOf(
                withId(R.id.sendmessage),
                childAtPosition(
                    allOf(
                        withId(R.id.inputpanel),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            2
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        val materialButton3 = onView(
            allOf(
                withId(R.id.backbutton),
                childAtPosition(
                    allOf(
                        withId(R.id.headerpanel),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())

        val recyclerView2 = onView(
            allOf(
                withId(R.id.connections),
                childAtPosition(
                    withClassName(`is`("android.widget.LinearLayout")),
                    1
                )
            )
        )
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(1, click()))

        val materialButton4 = onView(
            allOf(
                withId(R.id.backbutton),
                childAtPosition(
                    allOf(
                        withId(R.id.headerpanel),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton4.perform(click())

        val recyclerView3 = onView(
            allOf(
                withId(R.id.connections),
                childAtPosition(
                    withClassName(`is`("android.widget.LinearLayout")),
                    1
                )
            )
        )
        recyclerView3.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.messageinput),
                childAtPosition(
                    allOf(
                        withId(R.id.inputpanel),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText4.perform(click())

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.messageinput),
                childAtPosition(
                    allOf(
                        withId(R.id.inputpanel),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText5.perform(replaceText("Meka poruka"), closeSoftKeyboard())

        val appCompatEditText6 = onView(
            allOf(
                withId(R.id.messageinput), withText("Meka poruka"),
                childAtPosition(
                    allOf(
                        withId(R.id.inputpanel),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText6.perform(click())

        val appCompatEditText7 = onView(
            allOf(
                withId(R.id.messageinput), withText("Meka poruka"),
                childAtPosition(
                    allOf(
                        withId(R.id.inputpanel),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText7.perform(replaceText("Neka poruka"))

        val appCompatEditText8 = onView(
            allOf(
                withId(R.id.messageinput), withText("Neka poruka"),
                childAtPosition(
                    allOf(
                        withId(R.id.inputpanel),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText8.perform(closeSoftKeyboard())

        val appCompatEditText9 = onView(
            allOf(
                withId(R.id.messageinput), withText("Neka poruka"),
                childAtPosition(
                    allOf(
                        withId(R.id.inputpanel),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText9.perform(click())

        val appCompatEditText10 = onView(
            allOf(
                withId(R.id.messageinput), withText("Neka poruka"),
                childAtPosition(
                    allOf(
                        withId(R.id.inputpanel),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText10.perform(replaceText("Neka poruka!"))

        val appCompatEditText11 = onView(
            allOf(
                withId(R.id.messageinput), withText("Neka poruka!"),
                childAtPosition(
                    allOf(
                        withId(R.id.inputpanel),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText11.perform(closeSoftKeyboard())

        val materialButton5 = onView(
            allOf(
                withId(R.id.sendmessage),
                childAtPosition(
                    allOf(
                        withId(R.id.inputpanel),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            2
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton5.perform(click())

        val materialButton6 = onView(
            allOf(
                withId(R.id.sendmessage),
                childAtPosition(
                    allOf(
                        withId(R.id.inputpanel),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            2
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton6.perform(click())

        val materialButton7 = onView(
            allOf(
                withId(R.id.backbutton),
                childAtPosition(
                    allOf(
                        withId(R.id.headerpanel),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton7.perform(click())

        val recyclerView4 = onView(
            allOf(
                withId(R.id.connections),
                childAtPosition(
                    withClassName(`is`("android.widget.LinearLayout")),
                    1
                )
            )
        )
        recyclerView4.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val materialButton8 = onView(
            allOf(
                withId(R.id.backbutton),
                childAtPosition(
                    allOf(
                        withId(R.id.headerpanel),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton8.perform(click())

        val recyclerView5 = onView(
            allOf(
                withId(R.id.connections),
                childAtPosition(
                    withClassName(`is`("android.widget.LinearLayout")),
                    1
                )
            )
        )
        recyclerView5.perform(actionOnItemAtPosition<ViewHolder>(2, click()))

        val materialButton9 = onView(
            allOf(
                withId(R.id.backbutton),
                childAtPosition(
                    allOf(
                        withId(R.id.headerpanel),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton9.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.user_name), withText("User User"),
                withParent(withParent(withId(R.id.linearLayout))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("User User")))

        val recyclerView6 = onView(
            allOf(
                withId(R.id.connections),
                childAtPosition(
                    withClassName(`is`("android.widget.LinearLayout")),
                    1
                )
            )
        )
        recyclerView6.perform(actionOnItemAtPosition<ViewHolder>(2, click()))

        val textView2 = onView(
            allOf(
                withId(R.id.messagetext), withText("Some text..."),
                withParent(withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Some text...")))

        val materialButton10 = onView(
            allOf(
                withId(R.id.backbutton),
                childAtPosition(
                    allOf(
                        withId(R.id.headerpanel),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton10.perform(click())

        val materialTextView = onView(
            allOf(
                withId(R.id.sign_out), withText("Sign out"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.linearLayout),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialTextView.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
