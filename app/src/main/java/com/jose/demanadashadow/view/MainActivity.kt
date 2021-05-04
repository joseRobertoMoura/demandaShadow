package com.jose.demanadashadow.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.jose.demanadashadow.R
import com.jose.demanadashadow.data.repository.DemandaShadowRepository
import com.jose.demanadashadow.model.DemandaShadowModel
import com.jose.demanadashadow.view.fragments.ListFragment
import com.jose.demanadashadow.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inflateFragment()
        btn_logout.setOnClickListener {
            logout()
        }
    }

    private fun inflateFragment(){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFrame, ListFragment.newInstance())
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