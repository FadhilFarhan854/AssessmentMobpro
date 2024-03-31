package org.d3if0739.assessment.ui.screen
import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Email

class Localstorage {


    object LocalStorages {

        private const val PREFS_NAME = "UserCredentials"

        fun saveCredentials(context: Context, email: String, username: String, password: String) {
            val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("email", email)
            editor.putString("username", username)
            editor.putString("password", password)
            editor.apply()
        }

        fun getCredentials(context: Context): Pair<String?, String?> {
            val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val username = sharedPreferences.getString("username", null)
            val password = sharedPreferences.getString("password", null)
            return Pair(username, password)
        }

        fun updatePassword(context: Context, newPassword: String) {
            val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("password", newPassword)
            editor.apply()
        }

        fun deleteCredentials(context: Context) {
            val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
        }
    }

}