package com.jose.demanadashadow.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.jose.demanadashadow.R
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        btn_cadastrar.setOnClickListener { cadastrar() }
        txt_voltar.setOnClickListener { changeActivity() }
    }

    private fun cadastrar(){
        val email = edt_email_cadastrar.text.toString()
        val senha = edt_senha_cadastrar.text.toString()

        if (email.isEmpty() || senha.isEmpty()){
            txt_alerta_cadastrar.text = getString(R.string.msg_campos_vazios)
        }else{
            cadastrar(email,senha)
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

                when (it) {
                    is FirebaseAuthWeakPasswordException -> {
                        txt_alerta_cadastrar.text = getString(R.string.msg_tamanho_senha)
                    }
                    is FirebaseAuthUserCollisionException -> {
                        txt_alerta_cadastrar.text = getString(R.string.msg_email_existente)
                    }
                    is FirebaseNetworkException -> {
                        txt_alerta_cadastrar.text = getString(R.string.msg_sem_conexao)
                    }
                    else -> {
                        txt_alerta_cadastrar.text = getString(R.string.msg_erro_cadastro_usuario)
                    }
                }
            }
    }

    private fun changeActivity() {
        val intentLogin = Intent(this,LoginActivity::class.java)
        startActivity(intentLogin)
    }
}