package com.lilpard.test.DAO

import com.lilpard.test.entity.RepositoryLogs
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RepositoryLogsDao : JpaRepository<RepositoryLogs,Date>{

}
