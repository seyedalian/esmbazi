package ir.sssa.esmbazi.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import ir.sssa.esmbazi.Strong
import java.io.ByteArrayOutputStream


abstract class BaseActivity : AppCompatActivity() {
    //strong spent names
    var spents = hashSetOf("")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    //check your name is true or not
    protected fun checkIsTrue(name:String):Int{
      val ser = Strong().names.find{s-> s == name}

        if(ser == name){
            if(spentHasThis(name)==0){
                spents.add(name)
                return 1
            }else{
                return 0
            }
        }
        return -1
    }
    //get name with firstOfName
    protected fun getName(firstOfName:Char):String{
        val ser = Strong().names.filter { s -> s.first() ==firstOfName }
        var n:Int =ser.size
        n--
        for(i in 0 .. n){
            if(spentHasThis(ser[i])==0)
                return ser[i]

        }

        return "iLoser"
    }
    //check spent has a name
    private fun spentHasThis(name: String): Int {
        val inSpent = spents.find { s -> s == name }
        if (inSpent == name) {
            return 1
        } else {

            return 0
        }
    }



//make Toast
    protected fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    protected fun toolbar(@IdRes toolbarId: Int) {
        setSupportActionBar(findViewById<View>(toolbarId) as Toolbar)
    }




    protected fun simpleAlert(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .create()
            .show()
    }
    //convert imageRes to Drawable object
    private fun imageInDrawable(@DrawableRes id: Int): Drawable {


        var bitmap = BitmapFactory.decodeResource(resources, id)
        bitmap = Bitmap.createScaledBitmap(bitmap, 300, 700, true)

        val bytearrayoutputstream= ByteArrayOutputStream()



        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytearrayoutputstream)

        return BitmapDrawable(resources, bitmap)


    }

}
