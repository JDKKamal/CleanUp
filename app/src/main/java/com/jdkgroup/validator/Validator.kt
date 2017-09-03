package com.jdkgroup.validator

import android.widget.EditText

import java.util.regex.Pattern

object Validator {

    fun getText(editText: EditText): String {
        return editText.text.toString().trim { it <= ' ' }
    }

    fun isEmplty(editText: EditText): Boolean {
        return getText(editText).equals("", ignoreCase = true)
    }

    fun isEmail(email: String): Boolean {
        val EMAIL_PATTERN = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z]{2,8}" +
                ")+"

        return Pattern.compile(EMAIL_PATTERN).matcher(email).matches()

    }

    fun isBusinessName(businessName: String): Boolean {
        val patern = "^[a-zA-Z0-9]+$"

        return Pattern.compile(patern).matcher(businessName).matches()

    }

    fun isValidePassword(password: String): Boolean {
        val patern = "[^\\w\\d]*(([0-9]+.*[A-Za-z]+.*)|[A-Za-z]+.*([0-9]+.*))"

        return Pattern.compile(patern).matcher(password).matches() && password.length > 4

    }


}
