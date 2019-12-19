package com.iflysse.util;

/**
 * @Author Crab
 * 2019-11-03 23:55
 **/

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;


public class upload2 {
    /**
     * 上传图片
     *  
     *
     * @param request
     * @param response
     * @param url2     文件的存放路径
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings({"static-access", "unchecked"})
    public static Map<String, Object> doGet(HttpServletRequest request, HttpServletResponse response, String url2)
            throws ServletException, IOException {


        Map<String, Object> paramMap = new HashMap<String, Object>();
        StringBuilder image = new StringBuilder();
        StringBuilder details_image = new StringBuilder();


        try {
            // 1、创建工厂类：DiskFileItemFactory
            DiskFileItemFactory facotry = new DiskFileItemFactory();


            // 2、创建核心解析类：ServletFileUpload
            ServletFileUpload upload = new ServletFileUpload(facotry);
            upload.setHeaderEncoding("UTF-8");// 解决上传的文件名乱码


            // 3、判断用户的表单提交方式是不是multipart/form-data
            boolean bb = upload.isMultipartContent(request);
            if (!bb) {
                return null;
            }
            // 4、是：解析request对象的正文内容List<FileItem>
            List<FileItem> items = upload.parseRequest(request);
            String storePath = request.getSession().getServletContext().getRealPath("/upload/pjimages");// 上传的文件的存放目录


            for (FileItem item : items) {
                if (item.isFormField()) {
                    // 5、判断是否是普通表单：打印看看
                    String fieldName = item.getFieldName();// 请求参数名
                    String fieldValue = item.getString("UTF-8");// 请求参数值
                    // System.out.println(fieldName + "=" + fieldValue);


                    if (fieldName.equals("old") && !fieldValue.equals("")) {
                        details_image.append(fieldValue);
                        details_image.append(",");
                    }
                    paramMap.put(fieldName, fieldValue);
                } else {
                    // 6、上传表单：得到输入流，处理上传：保存到服务器的某个目录中，保存时的文件名是啥？
                    String fileName = item.getName();// 得到上传文件的名称 C:\Documents
                    // and
                    // Settings\shc\桌面\a.txt
                    // a.txt
                    // 解决用户没有选择文件上传的情况
                    if (fileName == null || fileName.trim().equals("")) {
                        continue;
                    }


                    fileName = fileName.substring(fileName.lastIndexOf("/") + 1);


                    String newFileName = fileName;
                    InputStream in = item.getInputStream();


                    String name = new Date().getTime() + newFileName;
                    String savePath = storePath + "/" + name;
                    String url = url2 + "/" + name;
                    // System.out.println(item.getFieldName()+"上传的文件地址："+savePath);
                    if (item.getFieldName().equals("image")) {
                        image.append(url);
                        image.append(",");
                    }
                    if (item.getFieldName().equals("details_image")) {
                        details_image.append(url);
                        details_image.append(",");
                    } else {
                        paramMap.put(item.getFieldName(), url);
                    }


                    OutputStream out = new FileOutputStream(savePath);
                    byte b[] = new byte[1024];
                    int len = 0;
                    while ((len = in.read(b)) != -1) {
                        out.write(b, 0, len);
                    }


                    in.close();
                    out.close();
                    item.delete();// 删除临时文件
                }
            }
            if (image != null && image.length() != 0 && !"".equals(image.toString())) {
                String images = image.toString().substring(0, image.toString().length() - 1);


                paramMap.put("image", images);
            }


            if (details_image != null && details_image.length() != 0 && !"".equals(details_image.toString())) {
                String details_images = details_image.toString().substring(0, details_image.toString().length() - 1);


                paramMap.put("details_image", details_images);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "上传失败");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
        return paramMap;
    }
}
