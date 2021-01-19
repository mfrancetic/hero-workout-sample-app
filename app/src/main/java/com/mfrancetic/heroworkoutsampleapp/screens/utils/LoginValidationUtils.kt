package com.mfrancetic.heroworkoutsampleapp.screens.utils

import android.util.Patterns
import java.util.regex.Pattern

private const val PASSWORD_MIN_LENGTH = 12
const val PASSWORD_TOO_SHORT = "PASSWORD_TOO_SHORT"
const val PASSWORD_CAPITAL_LETTER_MISSING = "CAPITAL_LETTER_MISSING"
const val PASSWORD_NUMBER_MISSING = "NUMBER_MISSING"
const val PASSWORD_SPECIAL_CHARACTER_MISSING = "SPECIAL_CHARACTER_MISSING"

fun isEmailAddressValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email)
        .matches()
}

fun getPasswordValidationError(password: String): String {
    var passwordError = ""
    if (password.length < PASSWORD_MIN_LENGTH) {
        passwordError = PASSWORD_TOO_SHORT
    } else if (!password.containsNumber()) {
        passwordError = PASSWORD_NUMBER_MISSING
    } else if (!password.containsCapitalLetter()) {
        passwordError = PASSWORD_CAPITAL_LETTER_MISSING
    } else if (!password.containsSpecialCharacter()) {
        passwordError = PASSWORD_SPECIAL_CHARACTER_MISSING
    }
    return passwordError
}

private fun String.containsNumber(): Boolean {
    val numberExpression = ".*[0-9].*"
    val pattern = Pattern.compile(numberExpression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

private fun String.containsCapitalLetter(): Boolean {
    val capitalLetterExpression = ".*[A-Z].*"
    val pattern = Pattern.compile(capitalLetterExpression)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

private fun String.containsSpecialCharacter(): Boolean {
    val specialLetterExpression = ".*[~!@#\$%^&*()\\-_=+|\\[{\\]};:'\",<.>/?].*"
    val pattern = Pattern.compile(specialLetterExpression)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}