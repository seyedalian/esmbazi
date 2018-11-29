package ir.sssa.esmbazi.ListViewsOb

class GMClass(text:String,who:Boolean){
    //who ==true main person and who == false main partner
    var  isTrue:Condition = Condition.UNKNOWN
    val text:String =text
    val who:Boolean =who
}
enum class Condition {
    TRUE,FALSE,UNKNOWN
}