package ir.sssa.esmbazi.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import ir.sssa.esmbazi.R


class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val iconInSplash:ImageView =findViewById(R.id.iconInSplash)
        iconInSplash.alpha=0f

        iconInSplash.animate().alpha(1f).rotation(360f).setDuration(2000L)
        Handler().postDelayed({
            startActivity(Intent(this@Splash,MainActivity::class.java))
            finish()

        },2500L)
    }
}
