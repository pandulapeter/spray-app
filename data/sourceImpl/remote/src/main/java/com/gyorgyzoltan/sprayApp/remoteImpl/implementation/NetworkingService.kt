package com.gyorgyzoltan.sprayApp.remoteImpl.implementation

import com.github.theapache64.retrosheet.core.Read
import com.github.theapache64.retrosheet.core.ReadAsList
import com.gyorgyzoltan.sprayApp.remoteImpl.model.response.NozzleStubResponse
import com.gyorgyzoltan.sprayApp.remoteImpl.model.response.NozzleTypeResponse
import retrofit2.http.GET

internal interface NetworkingService {

    @ReadAsList
    @Read("SELECT *")
    @GET(NozzleTypeResponse.SHEET_NAME)
    suspend fun getNozzleTypes(): List<NozzleTypeResponse>

    @ReadAsList
    @Read("SELECT *")
    @GET(NozzleStubResponse.SHEET_NAME)
    suspend fun getNozzleStubs(): List<NozzleStubResponse>
}