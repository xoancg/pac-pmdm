package com.ilerna.pac_pmdm

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Activity1 : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private var audio = mutableListOf(R.raw.acdc)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)

        // Init mediaPlayer
        controlPlayer(audio[0])

        // Activity2 button
        val btnGotoA2 = findViewById<Button>(R.id.btnGotoA2)
        btnGotoA2.setOnClickListener(){
            val activity2 = Intent(this, Activity2::class.java)
            // Start Activity2
            startActivity(activity2)
        }

        // Activity3 button
        val btnGotoA3 = findViewById<Button>(R.id.btnGotoA3)
        btnGotoA3.setOnClickListener(){
            val activity3 = Intent(this, Activity3::class.java)
            // Start Activity3
            startActivity(activity3)
        }

        // Activity4 button
        val btnGotoA4 = findViewById<Button>(R.id.btnGotoA4)
        btnGotoA4.setOnClickListener(){
            val activity4 = Intent(this, Activity4::class.java)
            // Start Activity4
            startActivity(activity4)
        }
    }

    private fun controlPlayer(id: Int) {
        // Play button
        val btnPlay = findViewById<FloatingActionButton>(R.id.btnPlay)
        btnPlay.setOnClickListener {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, id)
                Log.d("Activity3", "Duración total: ${mediaPlayer!!.duration / 1000} segundos")
                initSeekbar()
            } else {
                Toast.makeText(this, "Servicio no iniciado!", Toast.LENGTH_SHORT).show()
            }
            mediaPlayer?.start()
            Log.d("Activity3", "Iniciado: ${mediaPlayer!!.currentPosition / 1000} segundos")
        }

        // Stop button
        val btnStop = findViewById<FloatingActionButton>(R.id.btnStop)
        btnStop.setOnClickListener {
            if (mediaPlayer !== null) {
                //Log.d("Activity3", "Detenido en: ${mediaPlayer!!.currentPosition / 1000} segundos")
                mediaPlayer?.stop()
                mediaPlayer?.reset()
                mediaPlayer?.release()
                mediaPlayer = null
            }
        }
    }

    private fun initSeekbar() {
        // Seekbar
        val seekbar = findViewById<SeekBar>(R.id.seekBar)
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) mediaPlayer?.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        // Establecemos el recorrido del seekbar en función de la duración del audio
        seekbar.max = mediaPlayer!!.duration

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    seekbar.progress = mediaPlayer!!.currentPosition
                    handler.postDelayed(this, 0)
                } catch (e: Exception){
                    seekbar.progress = 0
                }
            }
        }, 0)
    }
}