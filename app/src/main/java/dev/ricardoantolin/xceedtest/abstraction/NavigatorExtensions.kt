package dev.ricardoantolin.xceedtest.abstraction

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun FragmentManager.loadFragment(
    fragment: Fragment,
    containerIdRes: Int,
    addToBackStack: Boolean
) {
    beginTransaction()
        .replace(containerIdRes, fragment, fragment.javaClass.name)
        .applyIf(addToBackStack) { it.addToBackStack(fragment.javaClass.name) }
        .commit()
}