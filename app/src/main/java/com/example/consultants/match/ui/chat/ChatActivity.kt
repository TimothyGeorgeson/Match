package com.example.consultants.match.ui.chat

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.consultants.match.R
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import android.text.format.DateFormat
import android.view.View
import android.widget.ListView
import com.example.consultants.match.model.ChatMessage
import com.firebase.ui.database.FirebaseListAdapter
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_chat.*
import android.widget.TextView




class ChatActivity : AppCompatActivity() {
    companion object {
        const val SIGN_IN_REQUEST_CODE = 20
    }

    private var adapter: FirebaseListAdapter<ChatMessage>? = null

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

        fab.setOnClickListener {
            // Read the input field and push a new instance
            // of chat message to the Firebase database
            FirebaseDatabase.getInstance()
                .reference
                .push()
                .setValue(
                    ChatMessage(input.text.toString(),
                        FirebaseAuth.getInstance().currentUser?.displayName!!)
                )

            // Clear the input
            input.setText("")
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

        val listOfMessages = list_of_messages as ListView

        adapter = object : FirebaseListAdapter<ChatMessage>(this, ChatMessage::class.java,
            R.layout.message, FirebaseDatabase.getInstance().reference) {
            override fun populateView(v: View, model: ChatMessage, position: Int) {
                //bind views
                val messageText = v.findViewById(R.id.message_text) as TextView
                val messageUser = v.findViewById(R.id.message_user) as TextView
                val messageTime = v.findViewById(R.id.message_time) as TextView

                // Set text and date
                messageText.text = model.messageText
                messageUser.text = model.messageUser
                messageTime.text = DateFormat.format("MM-dd-yyyy (HH:mm)", model.messageTime)
            }
        }

        listOfMessages.adapter = adapter
    }
}
