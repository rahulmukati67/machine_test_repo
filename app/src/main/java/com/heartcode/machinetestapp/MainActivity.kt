package com.heartcode.machinetestapp

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.heartcode.machinetestapp.Adapter.AlbumAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var albumViewModel: AlbumViewModel
    private lateinit var btnRefresh : Button
    private lateinit var noInternetText :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         btnRefresh = findViewById<Button>(R.id.btnRefresh)
         noInternetText = findViewById<TextView>(R.id.noInternetTextView)
        loadApiData()
        btnRefresh.setOnClickListener {
            loadApiData()
        }

    }

    private fun loadApiData() {
        //check the internet Connectivity
        if (isNetworkAvailable()) {
            if(btnRefresh.visibility== View.VISIBLE && noInternetText.visibility == View.VISIBLE){
                btnRefresh.visibility = View.GONE
                noInternetText.visibility = View.GONE
            }
            // Initialize ViewModel
            albumViewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)

            // Set up RecyclerView
            val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
            val adapter = AlbumAdapter()
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)

            albumViewModel.albums.observe(this, Observer {
                adapter.submitList(it)
            })
            albumViewModel.errorMessage.observe(this, Observer { message ->
                message?.let {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                }
            })

        } else {
            // Handle no internet connectivity
            if(btnRefresh.visibility== View.GONE && noInternetText.visibility == View.GONE){
                btnRefresh.visibility = View.VISIBLE
                noInternetText.visibility = View.VISIBLE
            }
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
