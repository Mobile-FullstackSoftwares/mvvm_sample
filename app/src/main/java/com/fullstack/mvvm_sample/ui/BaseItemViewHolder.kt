package com.fullstack.mvvm_sample.ui


import android.view.View
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.fullstack.mvvm_sample.utils.AlertDialogUtility

/** created by ciaranSloan 06-03-2020 */
abstract class BaseItemViewHolder<T>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    abstract fun onBind(item: T)

}