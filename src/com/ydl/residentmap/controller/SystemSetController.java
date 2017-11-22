package com.ydl.residentmap.controller;

import com.ydl.residentmap.constants.ResultCode;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.model.ResponseResult;
import com.ydl.residentmap.service.SystemSetService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "system_set")
public class SystemSetController {
    private static Logger logger =Logger.getLogger(KeyPersonController.class);

    @Resource
    private SystemSetService systemSetService;

    /**
     * 获取所有重点人员
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/setlnglat", method = { RequestMethod.GET })
    public ResponseResult setLngLat() {
        logger.debug("同步经纬度");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        List<String> emptyLngLat = systemSetService.setLngLat();
        if(emptyLngLat.size()>0)
        {
            status = ResultCode.ERROR;
        }
        return ResponseResult.create(status, emptyLngLat, desc, error, error_description);
    }
}
