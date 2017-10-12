/*
package com.example.yukunlin.physiotherapydevice.utils;

import android.content.Context;

import com.example.yukunlin.physiotherapydevice.module.Device;
import com.example.yukunlin.physiotherapydevice.module.History;

import java.sql.SQLException;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

*/
/**
 * Created by yukunlin on 2016/11/14.
 *//*


public class DeviceDaoImpl implements DeviceDao {
    private Context context;
    private Realm mRealm;

    public DeviceDaoImpl(Context context) {
        this.context = context;
        mRealm = RealmUtils.getInstance(context).getRealm();
    }

    @Override
    public void insert(Device device) throws SQLException {
        mRealm.beginTransaction();//必须先开启事务
        Device device1 = mRealm.copyToRealm(device);//把Device对象复制到Realm
        mRealm.commitTransaction();//提交事务
//        mRealm.close();//必须关闭，不然会造成内存泄漏
    }

    @Override
    public void insertHistory(History history) throws SQLException {
        mRealm.beginTransaction();
        mRealm.copyToRealm(history);
        mRealm.commitTransaction();
    }

    @Override
    public List<Device> getAllDevice() throws SQLException {
        List<Device> list = null;
        RealmResults<Device> results = mRealm.where(Device.class).findAll();
        list = results;
//        mRealm.close();
        return list;
    }

    @Override
    public List<History> getHistory(String macAddress) throws SQLException {
        RealmResults<History> results = mRealm.where(History.class).equalTo("macAddress", macAddress).findAll();
        List<History> list = null;
        list = results;
        return list;
    }

    @Override
    public Device updateDevice(Device device) throws SQLException {
        mRealm.beginTransaction();//开启事务
        Device device1 = mRealm.copyToRealmOrUpdate(device);
        mRealm.commitTransaction();//提交事务
//        mRealm.close();//必须关闭事务
        return device1;
    }

    @Override
    public void updateMachineId(String id, String machineId) throws SQLException {
        mRealm.beginTransaction();//开启事务
        mRealm.where(Device.class)
                .equalTo("id", id)//根据id查询
                .findFirst()
                .setMachineId(machineId);//修改machineId
        mRealm.commitTransaction();
//        mRealm.close();
    }

    @Override
    public void updateMachineName(String id, String name) throws SQLException {
        mRealm.beginTransaction();//开启事务
        mRealm.where(Device.class)
                .equalTo("id", id)//根据id查询
                .findFirst()
                .setName(name);//修改machineId
        mRealm.commitTransaction();
    }

    @Override
    public String getMachineId(String id) throws SQLException {
        mRealm.beginTransaction();
        String machineId = mRealm.where(Device.class)
                .equalTo("id", id)
                .findFirst()
                .getMachineId();
        mRealm.commitTransaction();
        return machineId;
    }

    @Override
    public void deleteDevice(String id) throws SQLException {
        Device device = mRealm.where(Device.class).equalTo("id", id).findFirst();//删除id列值为id的行
        mRealm.beginTransaction();
        device.deleteFromRealm();//从数据库删除
        mRealm.commitTransaction();
//        mRealm.close();

    }

    @Override
    public void insertDeviceAsync(final Device device) throws SQLException {
        //一个Realm只能在同一个线程访问，在子线程中进行数据库操作必须重新获取realm对象
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.beginTransaction();//开启事务
                Device device1 = realm.copyToRealm(device);
                realm.commitTransaction();
                realm.close();//记得关闭事务
            }
        });
//        mRealm.close();//外面也不能忘记关闭事务
    }

    @Override
    public void deleteAll() throws SQLException {
        mRealm.beginTransaction();
        mRealm.where(Device.class).findAll().deleteAllFromRealm();
        mRealm.commitTransaction();
//        mRealm.close();
    }


    @Override
    public void closeRealm() {
        mRealm.close();
    }
}
*/
