package com.softvision.communitytrackerandroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.softvision.communitytrackerandroid.adapter.ListCommunityAdapter
import com.softvision.communitytrackerandroid.data.api.ApiHelper
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



        with(binding) {
            lifecycleScope.launchWhenCreated {
                try {
                    val response = ApiHelper.apiInterface.getCommunities()

                    if (response.isSuccessful && response.body() != null && response.body()!!.communities.isNotEmpty()) {
                        val testCommunity = response.body()!!.communities
                        Log.d(TAG, "$response")

                        val listCommunityAdapter = ListCommunityAdapter(testCommunity)

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