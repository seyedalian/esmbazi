package ir.sssa.esmbazi.view

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import ir.sssa.esmbazi.Strong


abstract class BaseActivity : AppCompatActivity() {
    var estefadeShodeTAK:String = ""
    var estefadeShodeTwoTa:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    protected fun CheckISTrue(name:String):Boolean{
        if(Strong().names.contains(name)){
            if(!estefadeShodeTAK.contains(name))
                estefadeShodeTAK+=name
            return true
        }
        return false
    }


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

}
