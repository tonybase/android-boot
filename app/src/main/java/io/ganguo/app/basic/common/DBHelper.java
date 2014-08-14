package io.ganguo.app.basic.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 建立一个数据库帮助类
 */
public class DBHelper extends SQLiteOpenHelper {

    private final static String dbName = "app.db";
    private final static int dbVersion = 1;

    private static DBHelper dbHelper;

    public static DBHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DBHelper(context);
        }
        return dbHelper;
    }

    /**
     * 据库名
     *
     * @param context
     */
    private DBHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    /**
     * 创建数据表
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tab_name");
        onCreate(db);
    }

    /**
     * ContentValues values = new ContentValues();
     * values.put("column_name", "data");
     * --> insert("table_name", null, values)
     *
     * @param tableName
     * @param nullColumnHack
     * @param values
     */
    public long insert(String tableName, String nullColumnHack, ContentValues values) {
        return dbHelper.getWritableDatabase()
                .insert(tableName, nullColumnHack, values);
    }

    /**
     * 更新某条数据
     *
     * @return count
     */
    public long update(String table, ContentValues values, String whereClause,
                       String[] whereArgs) {
        return dbHelper.getWritableDatabase()
                .update(table, values, whereClause, whereArgs);
    }

    /**
     * 查询数据
     *
     * @param sql
     * @param selectionArgs
     * @return cursor
     */
    public Cursor query(String sql, String[] selectionArgs) {
        return dbHelper.getReadableDatabase().rawQuery(sql, selectionArgs);
    }

    /**
     * 删除
     *
     * @return count
     */
    public long delete(String table, String whereClause, String[] whereArgs) {
        return dbHelper.getWritableDatabase()
                .delete(table, whereClause, whereArgs);
    }

    /**
     * 删除所有数据
     *
     * @return count
     */
    public long deleteAllData(String tableName) {
        return dbHelper.getWritableDatabase().delete(tableName, null, null);
    }

}
