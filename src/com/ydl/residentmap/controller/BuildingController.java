package com.ydl.residentmap.controller;


import com.alibaba.fastjson.JSONObject;
import com.ydl.residentmap.constants.ResultCode;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.model.Building;
import com.ydl.residentmap.model.ResponseResult;
import com.ydl.residentmap.model.vo.BuildingVo;
import com.ydl.residentmap.service.BuildingService;
import com.ydl.residentmap.util.LatitudeUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "buildings")
public class BuildingController {
    private static Logger logger =Logger.getLogger(BuildingController.class);
    @Resource
    private BuildingService buildingService;

    /**
     * 增加楼栋
     * @param building
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/add", method = { RequestMethod.POST })
    public ResponseResult add(@RequestBody Building building) {
        logger.debug("添加楼栋：  名称：【" + building.getName() + "】");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = "";
        String error = "";
        String error_description = "";
        try {

            String address = building.getAddress().trim();
            //地址不为空，获取经纬度
            if(!"".equals(address)){
                desc = ResultMessage.SAVE_SUCCESS;
                Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
                if(lngLat!=null){
                    String lng = lngLat.get("lng");
                    String lat = lngLat.get("lat");
                    building.setLng(lng);
                    building.setLat(lat);
                }
                else{
                    building.setLng(null);
                    building.setLat(null);
                    desc += " 根据地址["+address+"]未查询到重点人员经纬度，经纬度设置为空。";
                }
            }

            buildingService.save(building);
            data = building.getId();
            desc = ResultMessage.SAVE_SUCCESS;
        }
        catch (Exception ex){
            status = ResultCode.ERROR;
            error_description = ResultMessage.SAVE_FAILURE;
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 删除楼栋
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deletelist/{ids}", method = { RequestMethod.DELETE })
    public ResponseResult deleteList(@PathVariable("ids") String ids) {
        logger.debug("批量删除楼栋：ids：【"+ids+"】");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.DELETE_SUCCESS;
        String error = "";
        String error_description = "";
        try {
            String [] idArr = ids.split("_");
            List<String> idList = new ArrayList<String>();
            for(int i=0;i<idArr.length;i++){
                String idStr = idArr[i].trim();
                if(!"".equals(idStr)){
                    String id = idArr[i];
                    idList.add(id);
                }
            }
            buildingService.deleteList(idList);
        } catch (Exception e) {
            e.printStackTrace();
            status = ResultCode.ERROR;
            desc = ResultMessage.DELETE_FAILURE;
            error = "";
            error_description = ResultMessage.DELETE_FAILURE;
            logger.debug("删除楼栋异常，异常信息为："+error_description);
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 更新楼栋
     *
     * @param building
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public ResponseResult update(@RequestBody Building building) {
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.UPDATE_SUCCESS;
        String error = "";
        String error_description = "";
        try {

            String address = building.getAddress().trim();
            //地址不为空，获取经纬度
            if(!"".equals(address)){
                desc = ResultMessage.SAVE_SUCCESS;
                Map<String,String> lngLat = LatitudeUtils.getGeocoderLatitude(address);
                if(lngLat!=null){
                    String lng = lngLat.get("lng");
                    String lat = lngLat.get("lat");
                    building.setLng(lng);
                    building.setLat(lat);
                }
                else{
                    building.setLng(null);
                    building.setLat(null);
                    desc += " 根据地址["+address+"]未查询到重点人员经纬度，经纬度设置为空。";
                }
            }

            buildingService.update(building);
        } catch (Exception e) {
            status = ResultCode.ERROR;
            desc = ResultMessage.UPDATE_FAILURE;
            error_description = ResultMessage.UPDATE_FAILURE;
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 获取所有楼栋
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getall", method = { RequestMethod.GET })
    public ResponseResult getAll() {
        logger.debug("获取所有楼栋");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        List<BuildingVo> buildings = buildingService.getAllBuildingVos();
        if(buildings.size()==0){
            status=ResultCode.ERROR;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, buildings, desc, error, error_description);
    }

    /**
     * 根据名称获取楼栋
     *
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getbyname/{name}", method = { RequestMethod.GET })
    public ResponseResult getByName(@PathVariable(value = "name") String name) {
        logger.debug("获取带名称为：【"+name+"】的楼栋");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        List<BuildingVo> buildings = buildingService.getBuildingVosByName(name);
        if(buildings.size()==0){
            status=ResultCode.ERROR;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, buildings, desc, error, error_description);
    }

    /**
     * 根据楼栋id获取楼栋
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getbyid/{id}", method = RequestMethod.GET)
    public ResponseResult getById(@PathVariable("id") Long id) {
        logger.debug("根据id获取楼栋，ID为：【"+id+"】");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        try {
            data = buildingService.getBuildingVoById(id);
        } catch (Exception e) {
            e.printStackTrace();
            status = ResultCode.ERROR;
            desc = ResultMessage.SEARCH_FAILURE;
            error_description = ResultMessage.UPDATE_FAILURE;
            logger.debug("获取楼栋信息异常，异常信息为：【"+error_description+"】");
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

}
