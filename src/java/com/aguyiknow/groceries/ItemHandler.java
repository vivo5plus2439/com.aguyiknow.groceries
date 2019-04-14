package com.aguyiknow.groceries;

public class ItemHandler
{
	String name,note;
	int quantity;
	int id=0;

	public ItemHandler(){}
	public ItemHandler(String name,int quantity,String note){
		this.name=name;
		this.quantity=quantity;
		this.note=note;
	}
	public ItemHandler(int id, String name,int quantity,String note){
		this.id=id;
		this.name=name;
		this.quantity=quantity;
		this.note=note;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public String getNote()
	{
		return note;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public int getQuantity()
	{
		return quantity;
	}
	
}
