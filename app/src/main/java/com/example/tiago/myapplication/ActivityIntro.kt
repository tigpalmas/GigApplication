package com.example.tiago.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_intro.*

class ActivityIntro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        Picasso.with(this).load(R.drawable.balada_background).into(img_dancer);
        txt_entry.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
             startActivity(intent)
        }
        btn_login.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        btn_signup.setOnClickListener{
            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }

    }
}
