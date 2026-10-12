package com.example.kopiquest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private var keepSplash = true

    override fun onCreate(savedInstanceState: Bundle?) {

        // ==========================================
        // SPLASH SCREEN
        // ==========================================

        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition {
            keepSplash
        }

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Tahan splash selama 1,5 detik
        window.decorView.postDelayed({
            keepSplash = false
        }, 1500)

        // ==========================================
        // TOMBOL MULAI
        // ==========================================

        val btnMulai = findViewById<MaterialButton>(R.id.btnMulai)
        val btnTentang = findViewById<MaterialButton>(R.id.btnTentang)

        btnMulai.setOnClickListener {

            val intent = Intent(this, BermainActivity::class.java)

            startActivity(intent)
        }

        // ==========================================
        // TOMBOL TENTANG
        // ==========================================

        btnTentang.setOnClickListener {

            val builder = AlertDialog.Builder(this)

            builder.setTitle("☕ Tentang KopiQuest")

            builder.setMessage(
                "KopiQuest adalah aplikasi permainan tebak logo kopi " +
                        "yang ada di Indonesia.\n\n" +
                        "Uji pengetahuanmu tentang kedai kopi lokal " +
                        "sambil bermain dan bersenang-senang! ☕"
            )

            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }

            builder.show()
        }
    }
}