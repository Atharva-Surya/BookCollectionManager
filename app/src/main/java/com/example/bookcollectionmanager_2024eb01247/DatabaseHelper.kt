/*
 * Student ID: 2024EB01247
 * DatabaseHelper.kt
 */

package com.example.bookcollectionmanager_2024eb01247

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "BookCollection.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_BOOKS = "Books"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_AUTHOR = "author"
    }

    override fun onCreate(db: SQLiteDatabase) {

        val createTable = """
            CREATE TABLE $TABLE_BOOKS(
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_TITLE TEXT,
                $COLUMN_AUTHOR TEXT
            )
        """.trimIndent()

        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_BOOKS")
        onCreate(db)
    }

    fun insertBook(title: String, author: String): Boolean {

        val db = writableDatabase

        val values = ContentValues()
        values.put(COLUMN_TITLE, title)
        values.put(COLUMN_AUTHOR, author)

        val result = db.insert(TABLE_BOOKS, null, values)

        db.close()

        return result != -1L
    }

    fun getAllBooks(): MutableList<Book> {

        val list = mutableListOf<Book>()

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_BOOKS",
            null
        )

        if (cursor.moveToFirst()) {

            do {

                val title =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))

                val author =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AUTHOR))

                list.add(Book(title, author))

            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return list
    }
}