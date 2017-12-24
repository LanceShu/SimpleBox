package com.example.lance.simplebox.View.Informations.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lance.simplebox.R

/**
 * Created by Lance on 2017/12/16.
 */
class ScienceFragment : Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.information_layout,container,false)
        childFragmentManager

        return view
    }

    override fun onPause() {
        super.onPause()
    }
}