package com.newestaf.bongeuihighutil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.MutableLiveData
import com.newestaf.bongeuihighutil.neis.NeisOpenApi
import com.newestaf.bongeuihighutil.neis.NeisRetrofitClient
import com.newestaf.bongeuihighutil.neis.meal.data.Meal
import lineBreak
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    enum class ToRequest{
        Meal,
        SelfTest,
        Test
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request = findViewById<Button>(R.id.request)
        val result = findViewById<TextView>(R.id.result)
        val selector = findViewById<Spinner>(R.id.selector)

        var selected = ToRequest.Meal

        result.movementMethod = ScrollingMovementMethod()
        result.scrollY = 0

        request.setOnClickListener {
            when (selected) {
                ToRequest.Meal -> {
                    getMealdata(result)
                }
                ToRequest.SelfTest -> {

                }
                else -> {
                    Toast.makeText(this, "요청할 정보를 선택해주세요", Toast.LENGTH_SHORT).show()
                    result.text = "존,나,긴,텍,스,트,입,니,다,테,스,트,용,이,요,존,나,긴,텍,스,트,입,니,다,테,스,트,용,이,요,".lineBreak(",")
                }
            }

        }

        selector.adapter = ArrayAdapter.createFromResource(this@MainActivity, R.array.request_what, android.R.layout.simple_spinner_dropdown_item)

        selector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        selected = ToRequest.Meal
                    }
                    1 -> {
                        selected = ToRequest.SelfTest
                    }
                    else -> {
                        selected = ToRequest.Test
                    }
                }
            }
        }


    }

    fun getMealdata(result: TextView): MutableLiveData<Meal> {
        val call = NeisRetrofitClient.neisService
        val meal = MutableLiveData<Meal>()

        call.getMeal(NeisOpenApi.API_KEY1, "json", "K10", "7800157", "20220303").enqueue(object: Callback<Meal> {
            override fun onResponse(call: Call<Meal>, response: Response<Meal>) {
                if (response.isSuccessful) {
                    meal.value = response.body() as Meal
                    result.text = meal.value?.mealServiceDietInfo?.get(1)?.row?.get(0)?.DDISH_NM?.lineBreak("<br/>")
                    Log.d("YMC", "OnResponse 성공 " + meal.value?.toString())
                } else {
                    Log.d("YMC", "OnResponse 실패")
                }


            }
            override fun onFailure(call: Call<Meal>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return meal
    }

}