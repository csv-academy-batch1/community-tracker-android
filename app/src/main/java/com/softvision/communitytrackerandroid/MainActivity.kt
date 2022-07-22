package com.softvision.communitytrackerandroid

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.softvision.communitytrackerandroid.adapter.ListCommunityAdapter
import com.softvision.communitytrackerandroid.data.api.ApiHelper
import com.softvision.communitytrackerandroid.data.model.Community
import com.softvision.communitytrackerandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG: String = MainActivity::class.java.canonicalName

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val testCommunity = listOf<Community>(
        Community(id = 1, name = "Enterprise.net", managerId = 6, description = ".Net Group"),
        Community(id = 2, name = "Full-Stack Web", managerId = 7, description = "Web Group"),
        Community(id = 3, name = "Quality Engineering", managerId = 8, description = "QE Group"),
        Community(id = 4, name = "Cloud and DevOps", managerId = 6, description = "DevOps Group"),
        Community(id = 5, name = "Big Data & Analytics", managerId = 7, description = "QE Group"),
        Community(id = 6, name = "Product Delivery", managerId = 8, description = "Android and iOS Developer"),
        Community(id = 7, name = "Enterprise Coffee", managerId = 6, description = ".Net Group"),
        Community(id = 8, name = "Mobile Cross Platform", managerId = 7, description = "Android and iOS Developer"),
        Community(id = 9, name = "Research and Developent", managerId = 8, description = "Android and iOS Developer")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            /*
            lifecycleScope.launchWhenCreated {
                try {
                    val response = ApiHelper.apiInterface.getCommunities()

                    if (response.isSuccessful && response.body() != null && response.body()!!.communities.isNotEmpty()) {
                        val testCommunity = response.body()!!.communities
                        Log.d(TAG, "$response")

                        val listCommunityAdapter = ListCommunityAdapter(testCommunity, onItemClick = { position, view ->
                            onItemClick(position, view)
                        })

                        rvListCommunity.apply {
                            adapter = listCommunityAdapter
                            layoutManager = GridLayoutManager(this@MainActivity, 2)
                            setHasFixedSize(true)
                        }
                    } else {
                        Toast.makeText(this@MainActivity, "Error on getting community list", Toast.LENGTH_SHORT).show()
                    }
                }
                catch (e: Exception){
                    Log.e("Error", e.localizedMessage)
                }
            } 
            */

            val listCommunityAdapter = ListCommunityAdapter(testCommunity, onItemClick = { position, view ->
                onItemClick(position, view)
            })

            rvListCommunity.apply {
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

    fun onItemClick(position: Int, view: View) {
        Log.d(TAG, "position $position")
        if (view.id == R.id.cvCommunity) {
            Log.d(TAG, "card is clicked")
        } else if (view.id == R.id.imbUpdateCommunity) {
            Log.d(TAG, "Update Button is Clicked")
            val intent = Intent(this@MainActivity, ManageCommunityActivity::class.java)
            intent.putExtra("action", ACTION_UPDATE_COMMUNITY)
            intent.putExtra("community", testCommunity[position])
            startActivityForResult(intent, ACTION_UPDATE_COMMUNITY)
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

    companion object {
        public val ACTION_ADD_COMMUNITY: Int = 1
        public val ACTION_UPDATE_COMMUNITY: Int = 2
    }
}
