package com.midnightgeek.indoortracker

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.midnightgeek.indoortracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binder:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        binder= DataBindingUtil.setContentView(this,R.layout.activity_main);
        var prefHandler=PrefHandler(applicationContext)
        prefHandler.init()
        ApiHandler.getInstance().init()
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
            NotificationHandler.getInstance().creatNotificationChanner(applicationContext)
        if ("".equals(prefHandler.getString(prefHandler.TAG_USER_ID,""))){
            openLoginFragment()
        }else{
            openMainFragment();
        }

    }

    fun openLoginFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.mainFrame,LoginFragment.getInstance()).commit()
    }

    fun openMainFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.mainFrame,MainFragment.getInstance()).commit()
    }

}