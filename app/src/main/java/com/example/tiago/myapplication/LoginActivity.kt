package com.example.tiago.myapplication

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.tiago.myapplication.model.MVP
import com.example.tiago.myapplication.presenter.PresenterLogin
import com.example.tiago.myapplication.utils.Util
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), MVP.ViewLogin {


    lateinit var presenter: MVP.PresenterLogin
    lateinit var mProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Picasso.with(this).load(R.drawable.balada_background).into(img_dancer);
        presenter = PresenterLogin(this, this)
        presenter.setView(this)
        this.mProgressDialog = ProgressDialog(this)

        btn_facebook.setOnClickListener{
            presenter.loginFacebook()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data!=null){
            presenter.onActivityResult(requestCode, resultCode, data);
        }
    }

    override fun showProgressbar(status: Boolean, message: String) {
        Util.showProgressDialog(this, mProgressDialog, status, message)
    }

    override fun showToast(message: String) {
        Util.showToast(this, message)
    }
}
