package com.example.consultants.match.ui.contactlist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.consultants.match.R
import com.example.consultants.match.model.jsondata.Contact
import com.squareup.picasso.Picasso

class ContactListAdapter(val contacts: List<Contact>): RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(contacts[position].picture.large).into(holder.ivImage)
        holder.tvName.text = contacts[position].name.first
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ivImage: ImageView = itemView.findViewById(R.id.ivImage) as ImageView
        val tvName: TextView = itemView.findViewById(R.id.tvName) as TextView
    }
}