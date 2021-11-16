package com.dntk.kimychat.ui.modulelogin

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.dntk.kimychat.R
import com.dntk.kimychat.core.base.BaseActivity
import com.dntk.kimychat.databinding.ActivitySignInBinding
import com.dntk.kimychat.ui.home.HomeActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignInActivity : BaseActivity<LoginViewModel, ActivitySignInBinding>() {
    companion object {
        const val REQUEST_CODE = 100
        private const val TAG = "FacebookLogin"
        private const val RC_SIGN_IN = 12345
    }

    private var mCallbackManager: CallbackManager? = null
    private lateinit var auth: FirebaseAuth
    override val mViewModel: LoginViewModel by viewModel()

    override fun getLayoutResId(): Int = R.layout.activity_sign_in

    private var resultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                val email = data?.getStringExtra("data")
                Log.d("khiem---", email!!)
                mViewBinding.edtEmail.setText(email)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        mCallbackManager = CallbackManager.Factory.create()
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired
        Log.d(TAG, "facebook:isLoggedIn:$isLoggedIn");
        mViewBinding.btnLoginFb.setPermissions("email", "public_profile")
        mViewBinding.btnLoginFb.registerCallback(
            mCallbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    Log.d(TAG, "facebook:onSuccess:$result");
                    result?.let { handleFacebookAccessToken(it.accessToken) }
                }

                override fun onCancel() {
                    Log.d(TAG, "facebook:onCancel")
                }

                override fun onError(error: FacebookException?) {
                    Log.d(TAG, "facebook:onError")
                }

            })
        mViewBinding.tvSignUp.setOnClickListener {
            gotoSignUpActivity()
        }
        mViewBinding.btnLogin.setOnClickListener {
            signInAccount()
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = auth.currentUser

        Log.d(TAG, "Currently Signed in: " + currentUser?.email)
        Toast.makeText(
            this,
            "Currently Logged in: " + currentUser?.email,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mCallbackManager?.onActivityResult(requestCode, resultCode, data);
    }

    private fun signInAccount() {
        mViewBinding.progressCircular.visibility = View.VISIBLE
        val email = mViewBinding.edtEmail.text.toString().trim()
        val password = mViewBinding.edtPassword.text.toString().trim()
        when {
            TextUtils.isEmpty(email) -> {
                mViewBinding.edtEmail.error = "Required"
            }
            TextUtils.isEmpty(password) -> {
                mViewBinding.edtPassword.error = "Required"
            }
            password.length < 6 -> {
                mViewBinding.edtPassword.error = "Length must be 6 or more!"
            }
            else -> {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            mViewBinding.progressCircular.visibility = View.GONE
                            // Sign in success, update UI with the signed-in user's information
//                            val user = auth.currentUser
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                            mViewBinding.progressCircular.visibility = View.GONE
                        }

                    }
            }
        }

    }

    private fun gotoSignUpActivity() {
        val intent = Intent(this, SignUpActivity::class.java)
        resultLauncher.launch(intent)
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG, "handleFacebookAccessToken:$token")
        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    // Sign in success, UI will update with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user: FirebaseUser? = auth.currentUser
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("user", user)
                    startActivity(intent)
                    Toast.makeText(
                        this@SignInActivity,
                        "Authentication Succeeded.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    // If sign-in fails, a message will display to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(
                        this@SignInActivity,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

}