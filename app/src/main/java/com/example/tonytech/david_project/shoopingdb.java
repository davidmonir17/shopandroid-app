package com.example.tonytech.david_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class shoopingdb extends SQLiteOpenHelper {

   public  static String databaseName= "marketdb1";
    SQLiteDatabase marketdb;
    public shoopingdb(Context context) {
        super(context, databaseName, null,2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table customer(custid integer  PRIMARY key autoincrement," +
                "cutname text not null , username text not null, " +
                "password text not null, gender text not null, " +
                "birthdate text not null, job text not null )");
        db.execSQL("create table categories(catid integer primary key autoincrement ,catname text not null )");
        db.execSQL("create table products(proid integer primary key autoincrement,proname text not null ,price integer not null,quantity integer not null,catsid integer not null ,foreign key (catsid) REFERENCES categories(catid) )" );
        db.execSQL( "create table orders(orderid integer primary key autoincrement, orderdate text not null,custid integer not null,address text not null, foreign key (custid)references customer(custid) )");
        db.execSQL("create table order_details(orderid integer not null , proid integer not null,quantity integer not null,primary key (orderid,proid),foreign key(orderid)references orders(orderid),foreign key(proid)references products(proid))");
        db.execSQL("create table dafkra(daid integer primary key autoincrement,custoid integer,prductid integer,quantaty integer,foreign key(custoid)references customer(custid) ,foreign key(prductid)references products(proid))");
        db.execSQL("insert into customer(cutname ,username , password , gender , birthdate , job) values ('david','davidmonir17','1234','male','17/9/1997','manager'),('d','d','d','male','17/9/1997','d')");
        db.execSQL("insert into categories(catname)values('clothes'),('mob'),('shoes') ");
        db.execSQL("insert into products(proname,price,quantity,catsid)values('T-shirt','100','9',1),('coat','100','9',1),('dress','100','9',1),('swimsuit','100','9',1),('sneakers','100','9',3),('ugs','100','9',3),('seafty','100','9',3),('oppo F7','100','9',2),('oppo F9','100','9',2),('Iphone 6','100','9',2)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists customer");
        db.execSQL("drop table if exists categories");
        db.execSQL("drop table if exists products");
        db.execSQL("drop table if exists orders");
        db.execSQL("drop table if exists order_details");
        db.execSQL("drop table if exists dafkra");
        onCreate(db);
    }

    public void da()
    {
        marketdb=getWritableDatabase();
        marketdb.delete("dafkra",null,null);
        marketdb.close();
    }
    ////////////////////////////////////////////////////////////////////////////////////
    public void deleteda( int id)
    {
        marketdb=getWritableDatabase();
        marketdb.delete("dafkra","prductid='"+id+"'",null);
        marketdb.close();
    }
    //////////////////////////////////////////////////////////////////////////////////////
public boolean cheackproduct(int id)
{
    marketdb=getReadableDatabase();
    Cursor cursor=marketdb.rawQuery("select * from dafkra where prductid='"+id+"'",null);
    if(cursor.getCount()>0)
        return true;
    else
        return false;

}

    ///////////////////////////////////////////////////////////////////////////////////

    //order_details(orderid integer not null , proid integer not null,quantity
    public void makeorderdetails(int orderid,int prodid,int qu)
    {
        marketdb=getWritableDatabase();
        ContentValues row= new ContentValues();
        row.put("orderid",orderid);
        row.put("proid",prodid);
        row.put("quantity",qu);
        marketdb.insert("order_details",null,row);
        marketdb.close();

    }
    public int lastorder ()
    {marketdb=getReadableDatabase();
    Cursor cursor=marketdb.rawQuery("select*from orders ",null);
    cursor.moveToLast();
    return cursor.getInt(0);
    }

    //orderid integer primary key autoincrement, orderdate text not null,custid integer not null,address
    public void makeorder(String dat,int cusi,String add)
    { marketdb=getWritableDatabase();
        ContentValues row= new ContentValues();
        row.put("orderdate",dat);
        row.put("custid",cusi);
        row.put("address",add);
        marketdb.insert("orders",null,row);
        marketdb.close();
    }
    public  boolean addcus(String name,String user,String pass ,String gener,String birth,String job)
    {//cutname ,username , password , gender , birthdate , job

        marketdb=getWritableDatabase();
        ContentValues row= new ContentValues();
        row.put("cutname",name);
        row.put("username",user);
        row.put("password",pass);
        row.put("gender",gener);
        row.put("birthdate",birth);
        row.put("job",job);
        long i= marketdb.insert("customer",null,row);
        // marketdb.close();
        if(i==-1){
            return  false;
        }else
            return  true;

    }
    public void insertdafkra(int cuid,int proid,int qua){
        marketdb=getWritableDatabase();
        ContentValues row= new ContentValues();
        row.put("custoid",cuid);
        row.put("prductid",proid);
        row.put("quantaty",qua);
        marketdb.insert("dafkra",null,row);
    }
    public Boolean cheackus(String user)
    {
        marketdb=getReadableDatabase();
        Cursor cursor= marketdb.rawQuery("select * from customer where username=? ",new String[]{user} );
        if (cursor.getCount()>0)
            return false ;
        else
            return  true;

    }
    public  boolean cheackacc(String nam, String pass) {
        marketdb = getReadableDatabase();
        Cursor cursor = marketdb.rawQuery("select * from customer where username=? and password=?", new String[]{nam, pass});
        if (cursor.getCount() > 0)
        {
            return  true;
        }else
            return  false;
    }
    public Cursor searchcat(String nam)
    {
        marketdb=getReadableDatabase();
        // String[] rowDetails={nam};
        //new String[]{"%"+nam+"%","%"+nam+"%"}
        Cursor cursor=marketdb.rawQuery("select * from  products p join categories c on p.catsid=c.catid where c.catname like '%"+nam+"%' or  p.proname like '%"+nam+"%' ",null);
        //       Cursor cursor=marketdb.rawQuery("select * from  products p inner join categories c on p.catsid=c.catid where c.catname like '%"+nam+"%' union select * from products where proname like '%"+nam+"%' ",null);
        cursor.moveToFirst();
        marketdb.close();
        return cursor;


    }
    public Cursor getcat(int id){
        marketdb=getReadableDatabase();
        Cursor cursor=marketdb.rawQuery("select * from products where proid="+id,null);
        cursor.moveToFirst();
        marketdb.close();
        return cursor;
    }

    public Cursor Fetchall()
    {
        marketdb=getReadableDatabase();
        // String[] rowDetails={nam};
        Cursor cursor=marketdb.rawQuery("select * from products ",null);
        cursor.moveToFirst();
        marketdb.close();
        return cursor;

    }
    public Cursor getcusid(String usern)
    {
        marketdb=getReadableDatabase();
        Cursor cursor= marketdb.rawQuery("select * from customer where username='"+usern+"'",null);
        cursor.moveToFirst();
       // marketdb.close();
        return cursor;
    }
    public boolean chaekjob(String bd,String name)
    {
        marketdb=getReadableDatabase();
        Cursor cursor= marketdb.rawQuery("select  * from customer where job='"+bd+"' and username='"+name+"' ",null);
        if (cursor.getCount() > 0)
        {
            return  true;
        }else
            return  false;

    }
    public  void updateproduct(int id,int nequa)
    {  marketdb=getWritableDatabase();
        ContentValues row= new ContentValues();
        row.put("quantity",nequa);
        marketdb.update("products",row,"proid ='"+id+"'",null);
        marketdb.close();
    }
    public  void updateCustomer(String user,String pass)
    {  marketdb=getWritableDatabase();
        ContentValues row= new ContentValues();
        row.put("password",pass);
        marketdb.update("customer",row,"username = ?",new String[]{user});
        marketdb.close();
    }
    //"select * from  products p join categories c on p.catsid=c.catid where c.catname like '%"+nam+"%' or  p.proname like '%"+nam+"%' "

    public Cursor getcart(int cusid)
    {
        marketdb=getReadableDatabase();
        Cursor cursor=marketdb.rawQuery("select * from products p join dafkra d where d.custoid='"+cusid+"' and d.prductid=p.proid",null);
        cursor.moveToFirst();
        marketdb.close();
        return cursor;
    }

    public Cursor getcartid(String user)
    {
        marketdb=getReadableDatabase();
        Cursor cursor=marketdb.rawQuery("select * from products p join dafkra d where d.prductid=p.proid and p.proname='"+user+"'",null);
        cursor.moveToFirst();
        marketdb.close();
        return cursor;
    }

    public void delda(int cusid)
    {marketdb=getWritableDatabase();
        marketdb.delete("dafkra","custoid='"+cusid+"'",null);
        marketdb.close();
    }
    public void updateda(int proid,int qu)
    {
        marketdb=getWritableDatabase();
        ContentValues row=new  ContentValues();
        //, integer,
        row.put("quantaty",qu);
        marketdb.update("dafkra",row,"prductid='"+proid+"'",null);
        marketdb.close();


    }
}
