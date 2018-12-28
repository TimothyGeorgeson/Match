package com.example.consultants.match.ui.fragments

import android.app.SearchManager
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import com.example.consultants.match.R
import com.example.consultants.match.model.jsondata.Contact
import com.example.consultants.match.ui.contactlist.ContactListActivity
import com.example.consultants.match.ui.contactlist.ContactListAdapter
import com.example.consultants.match.ui.contactlist.ContactListViewModel
import com.example.consultants.match.util.ContactUtil
import kotlinx.android.synthetic.main.fragment_all.*

class AllFragment : Fragment(), Observer<List<Contact>> {

    private var lat = ContactListActivity.lat
    private var lng = ContactListActivity.lng
    private var adapter: ContactListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            val bundle = arguments as Bundle
            lat = bundle.getDouble("LAT")
            lng = bundle.getDouble("LONG")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_all, container, false)

        //hook to viewModel, and observe contacts
        val viewModel = ViewModelProviders.of(this).get(ContactListViewModel::class.java)
        viewModel.allContacts.observe(this, this)

        //set layoutmanager to recyclerview
        val rvContactList: RecyclerView = view.findViewById(R.id.rvContactList)
        val layoutManager = GridLayoutManager(context, 2)
        rvContactList.layoutManager = layoutManager

        Log.i("_tag", "from fragment: Lat: $lat Long: $lng")

        return view
    }

    //when contacts data changes, set the adapter to display in recyclerview
    override fun onChanged(contacts: List<Contact>?) {
        if (contacts != null) {
            ContactUtil.generateLocations(contacts, lat, lng, false)
            adapter = ContactListAdapter(contacts)
            rvContactList.adapter = adapter
        }
    }

    fun sendQuery(query: String) {
        adapter?.filter?.filter(query)
    }
}
