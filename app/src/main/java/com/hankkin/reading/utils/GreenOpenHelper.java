package com.hankkin.reading.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hankkin.reading.greendao.DaoMaster;

public class GreenOpenHelper extends DaoMaster.DevOpenHelper {

    public GreenOpenHelper(Context context, String name) {
        super(context, name);
    }

    public GreenOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            new GreenUpgradeHelper().upgrade(db, this);
        }
    }
}


