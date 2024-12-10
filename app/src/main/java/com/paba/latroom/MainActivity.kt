package com.paba.latroom

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.paba.latroom.database.daftarBelanjaDB
import com.paba.latroom.database.daftarbelanja
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async


class MainActivity : AppCompatActivity() {

    private lateinit var DB: daftarBelanjaDB
    private lateinit var adapterDaftar: adapterDaftar
    private var arDaftar: MutableList<daftarbelanja> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        DB = daftarBelanjaDB.getDatabase(this)

        var _fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)

        _fabAdd.setOnClickListener {
            startActivity(Intent(this, TambahDaftar::class.java))
        }

        adapterDaftar = adapterDaftar(arDaftar)

        var _rvDaftar = findViewById<RecyclerView>(R.id.rvDaftar)
        _rvDaftar.layoutManager = LinearLayoutManager(this)
        _rvDaftar.adapter = adapterDaftar
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.Main).async {
            val daftarBelanja = DB.fundaftarBelanjaDAO().selectALL()
            Log.d("data ROOM", daftarBelanja.toString())
            adapterDaftar.isiData(daftarBelanja)
        }
    }
}