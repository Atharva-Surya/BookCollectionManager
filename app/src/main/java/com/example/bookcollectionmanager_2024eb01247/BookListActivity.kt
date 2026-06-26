/*
 * Student ID: 2024EB01247
 * BookListActivity.kt
 */

package com.example.bookcollectionmanager_2024eb01247

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        val recyclerView =
            findViewById<RecyclerView>(R.id.recyclerViewBooks)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val databaseHelper = DatabaseHelper(this)

        val books = databaseHelper.getAllBooks()

        recyclerView.adapter = BookAdapter(books)
    }
}