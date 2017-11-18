package com.ydl.residentmap.controller;

import com.alibaba.fastjson.JSONObject;
import com.ydl.residentmap.constants.ResultCode;
import com.ydl.residentmap.constants.ResultMessage;
import com.ydl.residentmap.model.Block;
import com.ydl.residentmap.model.Pager;
import com.ydl.residentmap.model.ResponseResult;
import com.ydl.residentmap.model.vo.BlockVo;
import com.ydl.residentmap.service.BlockService;
import com.ydl.residentmap.util.LatitudeUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "blocks")
public class BlockController {
    private static Logger logger =Logger.getLogger(BlockController.class);
    @Resource
    private BlockService blockService;

    /**
     * 增加小区
     * @param block
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/add", method = { RequestMethod.POST })
    public ResponseResult add(@RequestBody Block block) {
        logger.debug("添加小区：  姓名：【" + block.getName() + "】");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = "";
        String error = "";
        String error_description = "";
        try {
            blockService.save(block);
            data = block.getId();
            desc = ResultMessage.SAVE_SUCCESS;
        }
        catch (Exception ex){
            status = ResultCode.ERROR;
            desc=ResultMessage.SAVE_FAILURE;
            error_description = ex.getMessage();
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 删除重点人员
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deletelist/{ids}", method = { RequestMethod.DELETE })
    public ResponseResult deleteList(@PathVariable("ids") String ids) {
        logger.debug("批量删除小区：ids：【"+ids+"】");
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
            blockService.deleteList(idList);
        } catch (Exception e) {
            e.printStackTrace();
            status = ResultCode.ERROR;
            desc = ResultMessage.DELETE_FAILURE;
            error = "";
            error_description = e.getMessage();
            logger.debug("删除小区异常，异常信息为："+error_description);
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 更新小区
     *
     * @param block
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public ResponseResult update(@RequestBody Block block) {
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.UPDATE_SUCCESS;
        String error = "";
        String error_description = "";
        try {
            blockService.update(block);
        } catch (Exception e) {
            status = ResultCode.ERROR;
            desc = ResultMessage.UPDATE_FAILURE;
            error_description = e.getMessage();
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 获取所有小区
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getall", method = { RequestMethod.GET })
    public ResponseResult getAll() {
        logger.debug("获取所有小区");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        List<BlockVo> blocks = blockService.getAllBlockVos();
        if(blocks.size()==0){
            status=ResultCode.ERROR;
            desc=ResultMessage.SEARCH_FAILURE;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, blocks, desc, error, error_description);
    }

    /**
     * 根据小区名称获取小区
     *
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getbyname/{name}", method = { RequestMethod.GET })
    public ResponseResult getByName(@PathVariable(value = "name") String name) {
        logger.debug("获取带名字为：【"+name+"】的小区");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        List<BlockVo> blocks = blockService.getBlockVosByName(name);
        if(blocks.size()==0){
            status=ResultCode.ERROR;
            desc=ResultMessage.SEARCH_FAILURE;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, blocks, desc, error, error_description);
    }

    /**
     * 根据id获取小区
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getbyid/{id}", method = RequestMethod.GET)
    public ResponseResult getById(@PathVariable("id") Long id) {
        logger.debug("根据id获取小区，ID为：【"+id+"】");
        String status = ResultCode.SUCCESS;
        Object data = new JSONObject();
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";
        try {
            data = blockService.getBlockVoById(id);
        } catch (Exception e) {
            e.printStackTrace();
            status = ResultCode.ERROR;
            desc = ResultMessage.SEARCH_FAILURE;
            error_description = ResultMessage.UPDATE_FAILURE;
            logger.debug("获取小区信息异常，异常信息为：【"+error_description+"】");
        }
        return ResponseResult.create(status, data, desc, error, error_description);
    }

    /**
     * 分页获取小区
     *
     * @param pageinfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getbypage/{pageinfo}", method = { RequestMethod.GET })
    public ResponseResult getByPage(@PathVariable(value = "pageinfo") String pageinfo) {
        logger.debug("分页获取小区");
        String status = ResultCode.SUCCESS;
        String desc = ResultMessage.SEARCH_SUCCESS;
        String error = "";
        String error_description = "";

        String [] pageArr = pageinfo.split("_");
        int offset=Integer.parseInt(pageArr[0]);
        int size=Integer.parseInt(pageArr[1]);
        Pager<Block> blocks = blockService.getBlocksByPage(offset,size);
        if(blocks.getTotal()==0){
            status=ResultCode.ERROR;
            desc=ResultMessage.SEARCH_FAILURE;
        }
        else{
            status=ResultCode.SUCCESS;
        }
        return ResponseResult.create(status, blocks, desc, error, error_description);
    }

}
