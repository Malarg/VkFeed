package com.malarg.vkfeed.core.platform

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

abstract class BaseFragment : Fragment() {
    private lateinit var baseViewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseViewModel.toast.observe(this, Observer { event ->
            Toast.makeText(context, event, Toast.LENGTH_SHORT).show()
        })
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : BaseViewModel> getViewModel(ofClass: Class<T>): T {
        baseViewModel = ViewModelProviders.of(this)[ofClass]
        return baseViewModel as T
    }

    inline fun <reified T : BaseViewModel> getViewModel(): T {
        return getViewModel(T::class.java)
    }

    fun notify(value: Int) {
        Toast.makeText(context, value, Toast.LENGTH_SHORT).show()
    }

    fun notify(value: String?) {
        Toast.makeText(context, value, Toast.LENGTH_SHORT).show()
    }
}