package com.newestaf.bongeuihighutil.neis

import com.newestaf.bongeuihighutil.neis.meal.data.Meal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NeisService {

    @GET(" mealServiceDietInfo")
    fun getMeal(
        @Query("KEY") apiKey: String,
        @Query("Type") type: String,
        @Query("ATPT_OFCDC_SC_CODE") eduCode: String,
        @Query("SD_SCHUL_CODE") schoolCode: String,
        @Query("MLSV_YMD") mealDate: String
    ): Call<Meal>

}