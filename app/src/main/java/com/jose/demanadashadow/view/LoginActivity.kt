package com.jose.demanadashadow.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.jose.demanadashadow.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        verificaUsuarioLogado()

        btn_entrar.setOnClickListener { login()}
        txt_tela_cadastro.setOnClickListener { changeActiviryCadastro() }
    }

    private fun login(){
        val email = edt_email.text.toString()
        val senha = edt_senha.text.toString()

        if (email.isEmpty() || senha.isEmpty()){
            txt_alerta.setText(R.string.msg_campos_vazios)
        }else{
            logar(email, senha)
        }
    }

    private fun logar(email: String, senha: String) {

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Login efetuado com sucesso!", Toast.LENGTH_LONG)
                        .show()
                    changeActiviryMain()
                }
            }.addOnFailureListener {
                when (it) {
                    is FirebaseAuthWeakPasswordException -> {
                        txt_alerta.text = getString(R.string.msg_senha_incorreta)
                    }
                    else -> {
                        txt_alerta.text = getString(R.string.msg_erro_login)
                    }
                }
            }
    }

    private fun changeActiviryMain() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    private fun changeActiviryCadastro() {
        val intentCadastro = Intent(this, CadastroActivity::class.java)
        startActivity(intentCadastro)
    }

    private fun verificaUsuarioLogado(){
        val usuarioLogado = FirebaseAuth.getInstance().currentUser

        if (usuarioLogado != null){
            changeActiviryMain()
        }
    }

}