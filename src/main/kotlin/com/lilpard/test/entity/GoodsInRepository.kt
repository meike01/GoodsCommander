package com.lilpard.test.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "repository")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
class GoodsInRepository {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    var gid: String? =null

    var gbrand = ""

    var gname: String = ""

    var gkind = ""

    var gamount = 0

    var gvalue = 0f

    var gcode:Long? = 0

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    var creationTime : Date? = null

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    var updateTime :Date? = null

    var creationUserId = ""

    var updateUserId = ""

    constructor(_gid: String){
        gid = _gid;
    }

    constructor(){

    }


}
