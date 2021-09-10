package com.deng.currency.controller;

import com.deng.commons.config.result.ResultVOUtils;
import com.deng.commons.config.result.ResultVo;
import com.deng.commons.enums.ResultEnum;
import com.deng.commons.utils.StringUtil;
import com.deng.currency.utils.UploadFileUtil;
import com.deng.currency.utils.UploadFileUtilTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Api(tags = "文件管理")
@Controller
@RequestMapping("/uploadFile")
public class UploadFileController {
    protected static final Log logger = LogFactory.getLog(UploadFileController.class);

    @ApiOperation(value="文件上传", notes="文件上传")
    @PostMapping(value = "uploadpro")
    @ResponseBody
    public ResultVo uploadpro(HttpServletRequest request,
                              String getfile) {
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = null;
        try {
            file = multiRequest.getFile("file");
            if (file == null && !StringUtil.hasNullOrEmpty(getfile)) {
                file = multiRequest.getFile(getfile);
            }
            if (file == null) {
                file = multiRequest.getFile("file_mutil");
                if (file == null) {
                    throw new Exception();
                }
            }
        } catch (Exception e1) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
        String name = file.getOriginalFilename();
        String extName = name.substring(name.lastIndexOf("."));
        // 格式支持
        int filesupport = -1;
        if (".BMP".equalsIgnoreCase(extName) || ".PNG".equalsIgnoreCase(extName) || ".JPEG".equalsIgnoreCase(extName)
                || ".JPG".equalsIgnoreCase(extName) || ".MP4".equalsIgnoreCase(extName)
                || ".3GP".equalsIgnoreCase(extName) || ".GIF".equalsIgnoreCase(extName)
                || ".PDF".equalsIgnoreCase(extName)
        ) {
            filesupport = 1;
        }
        if (filesupport != 1) {
            return ResultVOUtils.error(ResultEnum.UNSUPPORT_FILEFORMAT_CODE);
        }
        if (!file.isEmpty()) {
            try {
                Map<String, Object> map = UploadFileUtil.upload(file);
                if (map == null) {
                    throw new Exception("返回结果map为null");
                }
                String path = map.get("imgName").toString().replaceAll("\\\\", "/");
                return ResultVOUtils.success(path);
            } catch (Exception e1) {
                logger.info("上传文件处理报错：" + e1.getMessage());
                return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
            }
        }
        return ResultVOUtils.error(ResultEnum.ERR);
    }

    /**
     *
     * Description:上传视频
     * @param filemark
     * @param format
     * @param request
     * @param getfile
     * @return
     * @throws Exception
     * @author yws
     * @date 2018年12月14日
     */
    @ApiOperation(value="视频上传", notes="视频上传")
    @PostMapping(value = "uploadVideo")
    @ResponseBody
    public ResultVo uploadVideo(String filemark, String format,
                                HttpServletRequest request, String getfile) throws Exception {
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = null;
        //获得输入文件
        try {
            file = multiRequest.getFile("file");
            if(file==null&&!StringUtil.hasNullOrEmpty(getfile)){
                file = multiRequest.getFile(getfile);
            }
            if(file==null){
                throw new Exception();
            }
        } catch (Exception e1) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
        //获得文件名和扩展名
        /*String name = file.getOriginalFilename();
        String extName = name.substring(name.lastIndexOf("."), name.length());*/
        String prjName = request.getSession().getServletContext().getContextPath();		// 项目名称
        String baseRealPath = request.getSession().getServletContext().getRealPath("/");// 当前web应用的绝对路径 :
        if (!file.isEmpty()) {
            try {
                Map<String, Object> map = UploadFileUtilTool.upload(file, prjName, baseRealPath, format);//上传视频文件并且返回截图
                if(map==null){
                    throw new Exception("返回结果map为null");
                }
                return ResultVOUtils.success(map);
            } catch (Exception e1) {
                logger.info("上传文件处理报错："+e1.getMessage());
                return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
            }
        }
        return ResultVOUtils.error(ResultEnum.ERR);
    }

}
