package com.example.tiago.myapplication.domain

import java.io.Serializable

/**
 * Created by tiago on 09/12/2017.
 */
class Establishment(
        val _id : String,
        val imgLogo: String,
        val group: String,
        val capacity: String,
        val description: String,
        val personalDataId:PersonalData,
        val addressId: Address?
     //  val coverId: List<Cover>,
     //   val tagId: List<Tag>

) : Serializable {

}