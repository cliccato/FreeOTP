package org.eu.fondatore.freeotp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TOTPDatabase";
    private static final String TABLE_SERVICES = "services";
    private static final String KEY_ID = "id";
    private static final String KEY_SERVICE_NAME = "service_name";
    private static final String KEY_SECRET_KEY = "secret_key";

    private static final int SERVICE_NAME_LENGTH = 50;
    private static final int SECRET_KEY_LENGTH = 100;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SERVICES_TABLE = "CREATE TABLE " + TABLE_SERVICES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_SERVICE_NAME + " VARCHAR(" + SERVICE_NAME_LENGTH + "),"
                + KEY_SECRET_KEY + " VARCHAR(" + SECRET_KEY_LENGTH + ")" + ")";
        db.execSQL(CREATE_SERVICES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICES);
        onCreate(db);
    }

    public void addService(String serviceName, String secretKey) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SERVICE_NAME, serviceName);
        values.put(KEY_SECRET_KEY, secretKey);
        db.insert(TABLE_SERVICES, null, values);
        db.close();
    }

    public List<Pair<String, String>> getAllServices() {
        List<Pair<String, String>> serviceKeys = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SERVICES, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String serviceName = cursor.getString(cursor.getColumnIndexOrThrow(KEY_SERVICE_NAME));
                String secretKey = cursor.getString(cursor.getColumnIndexOrThrow(KEY_SECRET_KEY));
                serviceKeys.add(new Pair<>(serviceName, secretKey));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return serviceKeys;
    }

    public void deleteAllServices() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SERVICES, null, null);
        db.close();
    }
}
