package id.rafilutfansyah.pokedex.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.rafilutfansyah.pokedex.databinding.ActivitySplashBinding
import id.rafilutfansyah.pokedex.view.main.MainActivity


class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnStart.setOnClickListener {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}