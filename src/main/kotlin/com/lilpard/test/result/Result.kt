package com.lilpard.test.result

open class Result<T> {
    var code:Int = 0
    constructor(_code: Int){
        code=_code
    }

    constructor(_code: Int, _data: T){
        code=_code
        data=_data
    }

    constructor(_code: Int, _data: T , mes:String){
        code=_code
        data=_data
        message=mes
    }

    constructor(_code: Int, mes:String){
        code=_code
        message=mes
    }



    var data: T? = null

    var message = ""
}
