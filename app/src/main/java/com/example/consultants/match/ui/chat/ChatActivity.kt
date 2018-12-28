package com.example.consultants.match.ui.chat

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.consultants.match.R
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent



class ChatActivity : AppCompatActivity() {
    companion object {
        const val SIGN_IN_REQUEST_CODE = 20
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        if(FirebaseAuth.getInstance().currentUser == null) {
            // Start sign in/sign up activity
            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .build(),
                SIGN_IN_REQUEST_CODE
            )
        } else {
            // User is already signed in
            Toast.makeText(this,
                "Welcome " + FirebaseAuth.getInstance().currentUser?.displayName,
                Toast.LENGTH_SHORT)
                .show()

            // Load chat room contents
            displayChatMessages();
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SIGN_IN_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(
                    this,
                    "Successfully signed in. Welcome!",
                    Toast.LENGTH_SHORT
                )
                    .show()
                displayChatMessages()
            } else {
                Toast.makeText(
                    this,
                    "We couldn't sign you in. Please try again later.",
                    Toast.LENGTH_SHORT
                )
                    .show()

                // Close the activity
                finish()
            }
        }
    }

    private fun displayChatMessages() {

    }
}
