package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var myAdapter: MyAdapter
    private lateinit var dataList: ArrayList<DataList>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        myAdapter = MyAdapter(this, ArrayList())
        recyclerView.adapter = myAdapter
        dataList = ArrayList()

        getMyData()
    }

    private fun getMyData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pro-api.coinmarketcap.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        val retrofitData = retrofit.fetchData()

        retrofitData.enqueue(object : Callback<DataList> {
            override fun onResponse(call: Call<DataList>, response: Response<DataList>) {
                if (response.isSuccessful) {
                    val data: DataList? = response.body()

//                    Log.d("DataCheck","Data")

                    if (data != null) {
//                        dataList = ArrayList(data.data) // Assuming 'data' contains a List<DataList>
//                        myAdapter.setData(dataList)
                        Toast.makeText(this@MainActivity, "Data is not null", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity, "Data is null", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Handle errors
                    Toast.makeText(this@MainActivity,"No responses of API",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DataList>, t: Throwable) {
                // Handle network failure
                Log.e("API", "API Loading Failed", t)
                Toast.makeText(this@MainActivity,"API Loading Failed",Toast.LENGTH_SHORT).show()
            }
        })
    }
}