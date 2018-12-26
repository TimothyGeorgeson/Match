package com.example.consultants.match.ui.contactlist

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.consultants.match.R
import com.example.consultants.match.model.jsondata.Contact
import com.example.consultants.match.ui.chat.ChatActivity
import com.example.consultants.match.ui.contactdetails.ContactDetails
import com.squareup.picasso.Picasso

class ContactListAdapter(private val contacts: List<Contact>): RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {
    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(contacts[position].picture.large).into(holder.ivImage)
        holder.tvName.text = contacts[position].name.first
        holder.btnChat.setOnClickListener{
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("NAME", contacts[position].name.first)
            intent.putExtra("PICURL", contacts[position].picture.large)
            context?.startActivity(intent)
        }
        holder.ivImage.setOnClickListener{
            val intent = Intent(context, ContactDetails::class.java)
            intent.putExtra("NAME", contacts[position].name.first)
            intent.putExtra("GENDER", contacts[position].gender)
            intent.putExtra("AGE", contacts[position].dob.age)
            intent.putExtra("EMAIL", contacts[position].email)
            intent.putExtra("PHONE", contacts[position].phone)
            intent.putExtra("PICURL", contacts[position].picture.large)
            intent.putExtra("LAT", contacts[position].location.coordinates.latitude)
            intent.putExtra("LONG", contacts[position].location.coordinates.longitude)
            context?.startActivity(intent)
        }
    }

    override fun getItemCount() = contacts.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ivImage: ImageView = itemView.findViewById(R.id.ivImage) as ImageView
        val tvName: TextView = itemView.findViewById(R.id.tvName) as TextView
        val btnChat: ImageButton = itemView.findViewById(R.id.btnChat) as ImageButton
    }
}