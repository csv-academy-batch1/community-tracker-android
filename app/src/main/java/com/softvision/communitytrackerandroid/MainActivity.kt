package com.softvision.communitytrackerandroid

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.softvision.communitytrackerandroid.Adampter.ListCommunityAdapter
import com.softvision.communitytrackerandroid.data.SampleListCommunity
import com.softvision.communitytrackerandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    public val ACTION_ADD_COMMUNITY: Int = 1
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var listOfCommunity = listOf(
            SampleListCommunity("Mobile Cross Platform"),
            SampleListCommunity("Enterprise.Net"),
            SampleListCommunity("Quality Engineering"),
            SampleListCommunity("Full-Stack Web"),
            SampleListCommunity("Product Delivery"),
            SampleListCommunity("Cloud and DevOps"),
            SampleListCommunity("Product Delivery"),
            SampleListCommunity("Cloud and DevOps")
        )
        with(binding) {
            val recyclerView = findViewById<RecyclerView>(R.id.rvListCommunity)
            val listCommunityAdapter = ListCommunityAdapter(listOfCommunity)

            recyclerView.apply {
                adapter = listCommunityAdapter
                layoutManager = GridLayoutManager(this@MainActivity, 2)
                setHasFixedSize(true)
            }
        }

        binding.fab.setOnClickListener { view ->
            startActivityForResult(Intent(this@MainActivity, ManageCommunityActivity::class.java), ACTION_ADD_COMMUNITY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ACTION_ADD_COMMUNITY) {
            if (resultCode == RESULT_OK) {

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}