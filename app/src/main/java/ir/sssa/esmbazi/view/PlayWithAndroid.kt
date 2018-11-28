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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_with_android)

    }

    private fun imageInDrawable(@DrawableRes id: Int): Drawable {


        var bitmap = BitmapFactory.decodeResource(resources, id)
        bitmap = Bitmap.createScaledBitmap(bitmap, 300, 700, true)

        val BYTE: ByteArray
        val bytearrayoutputstream: ByteArrayOutputStream
        bytearrayoutputstream = ByteArrayOutputStream()



        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytearrayoutputstream)
        BYTE = bytearrayoutputstream.toByteArray()

        return BitmapDrawable(resources, bitmap)


    }
}
