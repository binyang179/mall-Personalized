package com.iflysse.controller;

import Decoder.BASE64Decoder;
import com.iflysse.service.*;
import com.iflysse.util.PythonUtil;
import com.iflysse.viewmodel.CategoryViewModel.ParentCategory;
import com.iflysse.viewmodel.EvaluateViewModel.EvaluateList;
import com.iflysse.viewmodel.GoodsViewModel.Goods4List;
import com.iflysse.viewmodel.PreferViewModel.PreferList;
import com.iflysse.viewmodel.ResultMessageView.ResultMessage;
import com.iflysse.viewmodel.UserViewModel.UserList;
import org.apache.ibatis.mapping.ResultMap;
import org.bridj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
//import sun.text.resources.FormatData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.Document;
import java.io.*;
import java.util.List;
import java.util.UUID;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PreferService preferService;

    @ResponseBody
    @RequestMapping("/user/login")
    public Boolean Login(@RequestBody UserList arr, HttpSession httpSession) {
        List<UserList> users = userService.getUserInfoByUser(arr.getUserName(), arr.getPassword());
        if (users == null || users.size() == 0) {
            return false;
        } else {
            httpSession.setAttribute("userId", users.get(0).getUserId());
            httpSession.setAttribute("userName", users.get(0).getUserName());
            return true;
        }
    }


    @RequestMapping("/user/logout")
    public boolean execute(HttpSession session) {
        session.invalidate();
        return true;
    }

    @RequestMapping("/user/checkLogin")
    @ResponseBody
    public boolean IsLogin(HttpSession httpSession) {
        if (httpSession.getAttribute("userId") != null) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping("/user/check/{data}")
    @ResponseBody
    public boolean Check(@PathVariable("data") String data) {
        List<UserList> users = userService.getUserInfoByUserName(data);
        if (users == null || users.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    @ResponseBody
    @RequestMapping("/user/register")
    public Boolean Register(@RequestBody UserList arr) {
        userService.addUser(arr.getUserName(), arr.getPassword());
        return true;
    }

    @ResponseBody
    @RequestMapping("/user/checkPrefer")
    public boolean CheckPrefer(HttpSession httpSession) {
        int userId = Integer.parseInt(httpSession.getAttribute("userId").toString());
        List<PreferList> prefers = preferService.getByUserId(userId);
        if (prefers == null || prefers.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    @ResponseBody
    @RequestMapping("/user/addPrefer")
    public void AddPrefer(@RequestBody List<PreferList> arrList, HttpSession httpSession) {
        int userId = Integer.parseInt(httpSession.getAttribute("userId").toString());
        for (PreferList arr : arrList) {
            preferService.insertInfo(userId, arr.getCategoryId());
        }
        String command = "/home/crab179/IdeaProjects/iflytek/Personalized/python/personal_lbr.py";
        PythonUtil.executePython(userId, command);
    }

//    @ResponseBody
//    @RequestMapping("/user/facelogin")
//    public int  faceLogin(@RequestBody UserList arr, HttpSession httpSession) {
//        System.out.println(arr.getUserName());
//        System.out.println(arr.getLoginPic());
////        List<UserList> users=userService.getUserInfoByUser(arr.getUserName(),arr.getPassword());
//        new TestWebCam(arr.getLoginPic(),arr.getUserName());
//        return 0;
////        if(users==null || users.size()==0){
////            return 0;
////        } else{
////            httpSession.setAttribute("userList", users.get(0));
////            return users.get(0).getUserId();
////        }
//    }


//    @RequestMapping(params = "method=addCircle")
//    public String addCircle(HttpServletResponse response,HttpServletRequest request) throws IOException {
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");
//
//
//        String path = request.getSession().getServletContext().getRealPath(
//                "/BackstageShoppingWebsite/images/addCircleimage");//保存的服务器地址
//
//
//        Map<String, String> map = upload1.upload(request, 1024 * 1024 * 10, path);
//
//        String file= map.get("file"); // 名称
//        String image = map.get("type"); // 图像
//        String newFile = map.get("newFile");// 地址
//
//        return null;
//    }
//

//    @RequestMapping(params = "method=addCircle")
//    public String addCircle(HttpServletResponse response,HttpServletRequest request) throws IOException {
//        String urls = Var.webConfigMap.get("WXLinkUrl")+"/upload/pjimages";
//
//        Map<String, Object> paramMap= upload2.doGet(request,response,urls);
//        String file= (String)map.get("file"); // 名称
//        String image = (String)map.get("type"); // 图像
//        String newFile = (String)map.get("newFile");// 地址
//        return null;
//    }


    /**
     * 功能描述：拍照并上传图片
     */
    @RequestMapping(value = "/user/RegUploadPhoto", method = RequestMethod.POST)
    public boolean uploadPhoto(HttpServletRequest httpServletRequest) throws IOException {
        System.out.println("fsadfasdf");

        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) httpServletRequest;
        String registerName = multipartHttpServletRequest.getParameter("regsiterPicName");
        System.out.println("registerName is " + registerName);

//        Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
//        List<MultipartFile> registerPicFile = (multipartHttpServletRequest).getFiles("registerPic");

        MultipartFile multipartFileForRegisterPic = multipartHttpServletRequest.getFile("registerPic");

        String EnvPath = "/home/crab179/IdeaProjects/iflytek/Personalized/";

//        List<MultipartFile> registerPicfiles = multipartHttpServletRequest.getFiles("registerPic");
//        MultipartFile multipartFileTest = multipartHttpServletRequest.getFile("registerPic");
//        System.out.println(multipartFileTest.getName());
//
        System.out.println(multipartFileForRegisterPic.isEmpty());
        System.out.println(multipartFileForRegisterPic.getOriginalFilename());
        System.out.println(multipartFileForRegisterPic.getContentType());

        String registerImgPath = EnvPath + "registerPic/" + registerName + ".png";
        System.out.println(registerImgPath);
        //        默认传入的参数带类型等参数：data:
//        image / png;
//        base64,
//        if (null != imgStr) {
//            imgStr = imgStr.substring(imgStr.indexOf(",") + 1);
//        }
//        Boolean flag = GenerateImage(imgStr, registerImgPath, registerImgName);
//        System.out.println(flag);
//        if (flag) {
//            result = filePath + fileName;
//        }
        if (!multipartFileForRegisterPic.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(registerImgPath)));
                System.out.println(multipartFileForRegisterPic.getName());
                out.write(multipartFileForRegisterPic.getBytes());
                out.flush();
                out.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;

//        InputStream inputStream = multipartFileForRegisterPic.getInputStream();
//        byte[] b = new byte[1048576];
//        int length = inputStream.read(b);
//        FileOutputStream fileOutputStream = new FileOutputStream(registerImgPath);
//        fileOutputStream.write(b, 0, length);
//        inputStream.close();
//        fileOutputStream.close();


//        BufferedOutputStream bufferedOutputStream = null;
//        if (!multipartFileForRegisterPic.isEmpty()) {
//            System.out.println("begin receive");
//                try {
//                    byte[] bytes = multipartFileForRegisterPic.getBytes();
//                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(
//                            new File(multipartFileForRegisterPic.getOriginalFilename())));
//                    bufferedOutputStream.write(bytes);
//                    bufferedOutputStream.close();
//                } catch (Exception e) {
//                    bufferedOutputStream = null;
////                    return "You failed to upload " + i + " => "
////                            + e.getMessage();
//                }
//            } else {
////                return "You failed to upload " + i
////                        + " because the file was empty.";
//            }

//        for (int i = 0; i < registerPicfiles.size(); ++i) {
//            multipartFile = registerPicfiles.get(i);
//            if (!multipartFile.isEmpty()) {
//                try {
//                    byte[] bytes = multipartFile.getBytes();
//                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(
//                            new File(multipartFile.getOriginalFilename())));
//                    bufferedOutputStream.write(bytes);
//                    bufferedOutputStream.close();
//                } catch (Exception e) {
//                    bufferedOutputStream = null;
//                }
//            } else {
//                System.out.println("what's wrong");
//            }

//        File file = new File(filePath, fileName);
//        try {
//            registerPic.transferTo(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
//
//    /**
//     * 功能描述：base64字符串转换成图片
//     *
//     * @since 2016/5/24
//     */
//    public boolean GenerateImage(String imgStr, String filePath, String fileName) {
//        try {
//            if (imgStr == null) {
//                return false;
//            }
//            BASE64Decoder decoder = new BASE64Decoder();
//            //Base64解码
//            byte[] b = decoder.decodeBuffer(imgStr);
//            //如果目录不存在，则创建
//            File file = new File(filePath);
//            if (!file.exists()) {
//                file.mkdirs();
//            }
//            //生成图片
//            OutputStream out = new FileOutputStream(filePath + fileName);
//            out.write(b);
//            out.flush();
//            out.close();
//            return true;
//        } catch (Exception e) {
//            System.out.println("error");
//            return false;
//        }
//    }
}
