package MainApp;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Item {
//(item_no number(4), description varchar2(20), category varchar2(15),price
//	number(6,2), qty number(3)
	int item_no,qty;
	float price;
	String description,category;
	public Item() {}
	public Item(int item_no, int qty, float price, String description, String category) {
		this.item_no = item_no;
		this.qty = qty;
		this.price = price;
		this.description = description;
		this.category = category;
	}
	public int getItem_no() {
		return item_no;
	}
	public void setItem_no(int item_no) {
		this.item_no = item_no;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String	 category) {
		this.category = category;
	
	}	
public void addItem(Connection cn,Scanner sc,FileWriter writer)
{
	//(item_no number(4), description varchar2(20), category varchar2(15),price
//	number(6,2), qty number(3)
	System.out.println("Name of item: ");
	String description=sc.next();
	System.out.println("Category: ");
	String category=sc.next();
	System.out.println("Item Number: ");
	int itemno=sc.nextInt();
	System.out.println("Price: ");
	float price=sc.nextFloat();
	System.out.println("Quantity: ");
	int qty=sc.nextInt();
	Item item=new Item(itemno, qty, price, description, category);
	String sql = "insert into item_xxxx values(?,?,?,?,?)"; 
	PreparedStatement ps;
	try {
		ps = cn.prepareStatement(sql);
	ps.setInt(1,itemno);//1 specifies the first parameter in the query  
	ps.setString(2,description);
	ps.setString(3, category);
	ps.setInt(4,qty);
	ps.setFloat(5, price);
	int i=ps.executeUpdate();  
	try {
	if (i == 1)
			writer.write("inserted successfully");
	else
        writer.write("insertion failed"); 
	}
	catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	}
	 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

static void modifyItem(Connection cn, int itemno,String description,FileWriter writer)
{
		String sql = "Update item_XXXX set description=? where item_no=?";
		PreparedStatement ps = null;
		try {
		  ps = cn.prepareStatement(sql);
		  ps.setString(1, description);
		  ps.setInt(2, itemno);
		  int i=ps.executeUpdate();
		  try {
				if (i == 1)
						writer.write("updated successfully");
				else
			        writer.write("updation failed"); 
				}
				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

static void deleteItem(Connection cn, int itemno,FileWriter writer)
{
		String sql="Delete from Item_XXXX where item_no=?";
		PreparedStatement ps=null;
		try {
			ps=cn.prepareStatement(sql);
			ps.setInt(1,itemno);
			int i=ps.executeUpdate();
			try {
				if (i == 1)
						writer.write("inserted successfully");
				else
			        writer.write("insertion failed"); 
				}
				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}


static void searchItem(Connection cn, Scanner sc) throws SQLException
{
	System.out.println("Enter item number to be searched");
	int itemNo=sc.nextInt();
	String sql="select * from item_xxxx where item_no=?";
	PreparedStatement ps=cn.prepareStatement(sql);
	try {
		ps.setInt(1,itemNo);
	ResultSet rs=ps.executeQuery();
	if(rs.next()==false)
		System.out.println("No items match your description");
	else {
	do
	{
		int itemno  = rs.getInt("item_no");
        String description= rs.getString("description");
        String category = rs.getString("category");
        int qty= rs.getInt("qty");
        float price=rs.getFloat("price");
        //Display values
        System.out.print("Item No: " + itemno);
        System.out.print(", Description: " + description);
        System.out.print(", Category: " + category);
        System.out.print(", Quantity: " + qty);
        System.out.println(", Price: "+price);
     }while(rs.next());
     rs.close();
	}
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
}

}
