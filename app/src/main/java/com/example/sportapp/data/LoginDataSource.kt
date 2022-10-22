package com.example.sportapp.data

import android.util.Log
import com.example.sportapp.data.model.LoggedInUser
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            if(username=="kacper-paczos@linux.pl")
            {
                Log.i("LOGOWANIE", username)
                if(password=="123456")
                {
                    Log.i("LOGOWANIE", password)
                    val nickName = "Kacper!"
                    val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), nickName)
                    return Result.Success(fakeUser)
                } else {
                    Log.i("CHCECK_USER_ERROR", "passoword")
                    throw IOException("password")
                }

            } else {
                Log.i("CHCECK_USER_ERROR", "login")
                throw IOException("login")
            }
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}