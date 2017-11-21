package com.example.lance.simplebox.View.FTFTransfer.Utils

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.example.lance.simplebox.R

/**
 * Created by Lance on 2017/11/21.
 */
object ReFTFFragUtil{

    fun replaceFTFSendFragment(fragment: Fragment,getActivty : FragmentActivity){
        val fragmentManager = getActivty.supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.sendViewPage,fragment)
        transaction.commit()
    }
}