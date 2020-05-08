package MainApp;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class Order {
//(OrderNo number(5), item_no number(4),ord_qty number(3), ord_date
//	varchar2(10), custNo varchar2(10)
	int orderNo,itemNo, ord_qty;
	String custno;
	Date ord_date;
	public Order() {
		
	}
	public Order(int orderNo, int itemNo, int ord_qty, String custno, Date ord_date) {
		super();
		this.orderNo = orderNo;
		this.itemNo = itemNo;
		this.ord_qty = ord_qty;
		this.custno = custno;
		this.ord_date = ord_date;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public int getOrd_qty() {
		return ord_qty;
	}
	public void setOrd_qty(int ord_qty) {
		this.ord_qty = ord_qty;
	}
	public String getCustno() {
		return custno;
	}
	public void setCustno(String custno) {
		this.custno = custno;
	}
	public Date getOrd_date() {
		return ord_date;
	}
	public void setOrd_date(Date ord_date) {
		this.ord_date = ord_date;
	}
	static void searchItem(Connection cn, Scanner sc,int itemno,int qty, String custNo,FileWriter writer) throws SQLException
	{
		String sql="select * from item_xxxx where item_no=?";
		PreparedStatement ps=cn.prepareStatement(sql);
		try {
			ps.setInt(1,itemno);
		ResultSet rs=ps.executeQuery();
		if(rs.next()==false)
			System.out.println("No items match your description");
		else
		{
		do
		{
			int itemNo  = rs.getInt("item_no");
	        String description= rs.getString("description");
	        String category = rs.getString("category");
	        int Qty= rs.getInt("qty");
	        float price=rs.getFloat("price");
	        //Display values
	        System.out.print("Item No: " + itemno);
	        System.out.print(", Description: " + description);
	        System.out.print(", Category: " + category);
	        System.out.print(", Quantity: " + qty);
	        System.out.println(", Price: "+price);
	     }while(rs.next());
	     rs.close();
			System.out.println("Do you want to add this product to cart?");
			String response=sc.next();
			if(response.equalsIgnoreCase("y")|| response.equalsIgnoreCase("yes"))
				addOrder(cn,itemno,qty,custNo,writer);
			else
			{
				System.out.println("Exiting");
			}
		}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	static void addOrder(Connection cn,int itemno, int qty, String custNo,FileWriter writer)
	{
		//(item_no number(4), description varchar2(20), category varchar2(15),price
//		number(6,2), qty number(3)
		String sql = "insert into Order_XXXX(item_no,ord_qty,custNo) values(?,?,?)"; 
		try {
		PreparedStatement ps=cn.prepareStatement(sql);  
		ps.setInt(1,itemno);//1 specifies the first parameter in the query  
		ps.setInt(2,qty);
		ps.setString(3, custNo);
		int i=ps.executeUpdate();  
		try {
			if (i == 1)
					writer.write("added successfully");
			else
		        writer.write("addition failed"); 
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
	static void showAllOrders(Connection cn)
	{
		String sql="Select * from order_XXXX";
		try {
		Statement st=cn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if (rs.next() == false) {
	        System.out.println("No data found");
	      } 
		else {
	    	  do {
	    		  String item;
	    		  int orderNo=rs.getInt(1);
	    		  int itemNo=rs.getInt(2);
	    		  int order_qty=rs.getInt(3);
	    		  Date ord_date=rs.getDate(4);
	    		  String custNo=rs.getString(5);
	    		  System.out.println(orderNo+" "+itemNo+" "+order_qty+" "+ord_date+" "+custNo);
	        } while (rs.next());
	      }
	}
		catch(SQLException e)
		{
		e.printStackTrace();
		}
	}
	static void showSpecificOrder(Connection cn, int orderNo)
	{
		String sql="Select * from order_XXXX where orderNo=?";
		try {
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setInt(1,orderNo);
			ResultSet rs=ps.executeQuery();
			if (rs.next() == false) {
		        System.out.println("No data found");
		      }
			else {
		    	  do {
		    		  int orderno=rs.getInt(1);
		    		  int itemNo=rs.getInt(2);
		    		  int order_qty=rs.getInt(3);
		    		  Date ord_date=rs.getDate(4);
		    		  int custNo=rs.getInt(5);
		    		  System.out.print(orderno+" "+itemNo+" "+order_qty+" "+ord_date+" "+custNo);
		        } while (rs.next());
		      }
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}
