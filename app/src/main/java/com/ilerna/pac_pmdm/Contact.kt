package com.ilerna.pac_pmdm

import android.provider.BaseColumns

class Contact() {

    // Definimos los campos de la tabla
    var id: Int = 0
    var name: String = ""
    var phone: String = ""

    // Constructor secundario
    constructor(id : Int, name : String, phone : String) : this() {
        this.id = id
        this.name = name
        this.phone = phone
    }

    // Mostrará los datos de cada contacto
    override fun toString(): String {
        return "$id. $name: $phone"
    }


}