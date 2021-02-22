package com.paparazzi.fastdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

/**
 * @ProjectName: springsession
 * @Package: com.paparazzi.fastdfs
 * @Description: java类作用描述
 * @Author: 张仪
 * @CreateDate: 2021/2/20 20:26
 * @Version: 1.0.0
 * <p>
 * Copyright: Copyright (c) 2021
 */
public class FastDFS {
    public static void main(String[] args) {
        //文件上传
        //fileUpload();

        //文件删除
        fileDelete("group1","M00/00/00/wKgSqGAxBZiAE_L-AAA8VLr-MEQ667.png");

    }
    //上传文件的方法
    public static void fileUpload(){
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        try {
            //1.加载配置文件，默认去classpath下加载
            ClientGlobal.init("fdfs_client.conf");
            //2.创建TrackerClient对象
            TrackerClient trackerClient = new TrackerClient();
            //3.创建TrackerServer对象
            trackerServer = trackerClient.getConnection();
            //4.创建StorageServler对象
            storageServer = trackerClient.getStoreStorage(trackerServer);
            //5.创建StorageClient对象，这个对象完成对文件的操作
            StorageClient storageClient = new StorageClient(trackerServer,storageServer);
            //6.上传文件  第一个参数：本地文件路径 第二个参数：上传文件的后缀 第三个参数：文件信息
            String [] uploadArray = storageClient.upload_file("D:/baidu.png","png",null);
            for (String str:uploadArray) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        } finally {
            if(storageServer != null){
                try {
                    storageServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(trackerServer != null){
                try {
                    trackerServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //删除文件的方法
    public static void fileDelete(String groupName,String remoteFileName){
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        try {
            //1.加载配置文件，默认去classpath下加载
            ClientGlobal.init("fdfs_client.conf");
            //2.创建TrackerClient对象
            TrackerClient trackerClient = new TrackerClient();
            //3.创建TrackerServer对象
            trackerServer = trackerClient.getConnection();
            //4.创建StorageServler对象
            storageServer = trackerClient.getStoreStorage(trackerServer);
            //5.创建StorageClient对象，这个对象完成对文件的操作
            StorageClient storageClient = new StorageClient(trackerServer,storageServer);
            //6.上传文件  第一个参数：本地文件路径 第二个参数：上传文件的后缀 第三个参数：文件信息
            //String [] uploadArray = storageClient.upload_file("D:/baidu.png","png",null);
            int count=storageClient.delete_file(groupName,remoteFileName);
            System.out.println("count = " + count);
            /*for (String str:uploadArray) {
                System.out.println(str);
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        } finally {
            if(storageServer != null){
                try {
                    storageServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(trackerServer != null){
                try {
                    trackerServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
