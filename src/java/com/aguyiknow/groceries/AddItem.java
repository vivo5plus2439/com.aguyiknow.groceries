package com.aguyiknow.groceries;

import android.content.*;
import android.database.*;
import android.os.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;

public class AddItem extends AppCompatActivity
{
	EditText etName,etQuantity,etNote;
	Button btnAdd;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item);
		context=this;
		
		etName=(EditText)findViewById(R.id.activityadditemEditText1);
		etQuantity=(EditText)findViewById(R.id.activityadditemEditText2);
		etNote=(EditText)findViewById(R.id.activityadditemEditText3);
		btnAdd=(Button)findViewById(R.id.activityadditemButton1);
		
		btnAdd.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					if(etName.getText().toString().isEmpty()||etQuantity.getText().toString().isEmpty()){
						Toast.makeText(context,"Name and quantity is required",Toast.LENGTH_LONG).show();
					}
					else
					{
						ItemHandler handler=new ItemHandler(etName.getText().toString(),Integer.parseInt(etQuantity.getText().toString()),etNote.getText().toString());
						GroceriesDB db=new GroceriesDB(context);
						if(db.insertItem(handler)){
							Cursor cursor=db.getAllItem();
							Toast.makeText(context,"New Item Added",Toast.LENGTH_LONG).show();
							etName.setText("");
							etQuantity.setText("");
							etNote.setText("");
						}else{
							Toast.makeText(context,"An error occurred while trying to add item \""+handler.getName()+"\"",Toast.LENGTH_LONG).show();
						}
					}
				}
				
			
		});
	}
	
}
