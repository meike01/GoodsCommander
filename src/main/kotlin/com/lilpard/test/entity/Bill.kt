package com.lilpard.test.entity

import java.util.*
import kotlin.random.Random

class Bill {
    var billNumber = Random(Date().time).nextLong((999999999-100000000)+1)+1000000000

    var list : List<GoodsOnSell>? = null

    var cost =0f

    var billDate = Date()


}
