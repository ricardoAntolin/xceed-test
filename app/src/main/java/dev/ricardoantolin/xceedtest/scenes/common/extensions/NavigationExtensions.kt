package dev.ricardoantolin.xceedtest.scenes.common.extensions

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction

fun FragmentActivity.navigateToActivity(
    classToStartIntent: Class<*>,
    extras: Bundle? = null,
    requestCode: Int? = null,
    inTransition: Int = 0,
    outTransition: Int = 0,
    shouldRemovePrevious: Boolean = false
) {
    val intent = Intent(this.applicationContext, classToStartIntent)
    extras?.let { intent.putExtras(extras) }

    requestCode?.let { startActivityForResult(intent, it) }
        ?: startActivity(intent)

    if (inTransition != 0 && outTransition != 0)
        overridePendingTransition(inTransition, outTransition)

    if(shouldRemovePrevious) finish()
}

fun FragmentActivity.pushFragment(
    fragment: Fragment,
    contentFrame: Int,
    addToBackStack: Boolean = false,
    anim: Int = 0,
    anim2: Int = 0,
    animOut: Int = 0,
    animOut2: Int = 0
) {
    val tag = fragment.javaClass.name
    supportFragmentManager
        .beginTransaction()
        .setCustomAnimation(anim, anim2, animOut, animOut2)
        .replace(contentFrame, fragment, tag)
        .applyIf(addToBackStack){ it.addToBackStack(tag) }
        .commit()

    if (contentFrame <= 0) {
        this.supportFragmentManager.executePendingTransactions()
    }
}

private fun FragmentTransaction.setCustomAnimation(
    anim: Int = 0,
    anim2: Int = 0,
    animOut: Int = 0,
    animOut2: Int = 0
): FragmentTransaction {
    return if (animOut > 0 && animOut2 > 0) {
        setCustomAnimations(anim, anim2, animOut, animOut2)
    } else {
        setCustomAnimations(anim, anim2)
    }
}