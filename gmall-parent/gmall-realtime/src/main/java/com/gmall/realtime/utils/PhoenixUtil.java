package com.gmall.realtime.utils;

import com.alibaba.fastjson.JSONObject;
import com.gmall.realtime.common.GmallConfig;
import org.apache.commons.beanutils.BeanUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Author: Felix
 * Date: 2021/2/5
 * Desc: 从Phoenix中查询数据
 */
public class PhoenixUtil {
    private static Connection conn = null;

    public static void init(){
        try {
            //注册驱动
            Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
            //获取Phoenix的连接
            Properties properties = new Properties();
//            properties.put("phoenix.schema.isNamespaceMappingEnabled","true");
//            properties.put("phoenix.schema.mapSystemTablesToNamespace","true");
            conn = DriverManager.getConnection(GmallConfig.PHOENIX_SERVER,properties);
            //指定操作的表空间
            conn.setSchema(GmallConfig.HABSE_SCHEMA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 从Phoenix中查询数据
    // select * from 表 where XXX=xxx
    public static <T> List<T> queryList(String sql,Class<T> clazz){
        if(conn == null){
            init();
        }
        List<T> resultList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //获取数据库操作对象
            ps = conn.prepareStatement(sql);
            //执行SQL语句
            rs = ps.executeQuery();
            //通过结果集对象获取元数据信息
            ResultSetMetaData metaData = rs.getMetaData();
            //处理结果集
            while (rs.next()){
                //声明一个对象，用于封装查询的一条结果集
                T rowData = clazz.newInstance();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    BeanUtils.setProperty(rowData,metaData.getColumnName(i),rs.getObject(i));
                }
                resultList.add(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("从维度表中查询数据失败");
        } finally {
            //释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return resultList;
    }

    public static void main(String[] args) throws SQLException {
//        System.out.println(queryList("SELECT TABLE_NAME FROM SYSTEM.CATALOG  WHERE TABLE_SCHEM = 'GMALL2023_REALTIME' AND  TABLE_TYPE = 'u'", JSONObject.class));
        System.out.println(queryList("select * from base_trademark", JSONObject.class));
        // 1.添加链接
        /*String url = "jdbc:phoenix:192.168.3.67:2181";
        // 2.创建配置
        Properties properties = new Properties();
        // 3.添加配置
        // 需要客户端服务端参数保存一致
        properties.put("phoenix.schema.isNamespaceMappingEnabled", "true");
        // 4.获取连接
        Connection connection = DriverManager.getConnection(url, properties);
        connection.setSchema(GmallConfig.HABSE_SCHEMA);
        // 5.编译SQL语句
        PreparedStatement preparedStatement = connection.prepareStatement("create schema GMALL2023_REALTIME");
        // 6.执行语句
        Boolean resultSet = preparedStatement.execute();
        // 7.输出结果
        *//*while (resultSet.next()){
            System.out.println(resultSet.getString(1) + ":" + resultSet.getString(2) + ":" + resultSet.getString(3));
        }*//*
        System.out.println(resultSet);
        // 8.关闭资源
        connection.close();*/
    }

}
