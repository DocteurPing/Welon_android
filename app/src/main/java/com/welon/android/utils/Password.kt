package com.welon.android.utils

fun checkPassword(pass: String, confirmPass: String): EnumReturnCheckPassword {
    val regex = Regex("^(?=.{8,}\$)(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9])")
    if (pass != confirmPass) return EnumReturnCheckPassword.NOT_MATCHING
    if (!pass.contains(regex)) return EnumReturnCheckPassword.INVALID
    return EnumReturnCheckPassword.GOOD
}