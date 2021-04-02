package com.ilerna.pac_pmdm

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class Activity3 : AppCompatActivity() {
    var photo: Uri? = null
    // private val REQUEST_GALLERY = 1001
    private val REQUEST_CAMERA = 1002

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        // Solicitamos al usuario los premisos necesarios para usar la cámara
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA), 1000)
        }

        // Camera button
        val btnCamera = findViewById<Button>(R.id.btnCamera)
        btnCamera.setOnClickListener{
            openCamera()
        }

        // Activity1 back button
        val btnBack = findViewById<Button>(R.id.btnBacktoA1)
        btnBack.setOnClickListener {
        finish()
        }

    }

    private fun openCamera(){
        val value = ContentValues()
        value.put(MediaStore.Images.Media.TITLE,"NewImage")
        photo = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, value)

        // Invocamos a la cámara
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photo)
        startActivityForResult(cameraIntent, REQUEST_CAMERA)
    }


}

// https://www.youtube.com/watch?v=oyIsOhb9ig4&t=197s
// https://developer.android.com/training/camera/cameradirect?hl=es
// https://developer.android.com/reference/android/hardware/camera2/package-summary?hl=es
// https://developer.android.com/training/camera/photobasics
// https://medium.com/developer-student-clubs/android-kotlin-camera-using-gallery-ff8591c26c3e