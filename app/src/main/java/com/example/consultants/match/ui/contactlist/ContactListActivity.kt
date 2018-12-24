package com.example.consultants.match.ui.contactlist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.example.consultants.match.R
import com.example.consultants.match.model.jsondata.Contact
import kotlinx.android.synthetic.main.activity_contact_list.*

class ContactListActivity : AppCompatActivity(), Observer<List<Contact>> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        val viewModel: ContactListViewModel = ViewModelProviders.of(this).get(ContactListViewModel::class.java)

        viewModel.contacts.observe(this, this)

        rvContactList.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onChanged(t: List<Contact>?) {
        TODO("not implemented")
    }
}
