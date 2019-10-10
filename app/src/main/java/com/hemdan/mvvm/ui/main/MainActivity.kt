package com.hemdan.mvvm.ui.main

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.hemdan.mvvm.R
import com.hemdan.mvvm.ui.base.BaseActivity
import com.hemdan.mvvm.ui.main.actorslist.ActorsListFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = this.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.list_fragment, ActorsListFragment())
        fragmentTransaction.commit()
    }
}
