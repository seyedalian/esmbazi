package ir.sssa.esmbazi.view

import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.os.Bundle
import android.view.View
import ir.sssa.esmbazi.ListViewsOb.Condition
import ir.sssa.esmbazi.ListViewsOb.GMClass
import ir.sssa.esmbazi.R
import android.widget.Toast


class ConnectToBluetooth : BaseActivity() {


    override fun init() {
            listView =findViewById(R.id.playGround)
            starText =findViewById(R.id.starText)
            timeText =findViewById(R.id.timeText)
            sendText =findViewById(R.id.sendText)
            sendIcon =findViewById(R.id.sendIcon)
            timeIcon =findViewById(R.id.time)
            bluetoothTextView =findViewById(R.id.search_BLUETOOTH_Device)
            help =findViewById(R.id.help)

            sendText.visibility = View.GONE
            sendIcon.visibility = View.GONE
            help.visibility = View.GONE
            timeIcon.visibility = View.GONE
            timeText.visibility = View.GONE

            bluetoothTextView.visibility =View.VISIBLE



        }

    lateinit var  bluetoothAdapter: BluetoothAdapter
    lateinit var bluetoothDevice: BluetoothDevice
    var bluDevice :List<BluetoothDevice> = ArrayList()

    val STATE_LISTENING:Int =1
    val STATE_CONNECTING:Int =2
    val STATE_CONNECTED:Int =3
    val STATE_MESSAGE_RECEIVED:Int =5
    val STATE_CONNECTING_FAILED:Int =4
    val REQUEST_ENABLE_BLUETOOTH:Int =1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connect_to_bluetooth)

        init()
        refreshDisplay(this)
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        if (bluetoothAdapter == null) {
            AlertDialog.Builder(this)
                .setTitle("عدم سازگاری")
                .setMessage("دستگاه شما فاقد قطعه بلوتوث است")
                .setPositiveButton("خارج شدن",null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
            finish()
        }


    }






    fun on() {
        if (!bluetoothAdapter.isEnabled()) {
            val turnOn = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(turnOn, REQUEST_ENABLE_BLUETOOTH)
            Toast.makeText(applicationContext, R.string.blueOn, Toast.LENGTH_LONG).show()
        }
    }

    fun visible() {
        val getVisible = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
        startActivityForResult(getVisible, 0)
    }



    private fun implementListeners() {
       bluetoothTextView.setOnClickListener {
           if(messengerList.size>0){
               (messengerList as ArrayList).clear()
               adapter.notifyDataSetChanged()
               (bluDevice as ArrayList).clear()
           }
           adapter.notifyDataSetChanged()
         val pairedDevices : Set<BluetoothDevice> =bluetoothAdapter.bondedDevices
            for(device:BluetoothDevice  in pairedDevices){
                (messengerList as ArrayList).add(GMClass(device.name,false,Condition.NOONE,device.address))
                (bluDevice as ArrayList).add(device)
            }




           if ((bluDevice as ArrayList).isEmpty()){
               toast("چیزی پیدا نشد!")
           }


       }



    }








}
