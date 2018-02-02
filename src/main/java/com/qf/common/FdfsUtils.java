package com.qf.common;

import org.csource.fastdfs.*;

/**
 *  @author TianYu
 *  @date 2018/2/2 0002 14:56
 *  @description
 */
public class FdfsUtils {

    //1.创建跟踪服务器的客户端
    private TrackerClient trackerClient;
    //2.连接跟踪服务器
    private TrackerServer trackerServer;
    //3.声明存储服务器
    private StorageServer storageServer;
    //4.创建存储客户端
    private StorageClient1 storageClient;
    private String conf;

}
