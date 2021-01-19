package com.lilpard.test.controller

import com.lilpard.test.DTO.EmployeeCreateDto
import com.lilpard.test.DTO.LoginDto
import com.lilpard.test.entity.Employee
import com.lilpard.test.result.Result
import com.lilpard.test.result.ResultCode
import com.lilpard.test.service.EmployeeService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.dozer.Mapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*


@Api(tags= ["雇员管理"])
@RestController
@Import(BCryptPasswordEncoder::class)
@RequestMapping("/api/employee")
class EmployeeController {
    @Autowired
    private var employeeService: EmployeeService? =null

    private var passwordEncoder= BCryptPasswordEncoder()

    @Autowired
    private var mapper : Mapper? =null

    @ApiOperation("员工注册或信息修改")
    @PostMapping("/update")
    fun CreateOrUpdateInfo(@RequestBody create: EmployeeCreateDto): Result<Any?> {

        var employee = mapper!!.map(create,Employee::class.javaObjectType)
        employee.passwordString=passwordEncoder!!.encode(create.password)
        employeeService!!.CreateOrUpdateEmployee(employee)
        return Result(200)

    }

    @ApiOperation("员工登录")
    @PostMapping("/login")
    fun VerifyInfo(@RequestBody employee: LoginDto) : Result<Any?>{
        if (employeeService!!.ConfirmInfo(employee.loginName,employee.password))
            return Result(ResultCode.REQUEST_SUCCEED.code,"登录成功！")
        else
            return Result(ResultCode.REQUEST_FAILED.code,"登录失败！")
    }

    @ApiOperation("员工信息删除")
    @DeleteMapping("/del/{eid)")
    fun DeleteInfo(@PathVariable eid:String): Result<Any?> {
        employeeService!!.DeleteEmployee(eid)
        return Result(200);
    }

    @ApiOperation("查询员工信息")
    @PostMapping("/all")
    fun GetInfo(@RequestBody ejob:Int): Result<Any?> {
        if (ejob==0)
            return Result(ResultCode.REQUEST_SUCCEED.code,
                employeeService!!.employeeDao!!.findAll())
        else
            return Result(ResultCode.REQUEST_SUCCEED.code,
            employeeService!!.employeeDao!!.findByEjob(ejob))
    }


}
