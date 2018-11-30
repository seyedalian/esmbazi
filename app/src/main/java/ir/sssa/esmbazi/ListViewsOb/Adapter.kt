package ir.sssa.esmbazi.ListViewsOb

import android.annotation.SuppressLint
import android.content.Context
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
        val retView: View
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
        val processR: ProgressBar =convertView.findViewById(R.id.progress_check_nameR)
        val falseImageR:ImageView =convertView.findViewById(R.id.falseImageR)
        val trueImageR:ImageView =convertView.findViewById(R.id.trueImageR)

        @SuppressLint("ResourceAsColor")
        fun fill(item:GMClass){
            if(item.who){
                textItem.setBackgroundResource(R.drawable.message_shap_player)
                if(item.isTrue == Condition.TRUE) {
                    processR.visibility = View.GONE
                    trueImageR.visibility = View.VISIBLE
                    falseImageR.visibility =View.GONE

                    process.visibility =View.GONE
                    trueImage.visibility =View.GONE
                    falseImage.visibility =View.GONE
                }
                if(item.isTrue ==Condition.FALSE){
                    processR.visibility = View.GONE
                    falseImageR.visibility =View.VISIBLE
                    trueImageR.visibility =View.GONE

                    process.visibility =View.GONE
                    trueImage.visibility =View.GONE
                    falseImage.visibility =View.GONE
                }
                if(item.isTrue ==Condition.UNKNOWN){
                    processR.visibility = View.VISIBLE
                    falseImageR.visibility =View.GONE
                    trueImageR.visibility =View.GONE

                    process.visibility =View.GONE
                    trueImage.visibility =View.GONE
                    falseImage.visibility =View.GONE
                }

            }else{
                textItem.setBackgroundResource(R.drawable.message_shap_partner)

                if(item.isTrue == Condition.TRUE) {
                    process.visibility = View.GONE
                    trueImage.visibility = View.VISIBLE
                    falseImage.visibility =View.GONE

                    processR.visibility =View.GONE
                    trueImageR.visibility =View.GONE
                    falseImageR.visibility =View.GONE
                }
                if(item.isTrue ==Condition.FALSE){
                    process.visibility = View.GONE
                    falseImage.visibility =View.VISIBLE
                    trueImage.visibility =View.GONE

                    processR.visibility =View.GONE
                    trueImageR.visibility =View.GONE
                    falseImageR.visibility =View.GONE
                }
                if(item.isTrue ==Condition.UNKNOWN){
                    process.visibility = View.VISIBLE
                    falseImage.visibility =View.GONE
                    trueImage.visibility =View.GONE

                    processR.visibility =View.GONE
                    trueImageR.visibility =View.GONE
                    falseImageR.visibility =View.GONE
                }


            }


            textItem.text = item.text



        }
    }
}