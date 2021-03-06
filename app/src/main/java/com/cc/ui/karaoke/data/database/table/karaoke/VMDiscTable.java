package com.cc.ui.karaoke.data.database.table.karaoke;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.cc.ui.karaoke.data.database.provider.DbProvider;
import com.cc.ui.karaoke.data.database.table.base.VMBaseTable;

/**
 * Project: Minion
 * Author: NT
 * Since: 6/18/2016.
 * Email: duynguyen.developer@yahoo.com
 */
public class VMDiscTable extends VMBaseTable {

    public static final String TABLE_NAME =  "ZDISC";

    public static final String QUERY_CREATE_TABLE =
            "CREATE TABLE `ZDISC` (\n" +
            "\t`ZROWID`\tINTEGER,\n" +
            "\t`ZSNAME`\tTEXT\n" +
            ");";

    public static final String ZROWID_COL = "ZROWID";
    public static final String ZSNAME_COL = "ZSNAME";

    private static VMDiscTable instance = null;

    public static VMDiscTable getInstance() {
        if (instance == null) {
            instance = new VMDiscTable();
        }

        return instance;
    }

    /**
     * create table
     *
     * @param db used to get database object for crate table
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(QUERY_CREATE_TABLE);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param database   used to get object write into table
     * @param oldVersion used get old version of database
     * @param newVersion used to get new version of tabase
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

    }

    /**
     * @param context       used to get Android system
     * @param helper        used to write to table
     * @param uri           used to get provider current in app
     * @param projection
     * @param selection     used to get where clause
     * @param selectionArgs used to set values into where clase
     * @param sortOrder     used to sort for result request    @return cursor query from table
     */
    @Override
    public Cursor query(Context context, SQLiteOpenHelper helper, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    /**
     * @param context       used to get Android system
     * @param helper        used to write to table
     * @param uri           used to get provider current in app
     * @param values        used to get value update to this row
     * @param selection     used to get where clause
     * @param selectionArgs used to set values into where clase
     * @return result update into table
     */
    @Override
    public int update(Context context, SQLiteOpenHelper helper, Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * @param context       used to get Android system
     * @param helper        used to write to table
     * @param uri           used to get provider current in app
     * @param selection     used to get where clause
     * @param selectionArgs used to set values into where clase
     * @return result delete row in to table
     */
    @Override
    public int delete(Context context, SQLiteOpenHelper helper, Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * insert into a row table
     *
     * @param context used to get Android system
     * @param helper  used to write to table
     * @param uri     used to get provider current in app
     * @param values  used to get values need to insert
     * @return result insert into table
     */
    @Override
    public Uri insert(Context context, SQLiteOpenHelper helper, Uri uri, ContentValues values) {
        return null;
    }

    /**
     * @param db     used to get object write into table
     * @param values used to get values for row
     * @return result insert into table
     */
    @Override
    public Uri insert(SQLiteDatabase db, ContentValues values) {
        return null;
    }

    /**
     * insert array a row into table
     *
     * @param context used to get Android system
     * @param helper  used to write to table
     * @param uri     used to get provider current in app
     * @param values  used to get values need to insert
     * @return result insert into table
     */
    @Override
    public int bunkInsert(Context context, SQLiteOpenHelper helper, Uri uri, ContentValues[] values) {
        return 0;
    }

    /**
     * get content uri for provider db in app
     *
     * @return
     */
    @Override
    public Uri getContentUri() {
        return Uri.withAppendedPath(DbProvider.CONTENT_URI, TABLE_NAME);
    }
}
