package com.softvision.communitytrackerandroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.softvision.communitytrackerandroid.Adampter.ListCommunityAdapter
import com.softvision.communitytrackerandroid.data.SampleListCommunity
import com.softvision.communitytrackerandroid.data.api.ApiHelper
import com.softvision.communitytrackerandroid.data.model.Community
import com.softvision.communitytrackerandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG: String = MainActivity::class.java.canonicalName

    public val ACTION_ADD_COMMUNITY: Int = 1
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenCreated {
            try {
                val communities = ApiHelper.apiInterface.getCommunities()
                Log.d(TAG, "$communities")
            }
            catch (e: Exception){
                Log.e("Error", e.localizedMessage)
            }
        }

        // TODO Use value from API to display community list
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

            fab.setOnClickListener { view ->
                val intent = Intent(this@MainActivity, ManageCommunityActivity::class.java)
                intent.putExtra("action", ACTION_ADD_COMMUNITY)
                startActivityForResult(intent, ACTION_ADD_COMMUNITY)
            }
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