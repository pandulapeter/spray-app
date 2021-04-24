package com.gyorgyzoltan.sprayApp.remoteImpl.implementation

import com.gyorgyzoltan.sprayApp.remoteImpl.model.response.NozzleStubResponse
import com.gyorgyzoltan.sprayApp.remoteImpl.model.response.NozzleTypeResponse
import com.theapache64.retrosheet.core.Read
import retrofit2.http.GET

internal interface NetworkingService {

    @Read("SELECT *")
    @GET(NozzleTypeResponse.SHEET_NAME)
    suspend fun getNozzleTypes(): List<NozzleTypeResponse>

    @Read("SELECT *")
    @GET(NozzleStubResponse.SHEET_NAME)
    suspend fun getNozzleStubs(): List<NozzleStubResponse>
}