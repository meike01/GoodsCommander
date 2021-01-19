package com.lilpard.test.DAO

import com.lilpard.test.entity.Employee
import com.lilpard.test.entity.GoodsOnSell
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeDao: JpaRepository<Employee, String> {

    fun findByEid(eid:String): Employee

    fun findByEphone(Ephone:String): Employee

    fun findByEjob(Ejob:Int): List<Employee>

}
