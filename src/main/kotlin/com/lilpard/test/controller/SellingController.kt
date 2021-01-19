package com.lilpard.test.controller

import com.lilpard.test.DTO.GoodsOnSellDto
import com.lilpard.test.result.Result
import com.lilpard.test.result.ResultCode
import com.lilpard.test.service.SellingService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@Api(tags= ["销售货物管理"])
@RestController
@RequestMapping("/api/sell")
class SellingController {
    @Autowired
    private var sellingService: SellingService? =null

    @ApiOperation("将仓库货物搬运到货架")
    @PostMapping("/move") fun add(@RequestBody goods: GoodsOnSellDto) : Result<Any?> {
        var f = sellingService!!.moveToSell(goods);
        if (f)
            return Result(ResultCode.REQUEST_SUCCEED.code)
        else
            return Result(ResultCode.REQUEST_SUCCEED.code,"仓库货物不足！请检查搬运货物的数量！")
    }

    @ApiOperation("查询所有的货架货物")
    @GetMapping("/all") fun getAll() : Result<Any?>{
        return Result(ResultCode.REQUEST_SUCCEED.code,sellingService!!.getAll())
    }

    @ApiOperation("搜索货架货物")
    @PostMapping("/search") fun searchResult(@RequestBody key:String?) : Result<Any?>{
        return if (key==null)
            Result(ResultCode.REQUEST_SUCCEED.code,sellingService!!.searchGoods("%$key%"))
        else
            Result(ResultCode.REQUEST_SUCCEED.code,sellingService!!.getAll())
    }

}
