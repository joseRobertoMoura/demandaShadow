package com.jose.demanadashadow.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jose.demanadashadow.R
import com.jose.demanadashadow.model.DemandaShadowModel
import com.jose.demanadashadow.view.adapter.AdapterDemandaShadow
import com.jose.demanadashadow.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModel()


    companion object {
        fun newInstance() = ListFragment()
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
       initViewModel(view,activity)
    }

    private fun initViewModel(view: View, activity: Context){
        mainViewModel.init()
        mainViewModel.dataList.observe(viewLifecycleOwner, {list ->
            if (list != null){
                setAdapter(view, activity,list)
            }else{
                Toast.makeText(
                    activity,
                    "Ops tivemos um problema, tente novamente mais tarde!",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun setAdapter(view: View, activity: Context, list:List<DemandaShadowModel>) {
        val recyclerView = view.fragmentList
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = AdapterDemandaShadow(list)
    }

}