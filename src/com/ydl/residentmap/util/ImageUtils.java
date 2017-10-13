package com.ydl.residentmap.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.List;
import java.util.UUID;


/**
 * 处理图片帮助类
 * Created by 小强 on 2017/7/20.
 */
public class ImageUtils {

    /**
     * 解析图片，将图片保存到指定路径
     * @param folderPath 图片文件夹目录
     * @param imgList 图片信息base64编码
     * @return 保存的图片路径+图片名称
     */
    public static List<String> saveImg(String folderPath,List<String> imgList) throws Exception{
        //创建文件
        createFolder(folderPath);

        for(int i=0;i<imgList.size();i++){
            //图片BASE64编码
            String imgStr = imgList.get(i);
            BASE64Decoder decoder = new BASE64Decoder();
            decoder.decodeBuffer(imgStr);

        }

        return null;
    }


    /**
     * 将图片BASE64编码
     * @param folderPath
     * @param img
     * @return
     * @throws Exception
     */
    public static String decodeBase64ToImg(String folderPath,String img) throws Exception{
        String rootPath = System.getProperty("kechuangMap.webapp");
        //创建文件
        createFolder(rootPath+folderPath);
        BASE64Decoder decoder = new BASE64Decoder();
        //去掉前缀，如 data:image/jpg;base64,
        String cutStr = ";base64,";
        int beginIndex = img.indexOf(cutStr)+8;
        if(img.contains(cutStr)) {
            img = img.substring(beginIndex,img.length());
        }
        byte[] decoderBytes =decoder.decodeBuffer(img);
        //生成文件名
        String fileName = UUID.randomUUID().toString();
        //FileOutputStream write = new FileOutputStream(new File("e://222.jpg"));
        String imgPath = folderPath +"/"+ fileName+".jpg";
        FileOutputStream write = new FileOutputStream(new File(rootPath+imgPath));
        write.write(decoderBytes);
        write.flush();
        write.close();

        return imgPath;
    }

    public static String encodeImgToBase64(String filePath){
        InputStream inputStream = null;
        byte[] data = null;
        //判断文件是否存在
        File file =new File(filePath);
        if(!file.exists()) {
            return "";
        }
        try {

            inputStream = new FileInputStream(filePath);
            //inputStream = new FileInputStream("e:\\2.jpg");
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        String img = encoder.encode(data);
        img = "data:image/jpg;base64," +img;
        return img;
    }


    /**
     * 判断文件夹是否存在，如果不存在，创建文件夹
     * @param folderPath 文件夹路径
     */
    public static void createFolder(String folderPath){
        File file = new File(folderPath);
        if(!file.exists()){
            //文件夹不存在，创建文件
            file.mkdirs();
        }
    }

    public static void main(String[] args){

        try{
            //createFolder("C:\\java\\apache-tomcat-7.0.79\\webapps\\KechuangMap\\images\\aidResident\\header\\");
            //图片BASE64编码
            //String img = encodeImgToBase64("D:\\tmp\\aa\\head.jpg");
            //System.out.println(img);
            //BASE64 解码为图片
            //decodeBase64ToImg("d://tmp//aa",img);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
