package com.lilpard.test.DAO

import com.lilpard.test.entity.GoodsSold
import org.springframework.data.jpa.repository.JpaRepository

interface GoodsSoldDao : JpaRepository<GoodsSold,String> {

}
