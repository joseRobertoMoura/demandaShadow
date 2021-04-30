package com.jose.demanadashadow.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.jose.demanadashadow.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        verificaUsuarioLogado()

        if (supportActionBar != null){
            supportActionBar!!.hide()
        }

        btn_entrar.setOnClickListener(this)
        txt_tela_cadastro.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.btn_entrar){
            val email = edt_email.text.toString()
            val senha = edt_senha.text.toString()

            if (email.isEmpty() || senha.isEmpty()){
                txt_alerta.setText("Os campos devem ser preenchidos!")
            }else{
                logar(email, senha)
            }
        }else if(id == R.id.txt_tela_cadastro){
            changeActiviryCadastro()
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
                when {
                    it is FirebaseAuthWeakPasswordException -> {
                        txt_alerta.setText("Password incorreto!")
                    }
                    else -> {
                        txt_alerta.setText("Erro ao logar usuário!")
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