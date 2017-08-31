package com.jdkgroup.validator

import android.widget.EditText

import java.util.regex.Pattern

object Validator {

    fun getText(editText: EditText): String {
        return editText.text.toString().trim { it <= ' ' }
    }

    fun isEmplty(editText: EditText): Boolean {
        return if (getText(editText).equals("", ignoreCase = true)) {
            true
        } else false
    }

    fun isEmail(email: String): Boolean {
        val EMAIL_PATTERN = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z]{2,8}" +
                ")+"

        return if (Pattern.compile(EMAIL_PATTERN).matcher(email).matches()) {
            true
        } else false

    }

    fun isBusinessName(businessName: String): Boolean {
        val patern = "^[a-zA-Z0-9]+$"

        return if (Pattern.compile(patern).matcher(businessName).matches()) {
            true
        } else false

    }

    fun isValidePassword(password: String): Boolean {
        val patern = "[^\\w\\d]*(([0-9]+.*[A-Za-z]+.*)|[A-Za-z]+.*([0-9]+.*))"

        return if (Pattern.compile(patern).matcher(password).matches() && password.length > 4) {
            true
        } else false

    }


}
