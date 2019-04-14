package com.aguyiknow.groceries;
import android.content.*;
import android.database.*;
import android.database.sqlite.*;

public class GroceriesDB extends SQLiteOpenHelper
{
	public GroceriesDB(Context context){
		super(context,"items.db",null,1);
	}

	@Override
	public void onCreate(SQLiteDatabase p1)
	{
		p1.execSQL("CREATE TABLE ITEMS(itemID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, quantity INTEGER NOT NULL, note TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase p1, int p2, int p3)
	{
		// TODO: Implement this method
	}
	
	public boolean insertItem(ItemHandler handler){
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues content=new ContentValues();
		content.put("name",handler.getName());
		content.put("quantity",handler.getQuantity());
		content.put("note",handler.getNote());
		long result=db.insert("ITEMS",null,content);
		db.close();
		if(result==-1)
			return false;
		else
			return true;
	}
	
	public Cursor getAllItem(){
		SQLiteDatabase db=this.getReadableDatabase();
		return db.rawQuery("SELECT * FROM ITEMS",null);
	}
	
	public boolean deleteItem(ItemHandler handler){
		SQLiteDatabase db=this.getWritableDatabase();
		int result=db.delete("ITEMS","itemID=?",new String[]{""+handler.getId()});
		db.close();
		if(result>0){
			return true;
		}else{
			return false;
		}
	}
}
