package ir.sssa.esmbazi.ListViewsOb

class GMClass(text:String,who:Boolean,isTrue:Condition){
    //who ==true main person and who == false main partner
    var  isTrue:Condition = isTrue
    val text:String =text
    val who:Boolean =who // true is player false = partner of player
    lateinit var address: String
    constructor(text:String, who:Boolean, isTrue:Condition, address:String) : this(text,who,isTrue) {
        this.address =address
    }
    // false and noone is no player and no partner
}
enum class Condition {
    TRUE,FALSE,UNKNOWN ,NOONE
}