package com.jose.demanadashadow.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.jose.demanadashadow.R
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        if (supportActionBar != null){
            supportActionBar!!.hide()
        }

        btn_cadastrar.setOnClickListener(this)
        txt_voltar.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.btn_cadastrar){
            val email = edt_email_cadastrar.text.toString()
            val senha = edt_senha_cadastrar.text.toString()

            if (email.isEmpty() || senha.isEmpty()){
                txt_alerta_cadastrar.setText("Os campos devem ser preenchidos")
            }else{
                cadastrar(email,senha)
            }
        }else if (id == R.id.txt_voltar){
            changeActivity()
        }
    }

    private fun cadastrar(email:String, senha:String) {
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Cadastro Realizado com sucesso", Toast.LENGTH_SHORT)
                        .show()
                    changeActivity()
                }
            }.addOnFailureListener {

                val error = it
                when {
                    error is FirebaseAuthWeakPasswordException -> {
                        txt_alerta_cadastrar.setText("A senha deve ter no mínimo 6 caracteres")
                    }
                    error is FirebaseAuthUserCollisionException -> {
                        txt_alerta_cadastrar.setText("E-mail já cadastrado")
                    }
                    error is FirebaseNetworkException -> {
                        txt_alerta_cadastrar.setText("Sem conexão com a internet")
                    }
                    else -> {
                        txt_alerta_cadastrar.setText("Erro ao cadastrar usuário")
                    }
                }
            }
    }

    private fun changeActivity() {
        val intentLogin = Intent(this,LoginActivity::class.java)
        startActivity(intentLogin)
    }
}