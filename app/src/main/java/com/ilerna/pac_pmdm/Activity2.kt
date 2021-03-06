package com.ilerna.pac_pmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

// Init DB
var dbHandler: DatabaseHandler?= null

class Activity2 : AppCompatActivity() {
    var tablaCreada: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)



        // Mensaje indicando que estamos en la Activity 2
        Toast.makeText(this, "Estás en Activity 2", Toast.LENGTH_SHORT).show()

        // Botón Crear tabla
        val btnCreateTable = findViewById<Button>(R.id.btnCamera)
        btnCreateTable.setOnClickListener{
            // Init DB
            dbHandler = DatabaseHandler(this)
            tablaCreada = true
            Toast.makeText(this, "Tabla creada. Ya puedes insertar datos", Toast.LENGTH_SHORT).show()
        }


        // Botón insertar datos
        val btnInsertData = findViewById<Button>(R.id.btnInsertData)
        btnInsertData.setOnClickListener(){
            val a2InsertData = Intent(this, Activity2InsertData::class.java)
            if (tablaCreada == false) {
                Toast.makeText(this, "¡Debes crear la tabla antes de insertar datos!", Toast.LENGTH_SHORT).show()
            } else {
                // Iniciamos la activity
                startActivity(a2InsertData)
            }

        }

        // Botón consultar datos
        val btnSelectData = findViewById<Button>(R.id.btnSelectData)
        btnSelectData.setOnClickListener() {

            if (tablaCreada == false) {
                Toast.makeText(this, "¡Debes crear la tabla antes de consultar datos!", Toast.LENGTH_SHORT).show()
            } else {

                // String
                val data = dbHandler?.getAllContacts()
                val tvSelectData = findViewById<TextView>(R.id.tvSelectData)
                tvSelectData.setText(data?.joinToString("\n"))
            }
        }

        // Botón volver a Activity1
        val btnBack = findViewById<Button>(R.id.btnBacktoA1)
        btnBack.setOnClickListener {
            finish()
        }
    }
}

// https://tutorial.eyehunts.com/android/sqlite-database-in-android-kotlin-example/
// https://www.tutorialkart.com/kotlin-android/android-sqlite-example-application/