package com.ydl.residentmap.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydl.residentmap.constants.ResultCode;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.model.Community;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.ResponseResult;
import com.ydl.residentmap.model.vo.BlockVo;
import com.ydl.residentmap.model.vo.CommunityVo;
import com.ydl.residentmap.service.CommunityService;
import com.ydl.residentmap.util.LatitudeUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "communities")
public class CommunityController {
    private static Logger logger =Logger.getLogger(CommunityController.class);
    @Resource
    private CommunityService communityService;

    /**
     * 增加社区
     * @param community
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/add", method = { RequestMethod.POST })
    public ResponseResult add(@RequestBody Community community) {
        logger.debug("添加社区：  名称：【" + community.getName() + "】");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = "";
        String error = "";
        String error_description = "";
        try {
            communityService.save(community);
            data = community.getId();
            desc = ResultMessage.SAVE_SUCCESS;
        }
        catch (Exception ex){
            status = ResultCode.ERROR;
            desc =  ResultMessage.SAVE_FAILURE;
            error_description = ex.getMessage();
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 删除社区
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deletelist/{ids}", method = { RequestMethod.DELETE })
    public ResponseResult deleteList(@PathVariable("ids") String ids) {
        logger.debug("批量删除社区：ids：【"+ids+"】");
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
            communityService.deleteList(idList);
        } catch (Exception e) {
            e.printStackTrace();
            status = ResultCode.ERROR;
            desc = ResultMessage.DELETE_FAILURE;
            error = "";
            error_description = e.getMessage();
            logger.debug("删除社区异常，异常信息为："+error_description);
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 更新社区
     *
     * @param community
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public ResponseResult update(@RequestBody Community community) {
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.UPDATE_SUCCESS;
        String error = "";
        String error_description = "";
        try {
            communityService.update(community);
        } catch (Exception e) {
            status = ResultCode.ERROR;
            desc = ResultMessage.UPDATE_FAILURE;
            error_description = e.getMessage();
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 获取所有社区
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getall", method = { RequestMethod.GET })
    public ResponseResult getAll() {
        logger.debug("获取所有社区");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        List<CommunityVo> communities = communityService.getAllCommunityVos();
        if(communities.size()==0){
            status=ResultCode.ERROR;
            desc=ResultMessage.SEARCH_FAILURE;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, communities, desc, error, error_description);
    }

    /**
     * 分页获取社区
     *
     * @param pageinfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getbypage/{pageinfo}", method = { RequestMethod.GET })
    public ResponseResult getByPage(@PathVariable(value = "pageinfo") String pageinfo) {
        logger.debug("分页获取社区");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";

        String [] pageArr = pageinfo.split("_");
        int offset=Integer.parseInt(pageArr[0]);
        int size=Integer.parseInt(pageArr[1]);
        Pager<Community> communities = communityService.getCommunitiesByPage(offset,size);
        if(communities.getTotal()==0){
            status=ResultCode.ERROR;
            desc=ResultMessage.SEARCH_FAILURE;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, communities, desc, error, error_description);
    }

    /**
     * 根据社区名称获取社区
     *
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getbyname/{name}", method = { RequestMethod.GET })
    public ResponseResult getByName(@PathVariable(value = "name") String name) {
        logger.debug("获取带名字为：【"+name+"】的社区");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        List<CommunityVo> communities = communityService.getCommunityVosByName(name);
        if(communities.size()==0){
            status=ResultCode.ERROR;
            desc=ResultMessage.SEARCH_FAILURE;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, communities, desc, error, error_description);
    }

    /**
     * 根据id获取社区
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getbyid/{id}", method = RequestMethod.GET)
    public ResponseResult getById(@PathVariable("id") Long id) {
        logger.debug("根据id获取社区，ID为：【"+id+"】");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        try {
            data = communityService.getCommunityVoById(id);
        } catch (Exception e) {
            e.printStackTrace();
            status = ResultCode.ERROR;
            desc = ResultMessage.SEARCH_FAILURE;
            error_description = ResultMessage.SEARCH_FAILURE;
            logger.debug("获取社区信息异常，异常信息为：【"+error_description+"】");
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 根据社区id获取社区下的小区
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getblocksbyid/{id}", method = RequestMethod.GET)
    public ResponseResult getBlocksById(@PathVariable("id") Long id) {
        logger.debug("根据id获取社区下的小区，社区ID为：【"+id+"】");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";

        List<BlockVo> blocks = communityService.getBlockVosByCommunityId(id);
        if(blocks.size()==0){
            status=ResultCode.ERROR;
            desc=ResultMessage.SEARCH_FAILURE;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, blocks, desc, error, error_description);
    }

}
