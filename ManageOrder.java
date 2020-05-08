package MainApp;

import java.sql.SQLException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
public class ManageOrder {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
			FileWriter writer = new FileWriter("MyFile.txt", true);
			try {
			String url = "jdbc:mysql://localhost:3306/principal";
			Connection cn = DriverManager.getConnection(url,"root","admin");
			System.out.println("Welcome!!");
			welcome(cn, sc,writer);
			
		System.out.println("Would you like to continue?");
		System.out.println("Yes \t No");
		String response=sc.next();
			if(response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("Y"))
			{
			System.out.println("OK then!!");
			welcome(cn, sc,writer);
			}
		else if(response.equalsIgnoreCase("no") ||response.equalsIgnoreCase("N"))
				System.out.println("OK! Have a nice day");
			cn.close();
		}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	}
	static void welcome(Connection cn, Scanner sc,FileWriter writer) throws SQLException {
		System.out.println("Menu");
		System.out.println("1. Item Details");
		System.out.println("2. Order Details");
		System.out.println("3. Show Order Details");
		System.out.println("Please enter a choice and we shall get started.");
		int option=sc.nextInt();
		for(int i=0;i<100;i++)
		{
			if(option==1||option==2||option==3)
				break;
			else
			{
				System.out.println("Please enter a valid choice");
				option=sc.nextInt();
			}
		}
		if(option==1)
			ItemDetails(cn,sc,writer);
		else if(option==2)
			OrderDetails(cn, sc,writer);
		else if(option==3)
			OrderDetails(cn,sc,writer);
	}
	static void ItemDetails(Connection cn, Scanner sc,FileWriter writer) throws SQLException
	{
		Item item=new Item();
		System.out.println("What would you like to do?");
		System.out.println("1. Add an item");
		System.out.println("2. Modify an item");
		System.out.println("3. Search for an item");
		System.out.println("4. Delete an item");
		int response=sc.nextInt();
		if(response==1)
			item.addItem(cn,sc,writer);
		else if(response==2)
		{
			System.out.println("Enter item number of modified product");
			int itemno=sc.nextInt();
			System.out.println("Enter new description for product");
			String description=sc.next();
			item.modifyItem(cn, itemno, description,writer);
		}
		else if(response==3)
			{
			item.searchItem(cn, sc);
			}
		else if(response==4)
			{
			System.out.println("Enter item number of product to be deleted.");
			int itemno=sc.nextInt();
			item.deleteItem(cn, itemno,writer);
			}
		else
			for(int i=0;i<100;i++)
			{
				if(response==1||response==2||response==3||response==4)
					break;
				else
				{
					System.out.println("Please enter a valid choice");
					response=sc.nextInt();
				}
			}
	}
	static void OrderDetails(Connection cn, Scanner sc,FileWriter writer) throws SQLException
	{
		Order order=new Order();
		System.out.println("What would you like to do?");
		System.out.println("1. Search for an item");
		System.out.println("2. Add an item to your orders");
		System.out.println("3. View orders");
		int response=sc.nextInt();
		if(response==1)
		{
			System.out.println("Please enter the item number and the quantity you wish to search: ");
			int itemno=sc.nextInt();
			int qty=sc.nextInt();
			System.out.println("Please enter customer Number");
			String custNo=sc.next();
			Order.searchItem(cn, sc,itemno,qty,custNo,writer);
		}
		else if(response==2)
		{
			System.out.println("Please enter item number");
			int itemno=sc.nextInt();
			System.out.println("Please enter quantity");
			int qty=sc.nextInt();
			System.out.println("Please enter customer number");
			String custNo=sc.next();
			order.addOrder(cn, itemno, qty, custNo,writer);
		}
		else if(response==3)
		{System.out.println("How would you like to see the orders?");
		System.out.println("1. All at once");
		System.out.println("2. One by one");
		int option=sc.nextInt();
		if(option==1)
			order.showAllOrders(cn);
		else if(option==2)
			{
			System.out.println("Enter order number of order you wish to see");
			int orderNo=sc.nextInt();
			order.showSpecificOrder(cn, orderNo);
			}
		
		else
			for(int i=0;i<100;i++)
			{
				if(option==1||option==2)
					break;
				else
				{
					System.out.println("Please enter a valid choice");
					option=sc.nextInt();
				}
			}
		}
	}
}
