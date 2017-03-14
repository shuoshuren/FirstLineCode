package com.example.xiao.litepaltest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.xiao.litepaltest.bean.Book;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * 创建数据库
     * @param view
     */
    public void createDB(View view){

        Connector.getDatabase();

    }

    /**
     * 添加记录
     * @param view
     */
    public void addRecord(View view){
        Book book = new Book();
        book.setName("The dev code");
        book.setAuthor("dan");
        book.setPages(543);
        book.setPrice(17.7);
        book.setPress("Unknown");
        book.save();
        book.setPrice(10.2);
        book.save();
    }

    /**
     * 更新数据
     * @param view
     */
    public void updateData(View view){
        Book book = new Book();
        book.setPress("Anchor");
        book.setPrice(17.7);
        book.updateAll("author = ?","dan");

    }

    /**
     * 查询数据
     * @param view
     */
    public void queryData(View view){
        List<Book> list = DataSupport.findAll(Book.class);
        for (Book book:list){
            Log.i(TAG, "queryData: book:"+book.toString());
        }
    }

}
