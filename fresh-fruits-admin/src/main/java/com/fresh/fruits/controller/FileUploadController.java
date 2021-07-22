package com.fresh.fruits.controller;

import com.fresh.fruits.utils.FastDFSClient;
import com.fresh.fruits.utils.ResultCode;
import com.fresh.fruits.utils.ResultCommon;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @BelongsProject: fresh-fruits
 * @BelongsPackage: com.fresh.fruits.controller
 * @CreateTime: 2021-06-16 16:39
 * @Description: TODO
 */
@RestController
@RequestMapping("file")
public class FileUploadController {

    //文件要上传的地址
    public static final String FILE_SERVER_URL = "http://172.81.235.217:8080/";

    /**
     * 文件上传控制器
     * @return
     */
    @RequestMapping("upload")
    public ResultCommon file_upload(@RequestParam("file") MultipartFile file) {
        //1.获取上传的文件名
        String originalFilename = file.getOriginalFilename();
        System.out.println("上传的原始文件名是:" + originalFilename);
        //2.获取上传的文件的后缀名
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        System.out.println("文件的扩展名是:" + extName);
        try {
            //获取工具类,配置文件
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fdfs_client.conf");
            //上传文件,返回上传成功的地址
            // /group1/M00/00/00/rBEAD16CveSAC_wmAAA7XBQ3J9g389.png
            String url = fastDFSClient.uploadFile(file.getBytes(), extName);
            //还需要拼接一下全地址
            String fullPath = FILE_SERVER_URL + url;
            System.out.println("文件上传成功之后的全地址是:" + fullPath);
            return ResultCommon.success(ResultCode.SUCCESS, fullPath);
        } catch (Exception e) {
            return ResultCommon.fail(ResultCode.FAIL);
        }
    }
}
