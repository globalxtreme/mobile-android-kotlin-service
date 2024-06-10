package com.globalxtreme.gxsales.helper.base

import android.content.Context
import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import java.util.Locale

class CircleNameInitial {
    fun nameInitial(name: String): String {
        return name.substring(0, 1).uppercase(Locale.ROOT)
    }

//    fun ticketCategoryInitial(name: String): String {
//        return name.substring(0, 1).toUpperCase()
//    }

//    fun ticketCategoryColor(context: Context, name: String): ColorStateList? {
//        return when (name) {
//            "A", "G", "M", "S", "Y" -> ContextCompat.getColorStateList(context, R.color.colorTicket1)
//            "B", "H", "N", "T", "Z" -> ContextCompat.getColorStateList(context, R.color.colorTicket2)
//            "C", "I", "O", "U" -> ContextCompat.getColorStateList(context, R.color.colorTicket3)
//            "D", "J", "P", "V" -> ContextCompat.getColorStateList(context, R.color.colorTicket4)
//            "E", "K", "Q", "W" -> ContextCompat.getColorStateList(context, R.color.colorTicket5)
//            "F", "L", "R", "X" -> ContextCompat.getColorStateList(context, R.color.colorTicket6)
//            else -> ContextCompat.getColorStateList(context, R.color.softPurple)
//        }
//    }
}