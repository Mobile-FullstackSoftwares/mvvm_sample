package com.fullstack.mvvm_sample.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView


abstract class BaseAdapter<T, VH : BaseItemViewHolder<T>> : RecyclerView.Adapter<VH>() {

    protected val items = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, @LayoutRes viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(viewType, parent, false)
        return getViewHolder(itemView, viewType)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = getLayoutId(position, items[position])

    fun removeItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    open fun addItems(data: List<T>) {
        this.items.clear()
        this.items.addAll(data)
        notifyDataSetChanged()
    }

    abstract fun getViewHolder(itemView: View, @LayoutRes layoutResId: Int): VH

    abstract fun getLayoutId(position: Int, item: T): Int

    fun getExistingData(): List<T> = this.items
}