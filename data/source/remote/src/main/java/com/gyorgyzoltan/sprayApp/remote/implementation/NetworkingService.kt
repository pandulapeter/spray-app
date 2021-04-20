package com.gyorgyzoltan.sprayApp.remote.implementation

import com.gyorgyzoltan.sprayApp.remote.model.NozzleResponse
import com.gyorgyzoltan.sprayApp.remote.model.NozzleTypeResponse
import com.theapache64.retrosheet.core.Read
import retrofit2.http.GET

internal interface NetworkingService {

    @Read("SELECT *")
    @GET(NozzleTypeResponse.SHEET_NAME)
    suspend fun getNozzleTypes(): List<NozzleTypeResponse>

    @Read("SELECT *")
    @GET(NozzleResponse.SHEET_NAME)
    suspend fun getNozzles(): List<NozzleResponse>
}