package com.example.consultants.match.model.data.remote

import com.example.consultants.match.model.jsondata.ContactResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface RemoteService {

    @Headers("Content-Type: application/json")
    @GET("?gender=female&nat=us&results=10")
    fun getContactsObservable(): Observable<ContactResponse>
}
