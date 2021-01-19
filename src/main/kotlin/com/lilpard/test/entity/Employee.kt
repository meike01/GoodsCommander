package com.lilpard.test.entity

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "employees")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
class Employee {
    @Id @GeneratedValue(generator = "jpa-uuid")@Column(length = 32) var eid : String? = null

    var ename = ""

    //雇员职责代号，0为总管，1为货物管理员，2为销售管理员，3为收银员
    var ejob = 0

    //性别代号，1为男性，2为女性，3为其他性别
    var egender = 0

    var ephone = ""

    var salary : Float = 0f

    var eadress = ""

    var eidNumber = ""

    var passwordString = ""

}
