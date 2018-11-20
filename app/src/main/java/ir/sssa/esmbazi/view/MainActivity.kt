package ir.sssa.esmbazi.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import ir.sssa.esmbazi.R

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    val gameWithAndroid :TextView = findViewById(R.id.gameWithAndroid)
    val ftfGame :TextView = findViewById(R.id.ftfGame)

        gameWithAndroid.setOnClickListener(this)
        ftfGame.setOnClickListener(this)




    }

    override fun onClick(view: View?) {
        if (view != null) {
            if(view.id == R.id.gameWithAndroid){

            }
            if(view.id == R.id.ftfGame){

            }

        }
    }
}
