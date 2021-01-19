package com.lilpard.test.controller

import com.lilpard.test.DTO.GoodsInRepositoryDto
import com.lilpard.test.entity.GoodsInRepository
import com.lilpard.test.result.Result
import com.lilpard.test.result.ResultCode
import com.lilpard.test.service.GoodsService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.dozer.Mapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import com.tencentcloudapi.ocr.v20181119.models.QrcodeOCRResponse
import com.tencentcloudapi.ocr.v20181119.models.QrcodeOCRRequest
import com.tencentcloudapi.ocr.v20181119.OcrClient
import com.tencentcloudapi.common.profile.ClientProfile
import com.tencentcloudapi.common.profile.HttpProfile
import com.tencentcloudapi.common.Credential
import openj9.internal.tools.attach.target.IPC.getRandomString
import org.hibernate.bytecode.BytecodeLogger.LOGGER
import org.springframework.web.multipart.MultipartFile
import sun.misc.BASE64Encoder
import java.io.File
import java.io.FileInputStream
import java.io.IOException





@Api(tags= ["仓库货物管理"])
@RestController
@RequestMapping("/api/goods")
class RepositoryController {
    @Autowired
    private var goodsService : GoodsService?=null

    @Autowired
    private var mapper: Mapper?=null


    @ApiOperation("删除货物")
    @DeleteMapping("/del/{gid}")
    fun getGoodsById(@PathVariable gid : String):Result<Any?> {
        goodsService!!.delete(gid)
        return Result(200)
    }

    @ApiOperation("货物列表")
    @GetMapping("/all")
    @CrossOrigin fun getAll() : Result<Any?>{
        return Result(ResultCode.REQUEST_SUCCEED.code,goodsService!!.getAll())
    }

    @ApiOperation("添加/修改货物")
    @PostMapping("/add")
    fun add(@RequestBody goodsDto: GoodsInRepositoryDto):Result<Any?> {
        var goodsInRepository = mapper!!.map(goodsDto,GoodsInRepository::class.java)

        goodsService!!.addOrUpdate(goodsInRepository)
        return Result(200)
    }

    //模糊搜索时，字符串要被百分号包裹
    @ApiOperation("搜索仓库货物")
    @PostMapping("/all/{key}" ) fun searchResult(@PathVariable key: String?):Result<Any?> {
        return if (key=="")
            Result(ResultCode.REQUEST_SUCCEED.code,goodsService!!.getAll())
        else
            Result(ResultCode.REQUEST_SUCCEED.code,goodsService!!.searchGoods("%$key%"))
    }

    @ApiOperation("得到所有产品种类")
    @GetMapping("/getkinds") fun getKinds():Result<Any?>{
        return Result(ResultCode.REQUEST_SUCCEED.code,goodsService!!.getAllKind())
    }

    @ApiOperation("测试腾讯云接口")
    @GetMapping("/ocr") fun ocr(fileName:String) : Any? {
        var srcParent = System.getProperty("user.dir")
        var filePath = srcParent + "/src/main/resources/static/codes/"
        var file = filePath+fileName

        var inputStream = FileInputStream(file)
        var data =ByteArray(inputStream.available()+1)
        inputStream.read(data)
        inputStream.close()


        val cred = Credential("AKID5vVq7doteAQL00vH5e8LN8xazB6NfMTK", "ldFbhW4Voqb5zFIM9inb9b5QTIq8Bj2F")
        val httpProfile = HttpProfile()
        httpProfile.endpoint = "ocr.tencentcloudapi.com"
        val clientProfile = ClientProfile()
        clientProfile.httpProfile = httpProfile
        val client = OcrClient(cred, "ap-guangzhou", clientProfile)
        val req = QrcodeOCRRequest()
        req.imageBase64=BASE64Encoder().encode(data)
//        req.imageUrl =
//            "https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/exp/w=500/sign=a589f47f4510b912bfc1f6fef3fcfcb5/d01373f082025aafcc97cd0ff9edab64024f1a48.jpg"
        val resp = client.QrcodeOCR(req)
        println(QrcodeOCRResponse.toJsonString(resp))

        return resp.codeResults[0].url
//        try {
//
//        } catch (e: TencentCloudSDKException) {
//            println(e.toString())
//        }
//        return QrcodeOCRResponse()
    }

    @ApiOperation("上传图片")
    @PostMapping("/img") fun uploadImg(file: MultipartFile):Any?{
        if (file.isEmpty()) {
            return Result<Any?>(ResultCode.REQUEST_SUCCEED.code,"上传失败，请选择文件")
        }
        var srcParent = System.getProperty("user.dir")
        var fileName = file.getOriginalFilename()
        var filePath = srcParent + "/src/main/resources/static/codes/"
        var dest:File = File(filePath + fileName)
        return try {
            file.transferTo(dest)
            LOGGER.info("上传成功")
            var imgUrl="http://localhost:8022/file/codes/$fileName"
            Result(ResultCode.REQUEST_SUCCEED.code, ocr(fileName!!),"上传成功")
        } catch (e:IOException) {
            LOGGER.error(e.toString(), e)
            Result(ResultCode.REQUEST_SUCCEED.code,"上传失败！")
        }

    }





}
