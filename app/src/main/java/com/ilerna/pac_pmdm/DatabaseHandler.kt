package com.ilerna.pac_pmdm

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHandler (context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){

    // Comprueba si existe la BD y, si no existe, la crea
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME($ID Integer PRIMARY KEY, $NAME TEXT, $PHONE TEXT)"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Se llama a esta función cuando la BD necesita ser actualizada
    }

    // Función para añadir contactos
    fun addContact(contact: Contact): Boolean {
        // Crear o abrir la BD en formato escritura
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME, contact.name)
        values.put(PHONE, contact.phone)
        val success = db.insert(TABLE_NAME, null, values) // Devuelve el nº de filas alteradas o, si hay error, devuelve -1
        db.close()
        Log.v("InsertID", "$success") //
        return (Integer.parseInt("$success") != -1) // Si es diferente de -1, devuelve true; si no, false
    }

    // Función para mostrar contactos
    fun getAllContacts(): List<Contact>{
        var allContact = mutableListOf<Contact>()
        val db = readableDatabase // Abrimos como lectura
        val selectAllQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectAllQuery, null) // El cursor recorerrá cada fila
        if (cursor != null) { // Si el cursor devuelve datos
            cursor.moveToFirst()
            do {
                var id = cursor.getString(cursor.getColumnIndex(ID))
                var name = cursor.getString(cursor.getColumnIndex(NAME))
                var phone = cursor.getString(cursor.getColumnIndex(PHONE))

                allContact.add(Contact(id.toInt(), name, phone))
            } while (cursor.moveToNext()) // Mientras haya más filas, incrementamos la variable
        }
        cursor.close()
        db.close()
        return allContact // Devolvemos un string con todas las líneas
    }

    companion object{
        private val DB_NAME = "ContactsDB"
        private val DB_VERSION = 1
        private val TABLE_NAME = "contacts"
        private val ID = "id"
        private val NAME = "name"
        private val PHONE = "phone"
    }
}