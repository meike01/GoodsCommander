package com.lilpard.test.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "onSell")
class GoodsOnSell {

    @Id
    @Column(length = 32)
    var gid: String? =null

    var gbrand : String = ""

    var gname: String = ""

    var gkind = ""

    var gamount = 0

    var gprice = 0f

    var gcode:Long? = 0


    @Column(updatable = false,length = 3)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    var creationTime : Date? = null

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(length = 3)
    var updateTime : Date? = null

    var creationUserId = ""

    var updateUserId = ""

    constructor(_gid: String){
        gid = _gid;
    }

    constructor(){

    }


}
