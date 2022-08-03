package com.jps.takehomeassignment.vm.factory


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jps.takehomeassignment.ui.MainActivity
import com.jps.takehomeassignment.vm.MainVM


fun MainActivity.getViewModel(): MainVM {
    return ViewModelProvider(this, object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return MainVM() as T
        }
    })[MainVM::class.java]
}
