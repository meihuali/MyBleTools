/*
package com.example.yukunlin.physiotherapydevice.utils;

import android.content.Context;

import java.io.File;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;
import io.realm.exceptions.RealmMigrationNeededException;

*/
/**
 * Created by yukunlin on 2016/11/14.
 *//*


public class RealmUtils {
    private Context context;
    private static RealmUtils mInstance;
    private String realName = "device.realm";

    private RealmUtils(Context context) {
        this.context = context;
    }

    public static RealmUtils getInstance(Context context) {
        if (mInstance == null) {
            synchronized (RealmUtils.class) {
                if (mInstance == null) {
                    mInstance = new RealmUtils(context);
                }
            }
        }
        return mInstance;
    }

    */
/**
     * 获得Realm对象
     *
     * @return
     *//*

    public Realm getRealm() {

//        Realm realm = null;
//        realm = Realm.getInstance(
//                new RealmConfiguration.Builder(context)
//                        .name(realName)
//                        .schemaVersion(1)
//                        .migration(new RealmMigration() {
//                            @Override
//                            public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
//                                RealmSchema schema = realm.getSchema();
//                                if (guide_0 == oldVersion) {
//                                    schema.create("History")
//                                            .addField("gear", String.class);
//                                   oldVersion++;
//                                }
//                            }
//                        })
//                        .build());
//
//        return realm;
//        RealmConfiguration config0 = new RealmConfiguration.Builder(context)
//                .name(realName).schemaVersion(3).migration(new Migration()).build();

        return Realm.getInstance(new RealmConfiguration.Builder(context).name(realName).build());
    }
}
*/
