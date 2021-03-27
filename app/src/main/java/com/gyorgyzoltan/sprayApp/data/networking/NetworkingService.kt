package com.gyorgyzoltan.sprayApp.data.networking

import com.gyorgyzoltan.sprayApp.data.model.remote.NozzleResponse
import com.gyorgyzoltan.sprayApp.data.model.remote.NozzleTypeResponse
import com.theapache64.retrosheet.core.Read
import retrofit2.http.GET

interface NetworkingService {

    @Read("SELECT *")
    @GET(NozzleTypeResponse.SHEET_NAME)
    suspend fun getNozzleTypes(): List<NozzleTypeResponse>

    @Read("SELECT *")
    @GET(NozzleResponse.SHEET_NAME)
    suspend fun getNozzles(): List<NozzleResponse>
}