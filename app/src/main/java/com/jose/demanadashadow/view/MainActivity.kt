package com.jose.demanadashadow.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.jose.demanadashadow.R
import com.jose.demanadashadow.model.DemandaShadowModel
import com.jose.demanadashadow.view.fragments.ListFragment
import com.jose.demanadashadow.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    private lateinit var mainViewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()

        btn_logout.setOnClickListener {
            logout()
        }
    }

    private fun initViewModel(){
        mainViewModel = ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
        mainViewModel.init()
        mainViewModel.dataList.observe(this, {list ->
            if (list != null){
                inflateFragment(list)
            }else{
                Toast.makeText(
                    this,
                    "Ops tivemos um problema, tente novamente mais tarde!",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun inflateFragment(list: List<DemandaShadowModel>){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFrame, ListFragment.newInstance(list))
            addToBackStack(null)
            commit()
        }
    }

    private fun logout(){
        FirebaseAuth.getInstance().signOut()
        changeActivityLogin()
    }


    private fun changeActivityLogin() {
        val intentLogin = Intent(this, LoginActivity::class.java)
        startActivity(intentLogin)
    }

}