package com.example.tiago.myapplication.domain

import java.io.Serializable

/**
 * Created by tiago on 24/12/2017.
 */
class Address(
        val _id: String,
        val street: String,
        val number: String,
        val city: String,
        val state: String,
        val neighborhood: String) :Serializable{

}