package com.example.sportapp.ui.main

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.sportapp.R

private val TAB_TITLES = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2,
        R.string.tab_text_2
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Log.i("TAB", position.toString())

        return when (position) {
            0 -> {
                exercises_list()
            }
            1 -> Stoper()
            else -> {
                return profil()
            }
        }

    }

    override fun getPageTitle(position: Int): CharSequence? {
        Log.i("TAB2", position.toString())
        return context.resources.getString(TAB_TITLES[position])
        /*return when (position) {
            0 -> "First 111111"
            1 -> "Second Tab"
            else -> {
                return "Third Tab"
            }
        }*/

    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 3
    }

}