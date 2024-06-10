package com.globalxtreme.gxsalesdemo.model.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CompanyOfficeDatabase(context: Context) :
    SQLiteOpenHelper(context, TABLE_NAME, null, 1) {

    companion object {
        const val TABLE_NAME = "tb_company_office"
        const val ID = "id"
        const val NAME = "name"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $TABLE_NAME ($ID INTEGER, $NAME TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertData(id: Int, name: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, id)
        contentValues.put(NAME, name)
        db.insert(TABLE_NAME, null, contentValues)
    }

    fun deleteAllRow() : Boolean {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, null, null)
        return true
    }

    fun allData() : Cursor? {
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

}