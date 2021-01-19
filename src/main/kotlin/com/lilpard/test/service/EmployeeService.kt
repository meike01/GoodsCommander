package com.lilpard.test.service

import com.lilpard.test.DAO.EmployeeDao
import com.lilpard.test.entity.Employee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
internal class EmployeeService {
    @Autowired
    var employeeDao : EmployeeDao? = null

    var passwordEncoder = BCryptPasswordEncoder()

    fun CreateOrUpdateEmployee(employee: Employee): Boolean {
        employeeDao!!.save(employee)
        return true;
    }

    fun ConfirmInfo(name: String, password:String): Boolean{
        var employee = employeeDao!!.findByEphone(name)
        return passwordEncoder.matches(password,employee.passwordString)
    }

    fun DeleteEmployee(eid:String): Boolean{
        employeeDao!!.deleteById(eid)
        return true
    }

}
