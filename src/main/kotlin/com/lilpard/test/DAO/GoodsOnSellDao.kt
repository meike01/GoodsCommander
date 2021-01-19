package com.lilpard.test.DAO

import com.lilpard.test.entity.GoodsOnSell
import org.springframework.data.jpa.repository.JpaRepository

interface GoodsOnSellDao: JpaRepository<GoodsOnSell,String> {

    fun findByGbrandLikeOrGnameLikeOrGkindLike(Gbrand:String,Gname:String,Gkind:String) : List<GoodsOnSell>

    fun findByGcode(Gcode:Long) : GoodsOnSell

}
