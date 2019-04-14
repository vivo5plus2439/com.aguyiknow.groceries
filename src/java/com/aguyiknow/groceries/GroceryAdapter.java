package com.aguyiknow.groceries;
import android.app.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import android.util.*;
import android.content.*;

public class GroceryAdapter extends ArrayAdapter
{
	Activity context;
	ArrayList<ItemHandler> items;
	ArrayList<ViewHolder> holder;
	DeleteItem deleteItem;
	GroceriesDB db;
	
	public interface DeleteItem{
		public void onDeleteItem(ItemHandler handler)
	}
	
	public GroceryAdapter(Activity context, ArrayList<ItemHandler> items){
		super(context,R.layout.listview_row,items);
		this.context=context;
		this.items=items;
		holder=new ArrayList<ViewHolder>();
		
	}
	
	public void setOnDeleteItem(DeleteItem deleteItem){
		this.deleteItem=deleteItem;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
	
		LayoutInflater inflater=context.getLayoutInflater();
		View row=inflater.inflate(R.layout.listview_row,parent,false);
		
		TextView tvName,tvQuantity,tvNote;
		ImageButton imgBtnDelete;
		tvName=(TextView)row.findViewById(R.id.listviewrowTextView1);
		tvQuantity=(TextView)row.findViewById(R.id.listviewrowTextView2);
		tvNote=(TextView)row.findViewById(R.id.listviewrowTextView3);
		imgBtnDelete=(ImageButton)row.findViewById(R.id.listviewrowImageButton1);
		
		tvName.setText("Name: "+items.get(position).getName());
		tvQuantity.setText("Quantity: "+items.get(position).getQuantity());
		tvNote.setText("Note: "+items.get(position).getNote());
		
		holder.add(new ViewHolder(imgBtnDelete,position,items.get(position)));
			
		return row;
	}
	
	private class ViewHolder{
		ImageButton imgBtn;
		int position;
		ItemHandler handler;
		
		public ViewHolder(ImageButton imgBtn,int pos,ItemHandler handlerDel){
			this.imgBtn=imgBtn;
			position=pos;
			handler=handlerDel;
			
			imgBtn.setOnClickListener(new View.OnClickListener(){

					@Override
					public void onClick(View p1)
					{
						db=new GroceriesDB(context);
						AlertDialog.Builder alert=new AlertDialog.Builder(context);
						alert.setTitle("Delete");
						alert.setMessage("Are you sure you want to delete the item \""+handler.getName()+"\"?");
						alert.setPositiveButton("Yes", new DialogInterface.OnClickListener(){

								@Override
								public void onClick(DialogInterface p1, int p2)
								{
									db.deleteItem(handler);
									deleteItem.onDeleteItem(handler);
								}
								
							
						});
						alert.setNegativeButton("No", new DialogInterface.OnClickListener(){

								@Override
								public void onClick(DialogInterface p1, int p2)
								{
									p1.dismiss();
								}


							});
						alert.show();
					}

				});
		}
	}
}
