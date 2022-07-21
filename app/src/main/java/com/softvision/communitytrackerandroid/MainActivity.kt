package com.softvision.communitytrackerandroid

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.softvision.communitytrackerandroid.adapter.ListCommunityAdapter
import com.softvision.communitytrackerandroid.data.model.Community
import com.softvision.communitytrackerandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG: String = MainActivity::class.java.canonicalName

    public val ACTION_ADD_COMMUNITY: Int = 1
    public val ACTION_UPDATE_COMMUNITY: Int = 2
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val testCommunity = listOf<Community>(
        Community(id = 1, name = ".Net", managerId = 6, description = ".Net Group"),
        Community(id = 2, name = "QE Group", managerId = 7, description = "QE Group"),
        Community(id = 3, name = "Mobile", managerId = 8, description = "Android and iOS Developer"),
        Community(id = 4, name = "Java", managerId = 9, description = "Coffee Enterprise"),
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


      with(binding) {


//                    val response = ApiHelper.apiInterface.getCommunities()
//
//                    if (response.isSuccessful && response.body() != null && response.body()!!.communities.isNotEmpty()) {
//                        val testCommunity = response.body()!!.communities
//                        Log.d(TAG, "$response")
//
//                        val listCommunityAdapter = ListCommunityAdapter(testCommunity, onItemClick = { position, view ->
//                            onItemClick(position, view)
//                        })
//                        rvListCommunity.apply {
//                            adapter = listCommunityAdapter
//                            layoutManager = GridLayoutManager(this@MainActivity, 2)
//                            setHasFixedSize(true)
//                        }
//                            } else {
//                        Toast.makeText(this@MainActivity, "Error on getting community list", Toast.LENGTH_SHORT).show()
//                    }
//                }
//                catch (e: Exception){
//                    Log.e("Error", e.localizedMessage)
//                }

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
        if (view.id == R.id.layoutColor){

        }else if (view.id == R.id.imbUpdateCommunity) {
            val intent = Intent(this@MainActivity, ManageCommunityActivity::class.java)
            intent.putExtra("action", ACTION_UPDATE_COMMUNITY)
            intent.putExtra("community", testCommunity.get(position))
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



