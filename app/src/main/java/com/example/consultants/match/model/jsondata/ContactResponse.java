
package com.example.consultants.match.model.jsondata;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactResponse {

    @SerializedName("results")
    @Expose
    private List<Contact> contacts = null;
    @SerializedName("info")
    @Expose
    private Info info;

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}
