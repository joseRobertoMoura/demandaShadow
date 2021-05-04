package com.jose.demanadashadow.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jose.demanadashadow.R
import com.jose.demanadashadow.model.DemandaShadowModel
import com.jose.demanadashadow.view.adapter.AdapterDemandaShadow
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment(private val listData: List<DemandaShadowModel>) : Fragment() {


    companion object {
        fun newInstance(list: List<DemandaShadowModel>) = ListFragment(list)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = activity as Context
        setAdapter(view, activity)
    }

    private fun setAdapter(view: View, activity: Context) {
        val recyclerView = view.fragmentList
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = AdapterDemandaShadow(listData)
    }

}