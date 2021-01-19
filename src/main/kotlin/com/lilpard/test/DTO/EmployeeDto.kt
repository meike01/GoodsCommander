package com.lilpard.test.DTO

open class EmployeeDto {
    var gid = ""

    var ename = ""

    //雇员职责代号，0为总管，1为货物管理员，2为销售管理员，3为收银员
    var ejob = 0

    //性别代号，1为男性，2为女性，3为其他性别
    var egender = 0

    var ephone = ""

    var salary : Float = 0f
}

class EmployeeCreateDto : EmployeeDto(){

    var password = ""

}

class LoginDto {
    var loginName=""

    var password=""
}


