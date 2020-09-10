package com.morbis.spe.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.morbis.spe.R
import com.morbis.spe.view.adapter.JadwalDokterAdapter
import com.morbis.spe.viewModel.JadwalDokterViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_kosong.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val vm: JadwalDokterViewModel by viewModel()
    private lateinit var mAdapter: JadwalDokterAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        mAdapter = JadwalDokterAdapter()
        initRecycler(recyclerDokter)
        recyclerDokter.adapter = mAdapter
        vmHandle()
        swipeDokter!!.isRefreshing = true
        vm.getJadwal()
        swipeDokter.setOnRefreshListener {
            if (swipeDokter!!.isRefreshing) swipeDokter.isRefreshing = true
            vm.getJadwal()
        }
        val click = mAdapter.clickEvent.subscribe {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("nama", it.dokter)
            intent.putExtra("foto", it.foto)
            intent.putExtra("praktek", it.jadwalPraktek)
            intent.putExtra("hadir", it.kehadiran)
            intent.putExtra("poli", it.poli)
            startActivity(intent)
        }
    }



    private fun vmHandle() {
        vm.data.observe(this, Observer {
            if (it != null){
                if (it.isNotEmpty()){
                    linearKosong.visibility = View.GONE
                    mAdapter.setData(it)
                } else {
                    textMessage?.text = "Data tidak tersedia"
                    linearKosong.visibility = View.VISIBLE
                }
            }
        })
        vm.event.observe(this, Observer {
            if (it != null) {
                if (it.isLoading) {
                    linearKosong.visibility = View.VISIBLE
                    recyclerDokter.visibility= View.GONE
                    if (!swipeDokter.isRefreshing) swipeDokter!!.isRefreshing = true
                } else {
                    recyclerDokter.visibility= View.VISIBLE
                    if (swipeDokter.isRefreshing) swipeDokter!!.isRefreshing = false
                }
                if (it.isSuccess != null){
                    if (it.isSuccess) {
                    }
                }
                if (it.message != null)textMessage?.text = "${it.message}"
            }
        })
    }

    protected fun initRecycler(recycler: RecyclerView?) {
        val mManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler!!.apply {
            layoutManager = mManager
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
        }
    }
}