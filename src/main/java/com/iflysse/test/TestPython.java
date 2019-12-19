package com.iflysse.test;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestPython {
    public static void main(String[] args) throws InterruptedException, IOException {
        String exe = "python";
        String command = "/home/crab179/IdeaProjects/iflytek/Personalized/python/personal_recommendation_func.py";
        String userId = "11";
        String[] cmdArr = new String[] {exe, command, userId};
        Process process = Runtime.getRuntime().exec(cmdArr);
        InputStream is = process.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        String str = dis.readLine();
        process.waitFor();
        System.out.println(str);
    }
}
