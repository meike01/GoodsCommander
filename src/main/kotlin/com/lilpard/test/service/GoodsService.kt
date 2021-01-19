package com.lilpard.test.service

import com.lilpard.test.DAO.*
import org.springframework.beans.factory.annotation.Autowired
import com.lilpard.test.DTO.GoodsOnSellDto
import com.lilpard.test.DTO.GoodsSellingDto
import com.lilpard.test.entity.*
import org.dozer.Mapper
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
internal class GoodsService {
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

    @Transactional @Override
    fun delete(gid: String) {

        goodsInRepositoryDao!!.deleteById(gid)
    }

    @Transactional @Override
    fun getBygId(gid: String) : GoodsInRepository {
        return goodsInRepositoryDao!!.findByGid(gid)
    }

    @Transactional
    fun getAll(): List<GoodsInRepository> {
        var sort = Sort.by(Sort.Direction.ASC,"gid")
        return goodsInRepositoryDao!!.findAll(sort)
    }

    @Transactional
    fun addOrUpdate(goodsInRepository: GoodsInRepository){
        if (goodsInRepository.gid==null){
            goodsInRepositoryDao!!.save(goodsInRepository)
            var repositoryLogItem =mapper!!.map(goodsInRepository,RepositoryLogs::class.java)
            repositoryLogsDao!!.save(repositoryLogItem)
            var list = listOf<RepositoryLogs>(repositoryLogItem)
        } else{
            goodsInRepository.gamount+=goodsInRepositoryDao!!.findById(goodsInRepository.gid!!).get().gamount
            goodsInRepositoryDao!!.save(goodsInRepository)
            var repositoryLogItem = mapper!!.map(goodsInRepository,RepositoryLogs::class.java)
        }

    }

    @Transactional
    fun searchGoods(key:String) : List<GoodsInRepository> {
        return goodsInRepositoryDao!!.findAllByGnameLikeOrGkindLikeOrGbrandLike(key,key,key);
    }

    fun getAllKind():List<String>{
//        val ssr = StandardServiceRegistryBuilder().configure().build()
//        val sessionFactory: SessionFactory = MetadataSources(ssr).buildMetadata().buildSessionFactory()
//
//        var session = sessionFactory.openSession()
//        var transaction = session.beginTransaction()
//
//        var query: Query<*> = session.createQuery("select DISTINCT goods.gkind from GoodsInRepository goods order by goods.gkind desc")

        return goodsInRepositoryDao!!.findDistinctGkind()

    }

}
