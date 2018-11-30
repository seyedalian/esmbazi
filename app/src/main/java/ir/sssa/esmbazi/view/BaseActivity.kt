package ir.sssa.esmbazi.view

import android.content.Context
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
import android.widget.*
import ir.sssa.esmbazi.ListViewsOb.Adapter
import ir.sssa.esmbazi.ListViewsOb.Condition
import ir.sssa.esmbazi.ListViewsOb.GMClass
import ir.sssa.esmbazi.R
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

    //TODO you must init  objects in your class
    lateinit var listView:ListView
    lateinit var   starText:TextView
    lateinit var timeText:TextView
    lateinit var sendText:EditText
    lateinit var sendIcon:ImageView
    lateinit var help:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

  abstract fun init()

    fun refreshDisplay(context: Context){
        adapter = Adapter(context, R.layout.item_of_list_game,messageList)
        listView.adapter =adapter
    }

    fun relationshipPlayWithAndroid(){
              (messageList as ArrayList).add(GMClass(getName(firstOfName), false, Condition.TRUE))
        sendText.hint = "اسم با  "+firstOfName
        starText.text =star.toString()
        sendIcon.setOnClickListener {
            if(!sendText.text.isEmpty()){
                if(sendText.text.toString().first()==firstOfName || firstOfName=='ا' && sendText.text.toString().first()=='آ') {
                    val n: Int = checkIsTrue(sendText.text.toString())
                    if (n == -1) {
                        (messageList as ArrayList).add(GMClass(sendText.text.toString(), true, Condition.FALSE))
                        toast("من این اسم رو نمی شناسم!")
                        adapter.notifyDataSetChanged()
                    } else if (n == 1) {
                        (messageList as ArrayList).add(GMClass(sendText.text.toString(), true, Condition.TRUE))
                        star++
                        starText.text = star.toString()
                        firstOfName = sendText.text.toString().last()
                        val myName: String = getName(firstOfName)
                        firstOfName=myName.last()
                        if (myName != "iLoser")
                            (messageList as ArrayList).add(GMClass(myName, false, Condition.TRUE))
                        adapter.notifyDataSetChanged()
                        sendText.hint = "اسم با  "+firstOfName
                    } else if (n == 0) {
                        (messageList as ArrayList).add(GMClass(sendText.text.toString(), true, Condition.FALSE))
                        adapter.notifyDataSetChanged()
                        toast("قبلا استفاده شده!!")
                    }
                }else{
                    (messageList as ArrayList).add(GMClass(sendText.text.toString(), true, Condition.FALSE))
                    adapter.notifyDataSetChanged()
                    toast("اسم با  "+firstOfName)
                }
                sendText.text= null
                listView.focusSearch(messageList.size-1)
            }

        }

        help.setOnClickListener {
            if(star>=2){
                star=star-2
                val myname:String =getName(firstOfName)
                (messageList as ArrayList).add(GMClass(myname, true, Condition.TRUE))
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


