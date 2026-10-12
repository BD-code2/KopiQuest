package com.example.kopiquest

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class HasilActivity : AppCompatActivity() {

    private lateinit var tvSkorAkhir: TextView
    private lateinit var tvPesan: TextView
    private lateinit var btnMainLagi: MaterialButton
    private lateinit var btnMenuUtama: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_hasil)

        // Hubungkan XML dengan Kotlin
        tvSkorAkhir = findViewById(R.id.tvSkorAkhir)
        tvPesan = findViewById(R.id.tvPesan)
        btnMainLagi = findViewById(R.id.btnMainLagi)
        btnMenuUtama = findViewById(R.id.btnMenuUtama)

        // Ambil skor dari BermainActivity
        val skor = intent.getIntExtra("SKOR_AKHIR", 0)

        // Tampilkan skor
        tvSkorAkhir.text = "$skor / 100"

        // Tentukan pesan berdasarkan skor
        tvPesan.text = when {

            skor == 100 -> {
                "🔥 Sempurna! Kamu master kopi!"
            }

            skor >= 80 -> {
                "☕ Mantap! Kamu jago mengenali logo kopi!"
            }

            skor >= 60 -> {
                "👍 Bagus! Pengetahuan kopi kamu lumayan!"
            }

            skor >= 40 -> {
                "🙂 Lumayan! Yuk kenali lebih banyak kopi!"
            }

            else -> {
                "💪 Jangan menyerah! Coba lagi yuk!"
            }
        }

        // ==========================================
        // MAIN LAGI
        // ==========================================

        btnMainLagi.setOnClickListener {

            val intent = Intent(this, BermainActivity::class.java)

            startActivity(intent)

            finish()
        }

        // ==========================================
        // MENU UTAMA
        // ==========================================

        btnMenuUtama.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)

            finish()
        }
    }
}