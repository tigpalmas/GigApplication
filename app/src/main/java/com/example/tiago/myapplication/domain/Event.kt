package com.example.tiago.myapplication.domain

import java.io.Serializable
import java.util.*

/**
 * Created by tiago on 17/12/2017.
 */
class Event(
        val _id: String,
        val name: String,
        val description: String?,
        val imgUrl: String,
        val beginDate: Date


) : Serializable {

}