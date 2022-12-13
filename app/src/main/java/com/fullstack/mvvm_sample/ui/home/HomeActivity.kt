package com.fullstack.mvvm_sample.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fullstack.mvvm_sample.databinding.ActivityHomeBinding
import com.fullstack.mvvm_sample.databinding.WeatherCardBinding
import com.fullstack.mvvm_sample.ui.BaseActivity
import com.fullstack.mvvm_sample.utils.customBottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    override val viewModel: HomeViewModel by viewModels()
    override fun createBinding(): ActivityHomeBinding =
        ActivityHomeBinding.inflate(layoutInflater)
    private val adapter = ExampleAdapter{
        customBottomSheetDialog(this)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNetworkData()
        binding.rvMain.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvMain.adapter = adapter
        onSwipeDeleteRequest()
    }

    private fun onSwipeDeleteRequest(){

        class ItemTouchCallBack(private val adapter: ExampleAdapter) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true // we don't want drag to move functionality
            }

            override fun isLongPressDragEnabled(): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                var position = viewHolder.adapterPosition
            }
        }

        val itemTouchHelper = ItemTouchHelper(ItemTouchCallBack(adapter))
        itemTouchHelper.attachToRecyclerView(binding.rvMain)
    }
}

class ExampleAdapter(private val onClick: (String)->Unit) : RecyclerView.Adapter<ExampleAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        WeatherCardBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(onClick)
    }

    override fun getItemCount(): Int = 10

    class ViewHolder(private val root: WeatherCardBinding) : RecyclerView.ViewHolder(root.root){
        fun bind(onClick: (String)->Unit){
            root.apply {
                root.setOnClickListener { onClick("") }
            }
        }
    }
}
