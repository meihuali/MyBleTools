/*
package com.example.yukunlin.physiotherapydevice.utils;

import com.example.yukunlin.physiotherapydevice.module.Device;
import com.example.yukunlin.physiotherapydevice.module.History;

import java.sql.SQLException;
import java.util.List;

*/
/**
 * Created by yukunlin on 2016/11/14.
 *//*


public interface DeviceDao {
    */
/**
     * 插入一个用户
     *
     * @param device 需要插入的用户对象
     * @throws SQLException
     *//*

    void insert(Device device) throws SQLException;

    */
/**
     * 插入使用记录
     *
     * @param history
     * @throws SQLException
     *//*

    void insertHistory(History history) throws SQLException;

    */
/**
     * 获得所有的用户列表
     *
     * @return 用户列表
     * @throws SQLException
     *//*

    List<Device> getAllDevice() throws SQLException;

    */
/**
     * 获取历史记录
     *
     * @return 历史记录
     * @throws SQLException
     *//*

    List<History> getHistory(String macAddress) throws SQLException;

    */
/**
     * 更新一个用户
     *
     * @param device 需要更新的用户类
     * @return 更新后的对象
     * @throws SQLException
     *//*

    Device updateDevice(Device device) throws SQLException;

    */
/**
     * 根据姓名修改新姓名
     *
     * @param id
     * @param machineId
     * @throws SQLException
     *//*

    void updateMachineId(String id, String machineId) throws SQLException;


    void updateMachineName(String id,String name) throws SQLException;

    */
/**
     * 根据id删除用户
     *
     * @param id 用户主键
     * @throws SQLException
     *//*

    void deleteDevice(String id) throws SQLException;

    */
/**
     * 异步添加设备
     *
     * @param device 需要添加的设备对象
     * @throws SQLException
     *//*

    void insertDeviceAsync(Device device) throws SQLException;


    */
/**
     * 清楚所有
     *
     * @throws SQLException
     *//*

    void deleteAll() throws SQLException;

    String getMachineId(String id) throws SQLException;

    */
/**
     * 关闭事务
     *//*

    void closeRealm();
}
*/
