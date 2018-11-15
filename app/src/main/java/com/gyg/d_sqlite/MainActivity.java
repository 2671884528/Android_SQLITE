package com.gyg.d_sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase database;
    private ListView mlv;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB_Helper db_helper = new DB_Helper(this);
        database = db_helper.getWritableDatabase();

        //SimpleCursorAdapter 适配器,null指代的地方是cursor对象
        mlv = findViewById(R.id.lv);
        adapter = new SimpleCursorAdapter(this,R.layout.layout,null,
                new String[] {"_id","name","nickname"},new int[] {R.id.tv_id,R.id.tv_name,R.id.tv_nickname},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mlv.setAdapter(adapter);
    }

    public void insert(View view){

//        String sql = "insert into student(_id,name,nickname) values(2017,'傲娇刚','小老虎')";
//        database.execSQL(sql);
        //该类中有返回的方法，可以知道到底有没有插入表,底层是MAP
        for (int i=0;i<50;i++){
            ContentValues con = new ContentValues();
            con.put("_id",i);
            con.put("name","傲娇刚");
            con.put("nickname","小可爱"+i);
            //返回最近插入的一行的行号
            long insert = database.insert("student",null,con);
            // insert > 0表示插入成功
//        if (insert>0){
//            Toast.makeText(this,"添加成功",Toast.LENGTH_SHORT).show();
//        }
        }

    }

    public void query(View view){
        //当使用SimpleCursorAdapter时候 cursor必须包裹_id这个字段，可以不使用里面的数据
        //cursor 游标
        Cursor cursor = database.query("student",null,"name=?",
                new String[] {"傲娇刚"},null,null,null);
        //将一个新的cursor交换旧的cursor
        adapter.swapCursor(cursor);
        //刷新list View的数据
        adapter.notifyDataSetChanged();

//        while (cursor.moveToNext()){
//            int id = cursor.getInt(cursor.getColumnIndex("_id"));
//            String name = cursor.getString(cursor.getColumnIndex("name"));
//            String nickname = cursor.getString(cursor.getColumnIndex("nickname"));
//
//            Toast.makeText(this,id+"\t"+name+"\t"+nickname, Toast.LENGTH_SHORT).show();
//        }

    }

    //修改表
    public void update(View view) {

        ContentValues values = new ContentValues();
        values.put("name","小鱼儿");
        //受着一条语句影响的行数，如果有li
        long result = database.update("student",values,"nickname=?",new String[] {"小可爱"});

        if (result>0){
            Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
        }

    }

    //删除表
    public void delete(View view) {
        int result = database.delete("student","name=?",new String[] {"傲娇刚"});
        if (result>0){
            Toast.makeText(this,"删除成功",Toast.LENGTH_SHORT).show();

        }
    }
}
