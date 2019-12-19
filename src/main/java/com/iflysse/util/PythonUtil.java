package com.iflysse.util;

import java.io.DataInputStream;
import java.io.InputStream;

public class PythonUtil {

    public static void executePython(Integer userID, String command){
        try {
            // 进行一个python的调用
            String exe = "python";
            // String command = "/home/crab179/IdeaProjects/iflytek/Personalized/python/personal_recommendation_func.py";
            String userId = String.valueOf(userID);
            String[] cmdArr = new String[]{exe, command, userId};
            Process process = Runtime.getRuntime().exec(cmdArr);
            InputStream is = process.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            String str = dis.readLine();
            process.waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Integer userId = 12;
        String command = "/home/crab179/IdeaProjects/iflytek/Personalized/python/personal_lbr.py";
        executePython(userId, command);
    }
}
