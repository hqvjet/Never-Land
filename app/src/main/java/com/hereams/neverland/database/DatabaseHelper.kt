package com.hereams.neverland.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE Character (player_name TEXT PRIMARY KEY, player_level INTEGER, player_exp INTEGER, player_class TEXT, current_point INTEGER)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Character")
        onCreate(db)
    }

    fun addCharacter(playerName: String, playerLevel: Int, playerExp: Int, playerClass: String, currentPoint: Int): Long {
        val values = ContentValues().apply {
            put("player_name", playerName)
            put("player_level", playerLevel)
            put("player_exp", playerExp)
            put("player_class", playerClass)
            put("current_point", currentPoint)
        }
        val db = writableDatabase
        val rowId = db.insert("Character", null, values)
        db.close()
        return rowId
    }

    fun updateCharacter(playerName: String, playerLevel: Int, playerExp: Int, playerClass: String, currentPoint: Int): Int {
        val values = ContentValues().apply {
            put("player_level", playerLevel)
            put("player_exp", playerExp)
            put("player_class", playerClass)
            put("current_point", currentPoint)
        }
        val db = writableDatabase
        val rowsAffected = db.update("Character", values, "player_name = ?", arrayOf(playerName))
        db.close()
        return rowsAffected
    }

    fun deleteCharacter(playerName: String): Int {
        val db = writableDatabase
        val rowsAffected = db.delete("Character", "player_name = ?", arrayOf(playerName))
        db.close()
        return rowsAffected
    }

    fun getCharacter(playerName: String): Character? {
        val db = readableDatabase
        val cursor = db.query(
            "Character",
            arrayOf("player_name", "player_level", "player_exp", "player_class", "current_point"),
            "player_name = ?",
            arrayOf(playerName),
            null,
            null,
            null
        )
        val character = if (cursor.moveToFirst()) {
            Character(
                cursor.getString(cursor.getColumnIndexOrThrow("player_name")),
                cursor.getInt(cursor.getColumnIndexOrThrow("player_level")),
                cursor.getInt(cursor.getColumnIndexOrThrow("player_exp")),
                cursor.getString(cursor.getColumnIndexOrThrow("player_class")),
                cursor.getInt(cursor.getColumnIndexOrThrow("current_point"))
            )
        } else {
            null
        }
        cursor.close()
        db.close()
        return character
    }

    companion object {
        private const val DATABASE_NAME = "mydatabase.db"
        private const val DATABASE_VERSION = 1
    }
}

data class Character(val playerName: String, val playerLevel: Int, val playerExp: Int, val playerClass: String, val currentPoint: Int)
