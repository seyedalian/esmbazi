package ir.sssa.esmbazi.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.constraint.ConstraintLayout
import ir.sssa.esmbazi.R
import java.io.ByteArrayOutputStream

class PlayWithAndroid : BaseActivity() {
    override fun init() {
        listView =findViewById(R.id.playGround)
        starText =findViewById(R.id.starText)
        timeText =findViewById(R.id.timeText)
        sendText =findViewById(R.id.sendText)
        sendIcon =findViewById(R.id.sendIcon)
        help =findViewById(R.id.help)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_with_android)
        init()
        refreshDisplay(this)
        relationshipPlayWithAndroid()
    }


}
