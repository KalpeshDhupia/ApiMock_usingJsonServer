package com.example.postrequsingjsonserver

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPost.setOnClickListener {
            val id = etId.text.toString()
            val title = etTitle.text.toString()
            val author = etAuthor.text.toString()

            if (id.isNotEmpty() && title.isNotEmpty() && author.isNotEmpty()) {
                postData(id, title, author)
            } else {
                Toast.makeText(this, "Enter all data", Toast.LENGTH_SHORT).show()
            }
        }
        callApi()

    }

    private fun callApi() {
        val apiClient = Network.getInstance().create(ApiClient::class.java)
        val call = apiClient.getPosts()
        call.enqueue(object : Callback<List<Post>> {

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.body() != null) {
                    response.body()?.let {
                        buildData(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun postData(id: String, title: String, author: String) {
        idLoadingPB.visibility = View.VISIBLE
        val apiClient = Network.getInstance().create(ApiClient::class.java)
        val dataModel = DataModel(id.toInt(), title, author)
        val call = apiClient.createPost(dataModel)
        call.enqueue(object : Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                Toast.makeText(this@MainActivity, "Data added to API", Toast.LENGTH_SHORT).show()
                idLoadingPB.visibility = View.GONE
                val addedPost = response.body()
                Log.d(
                    "api response",
                    addedPost?.id.toString() + " " + addedPost?.title.toString() + " " + addedPost?.author.toString()
                )
                val responseString = response.code()
                    .toString() + "\nId : " + addedPost?.id + "\n" + "Title : " + addedPost?.title + "\n" + "Author : " + addedPost?.author
                idTVResponse.text = responseString

                callApi()
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                idTVResponse.text = t.message
            }

        })
    }

    private fun buildData(list: List<Post>) {
        val layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.layoutManager = layoutManager
        val postAdapter = PostAdapter(list)
        recyclerView.adapter = postAdapter
    }

}