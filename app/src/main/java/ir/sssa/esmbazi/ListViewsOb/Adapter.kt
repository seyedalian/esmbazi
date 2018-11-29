package ir.sssa.esmbazi.ListViewsOb

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import ir.sssa.esmbazi.R

class Adapter(context: Context,resource: Int,items:List<GMClass>):
        ArrayAdapter<GMClass>(context,resource,items){
        val layoutInflater: LayoutInflater= getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val items:List<GMClass> =items
        val resource:Int =resource



    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val item =items[position]
        val viewHolder:ViewHolder
        var retView: View
        if(convertView==null) {
            retView = layoutInflater.inflate(resource,null)
            viewHolder = ViewHolder(retView)
            retView.tag =viewHolder
        }else{
            viewHolder= convertView.tag as ViewHolder
            retView =convertView
        }
        viewHolder.fill(item)


        return retView
    }




    class ViewHolder(convertView: View){
        val process: ProgressBar =convertView.findViewById(R.id.progress_check_name)
        val falseImage:ImageView =convertView.findViewById(R.id.falseImage)
        val trueImage:ImageView =convertView.findViewById(R.id.trueImage)
        val textItem:TextView =convertView.findViewById(R.id.textItem)


        @SuppressLint("ResourceAsColor")
        fun fill(item:GMClass){
            if(item.isTrue == Condition.TRUE) {
                process.visibility = View.GONE
                trueImage.visibility = View.VISIBLE
                falseImage.visibility =View.GONE
            }
            if(item.isTrue ==Condition.FALSE){
                process.visibility = View.GONE
                falseImage.visibility =View.VISIBLE
                trueImage.visibility =View.GONE
            }
            if(item.isTrue ==Condition.UNKNOWN){
                process.visibility = View.VISIBLE
                falseImage.visibility =View.GONE
                trueImage.visibility =View.GONE
            }


            textItem.text = item.text

            if(item.who){
                textItem.setBackgroundColor(R.color.green)
            }else{
                textItem.setBackgroundColor(R.color.yellow)
            }

        }
    }
}