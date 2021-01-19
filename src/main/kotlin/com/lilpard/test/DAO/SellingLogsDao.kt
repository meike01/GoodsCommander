package com.lilpard.test.DAO

import com.lilpard.test.entity.SellingLogs
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SellingLogsDao:JpaRepository<SellingLogs,Date> {
}
