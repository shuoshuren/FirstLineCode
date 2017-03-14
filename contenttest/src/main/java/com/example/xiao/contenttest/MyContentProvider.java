package com.example.xiao.contenttest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by xiao on 2017/3/13.
 */

public class MyContentProvider extends ContentProvider {

    private static final int TABLE1_DIR = 0;
    private static final int TABLE1_ITEM = 1;
    private static final int TABLE2_DIR = 2;
    private static final int TABLE2_ITEM = 3;

    private static UriMatcher uriMatcher;

    /**
     * *：表示匹配任意长度的任意字符
     * #：表示匹配任意长度的数字
     * 匹配任意表的内容URI格式：content://com.example.app.provider/*
     * 匹配table1表中任意一行数据的内容URI格式：content://com.example.app.provider/table1/#
     */
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.xiao.contenttest.provider", "table1", TABLE1_DIR);
        uriMatcher.addURI("com.example.xiao.contenttest.provider", "table1/#", TABLE1_ITEM);
        uriMatcher.addURI("com.example.xiao.contenttest.provider", "table2", TABLE2_DIR);
        uriMatcher.addURI("com.example.xiao.contenttest.provider", "table2/#", TABLE2_ITEM);
    }

    /**
     * 初始化内容提供者的时候调用，通常会在这里完成对数据库创建和升级操作，
     * 只有当存在ContentResolver尝试访问我们程序中的数据时，内容提供器才会被初始化
     *
     * @return
     */
    @Override
    public boolean onCreate() {
        return false;
    }

    /**
     * 从内容提供器中查询数据
     *
     * @param uri           确定查询哪张表
     * @param projection    确定查询那些列
     * @param selection     用于约束查询那些列
     * @param selectionArgs 用于约束查询那些列
     * @param sortOrder     对结果进行排序
     * @return
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR://查询表table1中的所有数据
                break;
            case TABLE1_ITEM://查询表table1中的单条数据
                break;
            case TABLE2_DIR://查询表table2中的所有数据
                break;
            case TABLE2_ITEM://查询表table2中的单条数据
                break;
        }


        return null;
    }

    /**
     * 根据传入的URI返回相应的MIME类型
     * 一个内容URI对应的MIME字符串主要有3部分组成：
     * 1.必须以vnd开头
     * 2.如果内容URI以路径结尾，则后接android.cursor.dir/,如果内容URI以id结尾，则后接android.cursor.item/.
     * 3.最后结束vnd.<authority>.<path>.
     * <p>
     * eg:
     * 对于content://com.example.app.provider/table1这个内容uri，所对应的MIME类型可写成：
     * vnd.android.cursor.dir/vnd.com.example.app.provider.table1
     * 对于content://com.example.app.provider/table1/1这个内容URi，所对应的MIME类型可写成：
     * vnd.android.cursor.item/vnd.com.example.app.provider.table1
     *
     * @param uri
     * @return
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider.table1";
            case TABLE1_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.app.provider.table1";
            case TABLE2_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider.table2";
            case TABLE2_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.app.provider.table2";
        }

        return null;
    }

    /**
     * 插入数据
     *
     * @param uri    确定要添加的表
     * @param values 代保存的数据
     * @return 用于表示这条新记录的uri
     */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }


    /**
     * 删除数据
     *
     * @param uri           确定要删除的表
     * @param selection     约束删除的那些行
     * @param selectionArgs 约束删除的那些行
     * @return 删除的行数
     */
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    /**
     * 更新数据
     *
     * @param uri           确定要更新的表
     * @param values        更新的数据
     * @param selection     约束更新的那些行
     * @param selectionArgs 约束更新的那些行
     * @return 受影响的行数
     */
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
