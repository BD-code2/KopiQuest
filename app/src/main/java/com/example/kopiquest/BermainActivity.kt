package com.example.kopiquest

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

data class Soal(
    val gambar: Int,
    val jawaban: String
)

class BermainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private lateinit var tvNomorSoal: TextView
    private lateinit var tvSkor: TextView
    private lateinit var tvPertanyaan: TextView
    private lateinit var etJawaban: EditText
    private lateinit var btnJawab: MaterialButton
    private lateinit var btnSkip: MaterialButton
    private lateinit var btnKembali: ImageButton
    private lateinit var tvFeedback: TextView
    private lateinit var imgLogoKopi: ImageView

    private var nomorSoal = 0
    private var skor = 0

    // ==========================================
    // 11 LOGO KOPI
    // ==========================================

    private val semuaSoal = listOf(

        Soal(
            R.drawable.anuenue,
            "anuenue"
        ),

        Soal(
            R.drawable.janji_jiwa,
            "janji jiwa"
        ),

        Soal(
            R.drawable.kopi_kenangan,
            "kopi kenangan"
        ),

        Soal(
            R.drawable.kopi_way,
            "kopi way"
        ),

        Soal(
            R.drawable.lain_hati,
            "lain hati"
        ),

        Soal(
            R.drawable.point_coffe,
            "point coffee"
        ),

        Soal(
            R.drawable.sober,
            "sober"
        ),

        Soal(
            R.drawable.starbucks,
            "starbucks"
        ),

        Soal(
            R.drawable.tuah_kopi,
            "tuah kopi"
        ),

        Soal(
            R.drawable.tuku,
            "tuku"
        ),

        Soal(
            R.drawable.fore_coffe,
            "fore coffee"
        )
    )

    // ==========================================
    // 10 SOAL YANG AKAN DIMAINKAN
    // ==========================================

    private var soalGame = listOf<Soal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_bermain)

        // ==========================================
        // BACKSOUND
        // ==========================================
        mediaPlayer = MediaPlayer.create(this, R.raw.backsound)
        mediaPlayer?.isLooping = true
        mediaPlayer?.setVolume(0.25f, 0.25f)
        mediaPlayer?.start()

        // ==========================================
        // HUBUNGKAN XML DENGAN KOTLIN
        // ==========================================

        tvNomorSoal = findViewById(R.id.tvNomorSoal)
        tvSkor = findViewById(R.id.tvSkor)
        tvPertanyaan = findViewById(R.id.tvPertanyaan)
        etJawaban = findViewById(R.id.etJawaban)
        btnJawab = findViewById(R.id.btnJawab)
        btnSkip = findViewById(R.id.btnSkip)
        btnKembali = findViewById(R.id.btnKembali)
        tvFeedback = findViewById(R.id.tvFeedback)
        imgLogoKopi = findViewById(R.id.imgLogoKopi)

        // ==========================================
        // ACAK 11 LOGO → AMBIL 10
        // ==========================================

        soalGame = semuaSoal
            .shuffled()
            .take(10)

        // Tampilkan soal pertama
        tampilkanSoal()

        // ==========================================
        // TOMBOL KEMBALI
        // ==========================================

        btnKembali.setOnClickListener {
            finish()
        }

        // ==========================================
        // TOMBOL JAWAB
        // ==========================================

        btnJawab.setOnClickListener {
            cekJawaban()
        }

        // ==========================================
        // TOMBOL SKIP
        // ==========================================

        btnSkip.setOnClickListener {
            lewatiSoal()
        }
    }

    // ==========================================
    // MENAMPILKAN SOAL
    // ==========================================

    private fun tampilkanSoal() {

        val soalSekarang = soalGame[nomorSoal]

        tvNomorSoal.text = "SOAL ${nomorSoal + 1}/10"

        tvSkor.text = "⭐ $skor"

        tvPertanyaan.text = "Logo dari brand kopi mana ini?"

        imgLogoKopi.setImageResource(soalSekarang.gambar)

        etJawaban.text.clear()

        tvFeedback.text = ""
    }

    // ==========================================
    // CEK JAWABAN
    // ==========================================

    private fun cekJawaban() {

        val jawabanUser = etJawaban.text
            .toString()
            .trim()
            .lowercase()

        if (jawabanUser.isEmpty()) {

            Toast.makeText(
                this,
                "Isi jawaban terlebih dahulu!",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        val jawabanBenar = soalGame[nomorSoal].jawaban

        // ==========================================
        // JAWABAN BENAR
        // ==========================================

        if (jawabanUser == jawabanBenar) {

            skor += 10

            tvSkor.text = "⭐ $skor"

            tvFeedback.text = "✓ Jawaban benar!"
            tvFeedback.setTextColor(
                android.graphics.Color.rgb(46, 125, 50)
            )

            // Matikan tombol sementara
            btnJawab.isEnabled = false
            btnSkip.isEnabled = false

            nomorSoal++

            if (nomorSoal < 10) {

                btnJawab.postDelayed({

                    btnJawab.isEnabled = true
                    btnSkip.isEnabled = true

                    tampilkanSoal()

                }, 1200)

            } else {

                btnJawab.postDelayed({

                    bukaHalamanHasil()

                }, 1200)
            }

        } else {

            tvFeedback.text = "✗ Jawaban salah, coba lagi!"

            tvFeedback.setTextColor(
                android.graphics.Color.rgb(198, 40, 40)
            )
        }
    }

    // ==========================================
    // SKIP SOAL
    // ==========================================

    private fun lewatiSoal() {

        nomorSoal++

        if (nomorSoal < 10) {

            tampilkanSoal()

        } else {

            // ==========================================
            // SELESAI → HALAMAN HASIL
            // ==========================================

            bukaHalamanHasil()
        }
    }

    // ==========================================
    // BUKA HALAMAN HASIL
    // ==========================================

    private fun bukaHalamanHasil() {

        val intent = Intent(this, HasilActivity::class.java)

        intent.putExtra("SKOR_AKHIR", skor)

        startActivity(intent)

        finish()
    }

    // ==========================================
    // matikan & release musik
    // ==========================================

    override fun onDestroy() {
        mediaPlayer?.release()
        mediaPlayer = null
        super.onDestroy()
    }
}