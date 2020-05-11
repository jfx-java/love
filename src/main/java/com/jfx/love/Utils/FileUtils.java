package com.jfx.love.Utils;

import com.jfx.love.pojo.Volunteer;
import com.jfx.love.pojo.VolunteerExcel;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

public class FileUtils {


    /**
     * 将志愿者集合转成xls表格形式传给前端
     * @param list
     * @param fileName
     * @param resp
     * @throws IOException
     */
    public static  void volunteersToExcel(List<VolunteerExcel> list, String fileName, HttpServletResponse resp) {
        //忽略警告信息
        @SuppressWarnings("resource")
        HSSFWorkbook hssfWorkbook= new HSSFWorkbook();//new一个excel文件
        HSSFSheet sheet = hssfWorkbook.createSheet();//创建表
        HSSFCellStyle style = hssfWorkbook.createCellStyle();//创建样式风格
        style.setAlignment(HorizontalAlignment.CENTER);//为样式设置值，这里是居中
        HSSFRow row;//行
        HSSFCell cell;//小房间，牢房

        //第一行表列名
        row=sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue("志愿者用户名");
        cell = row.createCell(1);
        cell.setCellStyle(style);
        cell.setCellValue("联系方式");


        for (int i =1;i <= list.size();i++){

            //设置创建1行
            row=sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellStyle(style);
            VolunteerExcel volunteer = list.get(i-1);
            cell.setCellValue(volunteer.getSharerName());

            cell = row.createCell(1);
            cell.setCellStyle(style);;
            cell.setCellValue(volunteer.getSharerTelephone());
        }
           OutputStream out = null;
        try {
            out = resp.getOutputStream();
        resp.setContentType("multipart/form-data");
                resp.setHeader("Content-Disposition","attachment; filename="
                + URLEncoder.encode(fileName,"UTF-8"));
                hssfWorkbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    /**
     * 下载文件
     * @param filename
     * @param res
     * @throws IOException
     */
    public static void download(String filename, HttpServletResponse res) throws IOException {
        // 发送给客户端的数据
        OutputStream outputStream = res.getOutputStream();
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        // 读取filename
        bis = new BufferedInputStream(new FileInputStream(new File("./file/" + filename)));
        int i = bis.read(buff);
        while (i != -1) {
            outputStream.write(buff, 0, buff.length);
            outputStream.flush();
            i = bis.read(buff);
        }
    }


    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");

        return uuid;
    }

    public static String uploadPicture(MultipartFile img, String filePath) {

        //判断文件夹是否存在，不存在则创建
        File file = new File(filePath);

        //获取上传文件名
        String originalFilename = img.getOriginalFilename();
        if (!file.exists()) {
            file.mkdirs();
        }
        String uuid = FileUtils.getUUID();
        String newFilePath = filePath + uuid + originalFilename;

        try {
            img.transferTo(new File(newFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uuid + originalFilename;
    }


}
