package com.lilpard.test.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "sellingLogs")
class SellingLogs {
    var gid = ""
    var gname = ""
    var gamount = 0
    var gprice = 0f
    var profit = 0f

    @Id
    @Column(length = 3)
    var createTime: Date = Date()
}
