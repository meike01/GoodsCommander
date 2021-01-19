package com.lilpard.test.DTO

import org.hibernate.annotations.GenericGenerator
import org.hibernate.id.GUIDGenerator
import org.springframework.context.annotation.Primary
import javax.persistence.*



@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
open class GoodsDto {

    var gid : String? = null
    var gbrand = ""
    var gname : String = ""
    var gkind : String = ""
    var gamount = 0


    fun eToString(): String {
        var s : String = "{ gName = " +
                this.gname+", gKind = " +
                this.gkind+", gAmount = " +
                this.gamount
//        +", gValue = " +
//                this.gvalue+ ",gPrice = "+
//                this.gprice+" }"
        print(s)
        return s
    }
}
class GoodsInRepositoryDto : GoodsDto() {

    var gcode:Long? = 0


    var gvalue = 0f
}

class GoodsOnSellDto : GoodsDto(){

    var gcode:Long? = 0


    var gprice = 0f
}

class GoodsSellingDto {
    var gid = ""

    var gname = ""

    var gprice = 0f

    var gamount = 0

}
