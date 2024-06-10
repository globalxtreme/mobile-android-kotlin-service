package com.globalxtreme.gxsales.helper.base

import java.text.DecimalFormat

class StringHelper {
    fun getRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    fun formatNumberStringToInt(number: String): Int {
        return number.replace(".", "").toInt()
    }

    fun formatNumberIntToString(number: Int): String {
        return DecimalFormat("#,###").format(number.toDouble()).replace(",", ".")
    }
}