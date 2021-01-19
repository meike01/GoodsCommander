package com.lilpard.test.DAO

import com.lilpard.test.entity.GoodsInRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface GoodsInRepositoryDao : JpaRepository<GoodsInRepository, String> {

    fun findByGid(Gid:String): GoodsInRepository

    fun findAllByGnameLikeOrGkindLikeOrGbrandLike(Gname:String,Gkind:String,Gbrand:String): List<GoodsInRepository>

    @Query(value = "select DISTINCT g.gkind from GoodsInRepository g")
    fun findDistinctGkind() : List<String>

}
