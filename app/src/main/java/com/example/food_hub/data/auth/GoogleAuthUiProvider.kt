package com.example.food_hub.data.auth

import android.content.Context
import android.util.Log
import androidx.compose.animation.core.rememberTransition
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.example.food_hub.data.models.GoogleAcount
import com.example.food_hub.ui.GoogleServerClientID
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlin.jvm.Throws

class GoogleAuthUiProvider {
    suspend fun singIn(
        activityContest:Context,
        credentialManager: CredentialManager
    ):GoogleAcount {
        val creds = credentialManager.getCredential(
            activityContest,
            getCredentaialRequest()
        ).credential
return handelCretential(creds)


    }

  private  fun handelCretential(creds:Credential):GoogleAcount{
        when {
            creds is CustomCredential && creds.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL -> {
                val googleIdTokanCredential = creds as GoogleIdTokenCredential
                Log.d("GoogleAuthUiProvider","GoogleIdCredentiaal: $googleIdTokanCredential")
                return GoogleAcount(
                    tokan = googleIdTokanCredential.id,
                    displayName = googleIdTokanCredential.displayName ?: "",
                    profileImageurl = googleIdTokanCredential.profilePictureUri.toString()
                )
            }
            else ->{
                throw IllegalStateException("Invalid Credential")
            }
        }

    }

    private fun getCredentaialRequest(): GetCredentialRequest {
       return GetCredentialRequest.Builder()
           .addCredentialOption(
               GetSignInWithGoogleOption.Builder(
                   GoogleServerClientID
               ).build()


           ).build()
    }
}