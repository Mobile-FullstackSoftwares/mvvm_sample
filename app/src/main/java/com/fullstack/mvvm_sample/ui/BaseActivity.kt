package com.fullstack.mvvm_sample.ui

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.fullstack.mvvm_sample.extensions.hideKeyboard


abstract class BaseActivity<Binding : ViewBinding> : AppCompatActivity(){

    private var _binding: Binding? = null
    protected val binding: Binding get() = _binding!!

    protected abstract val viewModel: ViewModel

    abstract fun createBinding(): Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = createBinding()
        setContentView(binding.root)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_MOVE){
            hideKeyboard()
        }
        return super.onTouchEvent(event)
    }
}