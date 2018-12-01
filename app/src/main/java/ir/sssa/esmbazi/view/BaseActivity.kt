package ir.sssa.esmbazi.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.*
import ir.sssa.esmbazi.ListViewsOb.Adapter
import ir.sssa.esmbazi.ListViewsOb.Condition
import ir.sssa.esmbazi.ListViewsOb.GMClass
import ir.sssa.esmbazi.R
import ir.sssa.esmbazi.ShareReference
import ir.sssa.esmbazi.Strong
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.collections.ArrayList


abstract class BaseActivity : AppCompatActivity() {
    //strong spent names
    var spents = hashSetOf("")
    var star:Int =0
    var firstOfName:Char  =randChar()
    var  messageList:List<GMClass> = ArrayList()
    lateinit var adapter:Adapter
    val NAME_FILE_STAR:String ="emtiaz"

    //TODO you must init  objects in your class
    lateinit var listView:ListView
    lateinit var   starText:TextView
    lateinit var timeText:TextView
    lateinit var sendText:EditText
    lateinit var sendIcon:ImageView
    lateinit var help:TextView
    lateinit var starIcon: ImageView
    lateinit var timeIcon: ImageView
    lateinit var bluetoothTextView:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

  abstract fun init()

    fun refreshDisplay(context: Context){
        adapter = Adapter(context, R.layout.item_of_list_game,messageList)
        listView.adapter =adapter
    }

    fun relationship(context: Context){
        star = getStarFromFile(context)
        sendText.hint = "اسم با  "+firstOfName
        starText.text =star.toString()


        help.setOnClickListener {
            if(star>=2){

                val myname:String =getName(firstOfName)
                if(myname !="iLoser") {
                    (messageList as ArrayList).add(GMClass(myname, true, Condition.TRUE))
                    star=star-2
                    startToFile(context)
                }
                else {
                    toast("من اسمی ندارم!")
                    finish()
                }
                starText.text = star.toString()
                firstOfName = myname.last()
                val myName: String = getName(firstOfName)
                firstOfName=myName.last()
                if (myName != "iLoser")
                    (messageList as ArrayList).add(GMClass(myName, false, Condition.TRUE))
                adapter.notifyDataSetChanged()
                sendText.hint = "اسم با  "+firstOfName

            }else{
                //TODO shareRefrence
                toast("شما امتیازی کافی نداری!")
                toast("حدقل 2 امتیاز")
            }


        }

    }

    fun startToFile(context: Context) {
        ShareReference().writeFileOnInternalStorage(context, NAME_FILE_STAR, star.toString() +",s" )
    }

    private fun getStarFromFile(context: Context): Int {
        val s =ShareReference().readFileOnInternalStorage(context,NAME_FILE_STAR)
         var int= 0
        if(s!=null) {
            val ss = s.split(",")
            int = ss[0].toInt()

        }else{
            startToFile(context)
        }
        return int

    }


    fun randChar():Char{
       val n:Int = Random().nextInt(Strong().horof.size)+1
        return Strong().horof.elementAt(n)
    }


    //check your name is true or not
    protected fun checkIsTrue(name:String):Int{
        val first:Char = name.first()
        val names= Strong().names.filter { s -> s.first() ==first }
        var find =false
            for(i in 0 .. names.size-1){
                if(names[i] ==name){
                    find =true
                    break
                }
            }
        if(find){
            if(spentHasThis(name)==0){
                spents.add(name)
                return 1
                //name is true and is not in spent
            }else{
                return 0
                //is in spent
            }
        }
        return -1 //not in database
    }
    //get name with firstOfName
    protected fun getName(first:Char):String{
        val ser = Strong().names.filter { s -> s.first() ==first }
        var n:Int =ser.size
        n--
        for(i in 0 .. n){
            if(spentHasThis(ser[i])==0) {
                firstOfName = ser[i].last()
                spents.add(ser[i])
                return ser[i]
            }
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


