package com.dntk.kimychat.ui.modulelogin

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.dntk.kimychat.R
import com.dntk.kimychat.core.base.BaseActivity
import com.dntk.kimychat.databinding.ActivitySignUpBinding
import com.dntk.kimychat.ui.home.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : BaseActivity<LoginViewModel, ActivitySignUpBinding>() {
    private lateinit var mAuth : FirebaseAuth

    override fun getLayoutResId(): Int = R.layout.activity_sign_up

    override val mViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        mViewBinding.btnSignUp.setOnClickListener {
            validateAccount()
        }
    }

    private fun validateAccount() {
        val email = mViewBinding.edtEmail.text.toString().trim()
        val userName = mViewBinding.edtUserName.text.toString().trim()
        val password = mViewBinding.edtPassword.text.toString().trim()
        when {
            TextUtils.isEmpty(email) -> {
                mViewBinding.edtEmail.error = "Required"
            }
            TextUtils.isEmpty(userName) -> {
                mViewBinding.edtUserName.error = "Required"
            }
            TextUtils.isEmpty(password) -> {
                mViewBinding.edtPassword.error = "Required"
            }
            password.length < 6 -> {
                mViewBinding.edtPassword.error = "Length must be 6 or more!"
            }
            else -> {
                registerAccount(email, userName, password)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (SignInActivity.REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            val email = data?.getStringExtra("test")
            Log.d("TAGG", email!!)
            mViewBinding.tvAppName.text = email
        }
    }

    private fun registerAccount(email: String, userName: String, password: String) {
        mViewBinding.progressCircular.visibility = View.VISIBLE

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    mViewBinding.progressCircular.visibility = View.GONE
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("khiem", "createUserWithEmail:success")
//                    val user = mAuth.currentUser
                    val intent = Intent(this, SignInActivity::class.java)
                    intent.putExtra("data", email)
                    setResult(RESULT_OK, intent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.d("khiem", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    mViewBinding.progressCircular.visibility = View.GONE
                }
            }
    }
}