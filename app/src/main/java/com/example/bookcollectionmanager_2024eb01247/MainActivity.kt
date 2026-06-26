/*
 * Student ID: 2024EB01247
 * MainActivity.kt
 */

package com.example.bookcollectionmanager_2024eb01247

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etAuthor = findViewById<EditText>(R.id.etAuthor)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnViewBooks = findViewById<Button>(R.id.btnViewBooks)

        btnSave.setOnClickListener {

            val title = etTitle.text.toString().trim()
            val author = etAuthor.text.toString().trim()

            if (title.isEmpty() || author.isEmpty()) {

                Toast.makeText(
                    this,
                    "Please enter all fields",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val success = databaseHelper.insertBook(title, author)

                if (success) {

                    Toast.makeText(
                        this,
                        "Book Saved Successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    etTitle.text.clear()
                    etAuthor.text.clear()

                } else {

                    Toast.makeText(
                        this,
                        "Error saving book",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        btnViewBooks.setOnClickListener {

            val intent = Intent(this, BookListActivity::class.java)
            startActivity(intent)

        }
    }
}