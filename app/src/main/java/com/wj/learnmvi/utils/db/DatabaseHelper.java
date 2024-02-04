package com.wj.learnmvi.utils.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.wj.learnmvi.base2.impl.MyApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:WJ
 * Date:2024/1/26 10:14
 * Describe：
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;

    private DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static DatabaseHelper getInstance(){
        return Holder.Instance;
    }
    static class Holder {
        public static final DatabaseHelper Instance =new DatabaseHelper(MyApp.Companion.getInstance());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建"users"表
        String createUserTable = "CREATE TABLE users (id INTEGER PRIMARY KEY, name TEXT)";
        db.execSQL(createUserTable);

        // 创建"orders"表
        String createOrderTable = "CREATE TABLE orders (id INTEGER PRIMARY KEY, user_id INTEGER, product TEXT,FOREIGN KEY (user_id) REFERENCES users(id))";
        db.execSQL(createOrderTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 升级数据库
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS orders");
        onCreate(db);
    }

    public void addUser(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "insert into "+" users"+" (name) values(?)";
        Object[] values = new Object[]{name};
        db.execSQL(sql, values);
        db.close();
    }
    public void addOrder(Long usrId,Order order){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "insert into "+"orders"+" (user_id,product) values(?,?)";
        Object[] values = new Object[]{usrId,order.product};
        db.execSQL(sql, values);
        db.close();
    }

    public List<Order> getOrdersByUserName(String userName) {
        List<Order> orders = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT orders.id,orders.user_id, orders.product FROM orders INNER JOIN users ON orders.user_id = users.id WHERE users.name = ?";
        Cursor cursor = db.rawQuery(query, new String[]{userName});

        if (cursor.moveToFirst()) {
            do {
                int orderId = cursor.getInt(cursor.getColumnIndex("id"));
                int userId = cursor.getInt(cursor.getColumnIndex("user_id"));
                String product = cursor.getString(cursor.getColumnIndex("product"));

                Order order = new Order(orderId, userId,product);
                orders.add(order);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return orders;
    }
}
