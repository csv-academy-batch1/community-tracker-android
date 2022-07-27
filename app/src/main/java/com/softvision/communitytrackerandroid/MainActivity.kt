package com.softvision.communitytrackerandroid

import android.annotation.SuppressLint
import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.softvision.communitytrackerandroid.adapter.ListCommunityAdapter
import com.softvision.communitytrackerandroid.data.DataObject
import com.softvision.communitytrackerandroid.data.api.ApiHelper
import com.softvision.communitytrackerandroid.data.model.Community
import com.softvision.communitytrackerandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG: String = MainActivity::class.java.canonicalName

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private  var communityList: MutableList<Community> = mutableListOf()
    private lateinit var listCommunityAdapter: ListCommunityAdapter
    private lateinit var noCommunitiesFound: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            this@MainActivity.noCommunitiesFound = tvNoCommunityFound
//            communityList = DataObject.getCommunityList() as MutableList<Community>
            listCommunityAdapter = ListCommunityAdapter(communityList, onItemClick = { position, view ->
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

        loadCommunity()

    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadCommunity() {
        lifecycleScope.launchWhenCreated {
            try {
                val response = ApiHelper.apiInterface.getCommunities()

                if (response.isSuccessful && response.body() != null && response.body()!!.communities.isNotEmpty() ) {
                    Log.d(TAG, "$response")

                    val communityList = response.body()!!.communities
                    if (communityList.isEmpty()){
                        noCommunitiesFound.visibility = View.VISIBLE
                    }
                    this@MainActivity.communityList.clear()
                    this@MainActivity.communityList.addAll(communityList)
                    listCommunityAdapter.notifyDataSetChanged()


                } else {
                    val builder: AlertDialog.Builder? = this@MainActivity.let {
                        AlertDialog.Builder(it)
                    }
                    builder?.setTitle("List of Communities")
                        ?.setMessage("Error")
                    val dialog: AlertDialog? = builder?.create()
                    dialog?.show()
                    noCommunitiesFound.visibility = View.VISIBLE
//                    Toast.makeText(this@MainActivity, "Error on getting community list", Toast.LENGTH_SHORT).show()
                }
            }
            catch (e: Exception){
                Log.e("Error", e.localizedMessage)
            }
        }
    }

    private fun onItemClick(position: Int, view: View) {
        Log.d(TAG, "position $position")
        if (view.id == R.id.cvCommunity) {
            Log.d(TAG, "card is clicked")
            val intent = Intent(this@MainActivity, ResourceActivity::class.java)
            startActivity(intent)
        } else if (view.id == R.id.imbUpdateCommunity) {
            Log.d(TAG, "Update Button is Clicked")
            val intent = Intent(this@MainActivity, ManageCommunityActivity::class.java)
            intent.putExtra("action", ACTION_UPDATE_COMMUNITY)
            intent.putExtra("community", communityList[position])
            startActivityForResult(intent, ACTION_UPDATE_COMMUNITY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == ACTION_ADD_COMMUNITY || requestCode == ACTION_UPDATE_COMMUNITY) {
                loadCommunity()
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
