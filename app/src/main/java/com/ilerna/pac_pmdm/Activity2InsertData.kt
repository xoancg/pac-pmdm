package com.ilerna.pac_pmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ilerna.pac_pmdm.Activity2 as Activity2

class Activity2InsertData : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity2_insert_data)

/*        // Init DB
        var dbHandler: DatabaseHandler?= null*/

        // Campos de texto
        var txName = findViewById<EditText>(R.id.txName)
        var txPhone = findViewById<EditText>(R.id.txPhone)
/*    txName = ""
    txPhone = ""*/

        // Botón Guardar
        val btnSaveData = findViewById<Button>(R.id.btnSaveData)
        btnSaveData.setOnClickListener{
            // Pasamos los datos de los editText a la función validation para verificar que no estén vacíos
            if (validation(txName.text.toString(), txPhone.text.toString())) {
                val contact = Contact()
                var success = false
                contact.name = txName.text.toString()
                contact.phone = txPhone.text.toString()

                // Llamamos a la función addContact para añadir el contacto
                success = dbHandler!!.addContact(contact)
                Log.d("Insertar datos", "Aquí se debería de haber insertado un contacto")

                if (success) {
                    Toast.makeText(this, "¡Contacto añadido!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    // Función para validar que las entradas no estén vacías
    fun validation(txName : String, txPhone : String): Boolean{
        var validate = false

        if (!txName.equals("") &&
            !txPhone.equals("")){
            validate = true
        } else{
            validate = false
            Toast.makeText(this, "Por favor, rellene todos los campos.", Toast.LENGTH_LONG).show()
        }
        return validate
    }
}

// https://tutorial.eyehunts.com/android/sqlite-database-in-android-kotlin-example/

