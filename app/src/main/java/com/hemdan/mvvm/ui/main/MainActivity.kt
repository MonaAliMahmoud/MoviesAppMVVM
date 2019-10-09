package com.hemdan.mvvm.ui.main

import android.os.Bundle
import com.hemdan.mvvm.R
import com.hemdan.mvvm.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
