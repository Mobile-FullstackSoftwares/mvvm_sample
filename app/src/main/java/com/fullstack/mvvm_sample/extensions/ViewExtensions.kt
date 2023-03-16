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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

/**
 * @param noOfColumns are columns which we want ot show in grid layout of recycler-view
 * Don't set the layoutManager explicitly when calling this function.
 * //Making cardImage Square so width and height should be same
 * it can be used as
 * binding.recyclerView.apply {
 *      val width = setColumns(CrossMintBuilder.numberOfColumns())
 * }
 * or val width = binding.recyclerView.setColumns(CrossMintBuilder.numberOfColumns())
 * -> root.cardImage.layoutParams.width = cardWidth
 * -> root.cardImage.layoutParams.height = cardWidth
 */
fun RecyclerView.setGridLayout(noOfColumns: Int) : Int {
    this.layoutManager = GridLayoutManager(this.context, noOfColumns)
    return this.context.resources.displayMetrics.widthPixels/noOfColumns
}

/**
 * This is extension for making pagination easier and don't have to worry about flow in Fragment or
 * activity. @param hasNextData is callback which will tell the us if we have next data.
 * Question can also be put why not check this value in callBack instead of sending here.
 * isLastVisible() is also expensive operation as its called every-time if Recycler view is SCROLL_STATE_IDLE
 * If we have nextItems then only this will be called and saves us the CPU cycles and memory.
 */
private fun RecyclerView.onLastItemVisible(
    hasNextData: () -> Boolean, 
    onLastItemCallBack: () -> Unit
) = this.addOnScrollListener(
    object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if ((newState == RecyclerView.SCROLL_STATE_IDLE) && hasNextData() && isLastVisible
                    (recyclerView)) {
                onLastItemCallBack.invoke()
            }
        }

        private fun isLastVisible(recyclerView: RecyclerView): Boolean {
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val pos = layoutManager.findLastVisibleItemPosition()
            val numItems: Int = recyclerView.adapter?.itemCount ?: 0
            return pos + 1 >= numItems
        }
    })


/**
 * This is extension for making pagination easier and don't have to worry about flow in Fragment or
 * activity. If checking that we have nextData is an expensive operation we can use this instead
 * with @param hasNextData.
 */
private fun RecyclerView.onLastItemVisible(
    onLastItemCallBack: () -> Unit
) = this.addOnScrollListener(
    object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if ((newState == RecyclerView.SCROLL_STATE_IDLE) && isLastVisible
                    (recyclerView)) {
                onLastItemCallBack.invoke()
            }
        }

        private fun isLastVisible(recyclerView: RecyclerView): Boolean {
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val pos = layoutManager.findLastVisibleItemPosition()
            val numItems: Int = recyclerView.adapter?.itemCount ?: 0
            return pos + 1 >= numItems
        }
    })
