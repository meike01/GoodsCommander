package com.lilpard.test.entity

import org.hibernate.annotations.GenericGenerator
import java.text.SimpleDateFormat
import java.util.*
import javax.persistence.*

@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
class RepositoryLogs {
    var gid = ""
    var gname = ""
    var gamount = 0
    var gcost = 0f
    @Id
    @Column(length = 3)
    var creationDate : Date? = null

    constructor(){
        creationDate= Date()
    }
}
