package com.guilherme.paiva.effectivefactory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by guilhermepaiva on 17/02/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "effectivefactory.db";

    //database Version
    private static final int DATABASE_VERSION = 1;

    //column names
    public static final String TABLE_FACTORY = "factories";
    public static final String ID_FACTORY = "_id";
    public static final String FACTORY_NAME = "factory_name";
    public static final String FACTORY_ADDRESS = "factory_address";

    //factory table create statement
    private static final String CREATE_TABLE_FACTORY = "CREATE TABLE " + TABLE_FACTORY
            + "( " + ID_FACTORY + " INTEGER PRIMARY KEY," + FACTORY_NAME + " TEXT,"
            + FACTORY_ADDRESS + " TEXT" + ")";



    //EMPLOYEE
    //column names employee
    public static final String TABLE_EMPLOYEE = "employees";
    public static final String ID_EMPLOYEE = "_id";
    public static final String EMPLOYEE_NAME = "employee_name";
    public static final String EMPLOYEE_SUPERVISOR = "employee_supervisor";
    public static final String EMPLOYEE_CELL = "employee_cell";
    public static final String EMPLOYEE_CELULAR = "employee_celular";

    private static final String CREATE_TABLE_EMPLOYEE = "CREATE TABLE " +
            TABLE_EMPLOYEE +
            "( " + ID_EMPLOYEE + " INTEGER PRIMARY KEY," +
            EMPLOYEE_NAME + " TEXT," +
            EMPLOYEE_SUPERVISOR + " TEXT," +
            EMPLOYEE_CELL + "  TEXT," +
            EMPLOYEE_CELULAR + " TEXT)";

    //OPERATIVE POST
    //column names for operative post
    public static final String TABLE_OPERATIVE_POST = "operative_posts";
    public static final String ID_OPERATIVE_POST = "_id";
    public static final String OPERATIVE_POST_NAME = "operative_post_name";
    public static final String OPERATIVE_POST_NUMBER = "operative_post_number";

    //operative post table create statement
    private static final String CREATE_TABLE_OPERATIVE_POST = "CREATE TABLE " + TABLE_OPERATIVE_POST
            + "( " + ID_OPERATIVE_POST + " INTEGER PRIMARY KEY," + OPERATIVE_POST_NAME + " TEXT,"
            + OPERATIVE_POST_NUMBER + " TEXT" + ")";

    //PRODUCTS
    //column names for products
    public static final String TABLE_PRODUCT = "products";
    public static final String ID_PRODUCT = "_id";
    public static final String PRODUCT_NAME = "product_name";

    //product table create statement
    private static final String CREATE_TABLE_PRODUCT = "CREATE TABLE " + TABLE_PRODUCT
            + "( " + ID_PRODUCT + " INTEGER PRIMARY KEY," + PRODUCT_NAME + " TEXT" + ")";


    //OS
    //column names for os
    public static final String TABLE_OS = "os";
    public static final String ID_OS = "_id";
    public static final String LOTE_OS = "lote_os";
    public static final String PRODUCT_OS = "product_os";
    public static final String AMOUNT_PRODUCT = "amount_product";
    public static final String DATE_CREATED_OS = "date_created";

    //os table create statement
    private static final String CREATE_TABLE_OS = "CREATE TABLE " + TABLE_OS
            + "( " + ID_OS + " INTEGER PRIMARY KEY,"
            + LOTE_OS + " TEXT, "
            + PRODUCT_OS + " TEXT, "
            + AMOUNT_PRODUCT + " INTEGER, "
            + DATE_CREATED_OS + " DATE DEFAULT CURRENT_DATE" + ")";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FACTORY);
        db.execSQL(CREATE_TABLE_EMPLOYEE);
        db.execSQL(CREATE_TABLE_OPERATIVE_POST);
        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_OS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FACTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPERATIVE_POST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OS);
        onCreate(db);
    }
}
