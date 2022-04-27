package com.izmir.izmirli

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.izmir.izmirli.adapter.IzmirAdapter
import com.izmir.izmirli.databinding.ActivityMainBinding
import com.izmir.izmirli.model.HastanelerResponse
import com.izmir.izmirli.model.NobetciEczaneResponse
import com.izmir.izmirli.util.*
import com.kafein.weatherapp.IzmirAPIService
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity(), Example {

    private val izmirAdapter = IzmirAdapter()
    private lateinit var binding: ActivityMainBinding
    private lateinit var exampleInterface: Example
    private var izmirAPIService = IzmirAPIService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.pbLoader.visibility = View.VISIBLE
        exampleInterface = this

        categoryList().forEach {
            binding.tbCategory.addTab(binding.tbCategory.newTab().setText(it))
        }

        izmirAPIService.getNobetciEczane()
            .subscribeOn(Schedulers.newThread())
            .subscribeWith(object : DisposableSingleObserver<NobetciEczaneResponse>() {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onSuccess(eczaneler: NobetciEczaneResponse) {
                    runOnUiThread {
                        eczaneler[0].tarih?.replace("T08:00:00", " ")?.trim()?.let {
                            Log.i("Tarih",
                                it
                            )
                        }
                        //filter fun -> .filter { it.bolgeId == 19 } as ArrayList<NobetciEczaneResponse.NobetciEczaneResponseItem>
                        izmirAdapter.setIzmirData(eczaneler)
                        Log.i("response", eczaneler.toString())
                        binding.pbLoader.visibility = View.GONE
                    }
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    Log.i("response", e.message.toString())
                }
            })

        izmirAPIService.getHastaneler()
            .subscribeOn(Schedulers.newThread())
            .subscribeWith(object : DisposableSingleObserver<HastanelerResponse>() {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onSuccess(hastaneler: HastanelerResponse) {
                    runOnUiThread {
                        hastaneler.onemliyer[0].let {
                            Log.i("yer",
                                it.ADI
                            )
                        }
                        //filter fun -> .filter { it.bolgeId == 19 } as ArrayList<NobetciEczaneResponse.NobetciEczaneResponseItem>
                        izmirAdapter.setHastanelerData(hastaneler)
                        Log.i("response", hastaneler.toString())
                        binding.pbLoader.visibility = View.GONE
                    }
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    Log.i("response", e.message.toString())
                }
            })

        binding.apply {
            izmirAdapter.eczaneClickListener = {
                this@MainActivity.eczaneDetay(it)
            }

            rvMultitypeList.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            rvMultitypeList.adapter = izmirAdapter

            categoryList().forEach {

            }

            tbCategory.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tbSubCategory.removeAllTabs()
                    when(tab?.position){
                        0 -> { tbSubCategory.autoFillList(subCategoryList()) }
                        1 -> { tbSubCategory.autoFillList(subCategoryList2()) }
                    }
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }
                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })

            tbSubCategory.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab?.position){
                        //0 -> { izmirAdapter.adapterFill(subCategoryList()) }
                       // 1 -> { izmirAdapter.adapterFill(subCategoryList2()) }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

            })

        }
    }

    override fun listeyiDoldur(liste: ArrayList<String>) {

    }



}