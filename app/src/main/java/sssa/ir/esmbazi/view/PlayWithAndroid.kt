package ir.sssa.esmbazi.view

import android.os.Bundle
import ir.sssa.esmbazi.ListViewsOb.Condition
import ir.sssa.esmbazi.ListViewsOb.GMClass
import ir.sssa.esmbazi.R


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
        relationship(this)

        (messengerList as ArrayList).add(GMClass(getName(firstOfName), false, Condition.TRUE))
        sendText.hint = "اسم با  "+firstOfName
        sendIcon.setOnClickListener {
            if(!sendText.text.isEmpty()){
                //check first of name is = firstOfName
                if(sendText.text.toString().first()==firstOfName || firstOfName=='ا' && sendText.text.toString().first()=='آ') {
                    //check Condition of check name n==-1 main is not in dataBase
                    // n==1 main name is true and not in spent  and n==0 name is in spent

                    val n: Int = checkIsTrue(sendText.text.toString())
                    if (n == -1) {
                        (messengerList as ArrayList).add(GMClass(sendText.text.toString(), true, Condition.FALSE))
                        toast("من این اسم رو نمی شناسم!")
                        adapter.notifyDataSetChanged()
                    } else if (n == 1) {
                        //name is true so write in listView
                        (messengerList as ArrayList).add(GMClass(sendText.text.toString(), true, Condition.TRUE))
                        //increase start number
                        star++
                        //write start  on  InternalStorage for future
                        startToFile(this)
                        //write in startText
                        starText.text = star.toString()
                        //change firstOfName last of true name
                        firstOfName = sendText.text.toString().last()
                        val myName: String = getName(firstOfName)
                        firstOfName=myName.last()
                        if (myName != "iLoser")
                            (messengerList as ArrayList).add(GMClass(myName, false, Condition.TRUE))
                        else {
                            toast("من باختم!")
                            finish()
                        }
                        adapter.notifyDataSetChanged()
                        sendText.hint = "اسم با  "+firstOfName
                    } else if (n == 0) {
                        (messengerList as ArrayList).add(GMClass(sendText.text.toString(), true, Condition.FALSE))
                        adapter.notifyDataSetChanged()
                        toast("قبلا استفاده شده!!")
                    }
                }else{
                    (messengerList as ArrayList).add(GMClass(sendText.text.toString(), true, Condition.FALSE))
                    adapter.notifyDataSetChanged()
                    toast("اسم با  "+firstOfName)
                }
                sendText.setText("")
                listView.setSelection(messengerList.lastIndex)
            }

        }
    }


}
