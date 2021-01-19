package com.lilpard.test.service

import com.lilpard.test.DAO.*
import com.lilpard.test.DTO.GoodsOnSellDto
import com.lilpard.test.DTO.GoodsSellingDto
import com.lilpard.test.entity.*
import org.dozer.Mapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
internal class SellingService {


    @Autowired
    private var goodsInRepositoryDao: GoodsInRepositoryDao? = null

    @Autowired
    private var goodsOnSellDao: GoodsOnSellDao? = null

    @Autowired
    private var repositoryLogsDao: RepositoryLogsDao? = null

    @Autowired
    private var goodsSoldDao: GoodsSoldDao?=null

    @Autowired
    private var sellingLogsDao: SellingLogsDao?= null

    @Autowired
    private var mapper : Mapper? =null

    @Transactional
    fun getAll(): List<GoodsOnSell>{
        return goodsOnSellDao!!.findAll()
    }

    @Transactional
    fun searchGoods(key :String):List<GoodsOnSell>{
        return goodsOnSellDao!!.findByGbrandLikeOrGnameLikeOrGkindLike(key,key,key)
    }

    @Transactional
    fun moveToSell(goods: GoodsOnSellDto) :Boolean {
        //仓库货物处理
        var goodsInRepository = goodsInRepositoryDao!!.getOne(goods.gid!!)
        if (goodsInRepository.gamount<goods.gamount)
            return false
        else
            goodsInRepository.gamount -= goods.gamount
        goodsInRepositoryDao!!.save(goodsInRepository)

        //货架货物处理
        var goodsOnSell : GoodsOnSell?=null
        var optional = goodsOnSellDao!!.findById(goods.gid!!)
        if(optional.isPresent){
            goodsOnSell=optional.get()
            goodsOnSell.gamount += goods.gamount
        }
        else
            goodsOnSell= mapper!!.map(goods, GoodsOnSell::class.java)
        goodsOnSellDao!!.save(goodsOnSell!!)

        //仓库日志信息处理
        var repositoryLogsItem = mapper!!.map(goodsInRepository, RepositoryLogs::class.java)
        repositoryLogsItem.gamount = -goods.gamount
        repositoryLogsDao!!.save(repositoryLogsItem)

        return true
    }

    @Transactional
    fun findWithCode(code:Long): GoodsOnSellDto {
        var goodsOnSell = goodsOnSellDao!!.findByGcode(code)
        var goodsOnSellDto = mapper!!.map(goodsOnSell,GoodsOnSellDto::class.java)
        return goodsOnSellDto
    }

    @Transactional
    fun sellGoods(list: List<GoodsSellingDto>): Bill {
        var bill = Bill()
        var billList = mutableListOf<GoodsOnSell>()
        list.forEach {
            //货架货物处理
            var goods = goodsOnSellDao!!.findById(it.gid).get()
            goods.gamount -= it.gamount
            goodsOnSellDao!!.save(goods)

            //销售总表处理
            var goodsSold = mapper!!.map(goods, GoodsSold::class.java)
            var origin = goodsSoldDao!!.findById(it.gid)
            if (origin.isPresent){
                goodsSold.gamount = origin.get().gamount+it.gamount
            } else {
                goodsSold.gamount=it.gamount
            }
            goodsSoldDao!!.save(goodsSold)

            //财务日志信息处理
            var sellingLogItem = mapper!!.map(goods, SellingLogs::class.java)
            sellingLogItem.profit= it.gamount * (sellingLogItem.gprice - goodsInRepositoryDao!!.getOne(it.gid).gvalue)
            sellingLogsDao!!.save(sellingLogItem)

            //生成订单
            goods.gamount = it.gamount
            billList.add(goods)
            bill.cost+=it.gamount*it.gprice
        }
        return bill
    }
}
