package com.ydl.residentmap.util;

import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUtil {
    /**
     * 设置excel的response
     * @param response
     * @param title
     * @return
     * @throws UnsupportedEncodingException
     */
    public static HttpServletResponse setExcelResponse(HttpServletResponse response,String title) throws UnsupportedEncodingException {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        //导出文件的标题
        String finalTitle = title+ dateFormat.format(date)+".xls";
        //防止中文乱码
        String headStr = "attachment; filename=\"" + new String( finalTitle.getBytes("gb2312"), "ISO8859-1" ) + "\"";
        response.setContentType("octets/stream");
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", headStr);
        return response;
    }

    /**
     * 设置excel
     * @param headers
     * @param dataList
     * @return
     */
    public static HSSFWorkbook setExcel(String[] headers,List<List<String>> dataList) {
        // 第一步，创建一个workbook，对应一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet hssfSheet = workbook.createSheet("sheet1");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow hssfRow = hssfSheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
        //居中样式
        //hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFCell hssfCell = null;
        for (int i = 0; i < headers.length; i++) {
            hssfCell = hssfRow.createCell(i);//列索引从0开始
            hssfCell.setCellValue(headers[i]);//列名1
            hssfCell.setCellStyle(hssfCellStyle);//列居中显示
        }
        // 创建单元格，并设置值
        for(int i=0;i<dataList.size();i++) {
            hssfRow = hssfSheet.createRow(i+1);
            List<String> list = dataList.get(i);
            for(int j=0;j<list.size();j++) {
                hssfRow.createCell(j).setCellValue(list.get(j));
            }
        }

        return workbook;
    }


    /**
     * 获取查询条件map
     * @param condition
     * @return
     */
    public static HashMap<String,String> getCondtionMap(String condition)
    {
        HashMap<String,String> map = new HashMap<String,String>();
        String[] keyVals = condition.split("\\|");
        if(keyVals.length>0)
        {
            for(int i=0;i<keyVals.length;i++)
            {
                String[] keyVal = keyVals[i].split(":");
                if(keyVal.length==2)
                {
                    String key = keyVal[0];
                    String val = keyVal[1];
                    if(map.containsKey(key)==false)
                    {
                        map.put(key,val);
                    }
                }
            }
        }
        return map;
    }
}
