package com.gyorgyzoltan.sprayApp.data.networking

import com.gyorgyzoltan.sprayApp.data.model.Nozzle
import com.theapache64.retrosheet.core.Read
import retrofit2.http.GET

interface NozzleService {

    @Read("SELECT *")
    @GET(NetworkingManager.NOZZLES_SHEET_NAME)
    suspend fun getNozzles(): List<Nozzle>
}