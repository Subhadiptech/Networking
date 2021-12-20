package com.ersubhadip.networking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var adapter:ApiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dataRV:RecyclerView =findViewById(R.id.rvData)
        var listOfData= mutableListOf<ApiDataModel>()
        adapter = ApiAdapter(listOfData)

        //Making the Request
        lifecycleScope.launchWhenCreated {

            //Thread Launched with Coroutines
            var result = try {

                ApiSingletonObj.api.getApi()

            }catch (e:IOException){
                e.printStackTrace()

                withContext(Dispatchers.Main){

                    //Some Error Layout to user
                }
                return@launchWhenCreated
            }catch (e:HttpException){
                e.printStackTrace()
                withContext(Dispatchers.Main){

                    //Some Error Layout to user
                }
                return@launchWhenCreated
            }

            if(result.isSuccessful && result.body()!=null){
                adapter.list = result.body()!! //no need of for loop it will create the list acc to the model automatically
            }else{
                //handling
                Log.e("MainActivity","Data Not Found")
            }
            withContext(Dispatchers.Main){
                settingRecyclerView(listOfData,dataRV)
            }

        }



    }

    fun settingRecyclerView(listOfData:List<ApiDataModel>,rv:RecyclerView){
        adapter  = ApiAdapter(listOfData)
        rv.adapter = adapter
        rv.layoutManager= LinearLayoutManager(this@MainActivity,RecyclerView.VERTICAL,false)

    }
}