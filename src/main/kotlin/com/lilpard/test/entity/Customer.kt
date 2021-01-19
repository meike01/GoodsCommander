package com.lilpard.test.entity

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "customers")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
class Customer {
    @Id @GeneratedValue(generator = "jpa-uuid") @Column(length = 32) var cid :String?=null

    var cname =  ""

    var cgender = 0

    var cphone = ""
}
