package com.aguyiknow.groceries;

import android.content.*;
import android.database.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;
import java.util.*;

public class MainActivity extends AppCompatActivity
{
	Context context;
	ListView lv;
	ArrayList<ItemHandler> items;
	GroceriesDB db;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		context=this;
		
		FloatingActionButton fab;
		
		lv=(ListView)findViewById(R.id.mainListView);
		fab=(FloatingActionButton)findViewById(R.id.mainFloatingActionButton);
		
		db=new GroceriesDB(context);
		View footer = (View)getLayoutInflater().inflate(R.layout.list_footer,null);
		lv.addFooterView(footer);
		displayList();
		
		fab.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					Intent intent=new Intent(context, AddItem.class);
					startActivityForResult(intent,1);
				}

			
		});
    }
	
	public void displayList(){
		Cursor cursor=db.getAllItem();
		items=new ArrayList<ItemHandler>();

		while(cursor!=null&&cursor.moveToNext()){
			ItemHandler handler=new ItemHandler(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3));
			items.add(handler);
		}

		GroceryAdapter adapter=new GroceryAdapter(this,items);
		adapter.setOnDeleteItem(new GroceryAdapter.DeleteItem(){

				@Override
				public void onDeleteItem(ItemHandler handler)
				{
					displayList();
				}
				
			
		});

		lv.setAdapter(adapter);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(requestCode==1){
			displayList();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
}
