package com.qf.fdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;

import java.io.IOException;

public class Fdfs_Main {

    public static void main(String[] args) throws IOException, MyException {

        //1.初始化配置文件
        ClientGlobal.init("src/main/resources/fdfs_client.conf");

        //2.跟踪服务器的客户端

    }
}
