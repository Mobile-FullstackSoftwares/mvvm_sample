package com.fullstack.mvvm_sample.extensions

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationBarView
import java.lang.ref.WeakReference

/**
 * This is soft/virtual keyboard hiding extension for Fragment and Activity
 */
fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    this.currentFocus?.let {
        hideKeyboard(it)
    }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}


/**
 * Show the view  (visibility = View.VISIBLE)
 */
fun View.visible() : View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

/**
 * Remove the view (visibility = View.GONE)
 */
fun View.hide() : View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}
/**
 * Remove the view (visibility = View.GONE)
 */
fun View.invisible() : View {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
    return this
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}


fun NavigationBarView.customSetupWithNavController(navController: NavController) {
    NavigationUI.customSetupWithNavController(this, navController)
}

/**
 * This function is slightly changed from the base implementation to solve the an issue which was
 * that when user open a fragment upon one of the bottom navigation then upon switching between fragments
 * bottom navigation was not being highlighted properly.
 */
fun NavigationUI.customSetupWithNavController(
    navigationBarView: NavigationBarView,
    navController: NavController
) {
    navigationBarView.setOnItemSelectedListener { item ->
        onNavDestinationSelected(
            item,
            navController
        )
        true // This is added to solve the above issue
    }
    val weakReference = WeakReference(navigationBarView)
    navController.addOnDestinationChangedListener(
        object : NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                val view = weakReference.get()
                if (view == null) {
                    navController.removeOnDestinationChangedListener(this)
                    return
                }
                //few lines were changed to solve the issue
                view.menu.forEach { item ->
                    if (destination.id == item.itemId){
                        item.isChecked = true
                    }
                }
            }
        })
}