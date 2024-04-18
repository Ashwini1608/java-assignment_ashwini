package JDBC_spring.ashwini_assignment;



import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class Main_container {
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your choice: ");
        System.out.println("1. Insert");
        System.out.println("2. Delete");
        System.out.println("3. Update");
        
       

        int choice = sc.nextInt();
        sc.nextLine(); 

        switch (choice) {

            case 1:
                String configFile = "maven_project/spring_jdbc/ApplicationConfigurationFile.xml";
                ApplicationContext context = new ClassPathXmlApplicationContext(configFile);
                JdbcTemplate template = (JdbcTemplate)context.getBean("template");

              

                System.out.println("Enter the value of Name");
                String name_data = sc.nextLine();
                
                System.out.println("Enter the value of phone number");
                int number_data = sc.nextInt();

                System.out.println("Enter the value of Address");
                String address_data = sc.nextLine();

                String query = "insert into student values(?,?,?)";
                int response = template.update(query, name_data, number_data, address_data);
                

                System.out.println(response + " rows affected");
                System.out.println("Data Inserted");
               // continue;
               // break;
               
                break;
                
                
            case 2: 
            	 String configFile1 = "maven_project/spring_jdbc/ApplicationConfigurationFile.xml";
                 ApplicationContext context1 = new ClassPathXmlApplicationContext(configFile1);
                 JdbcTemplate template1 = (JdbcTemplate)context1.getBean("template1");

            	System.out.println("Enter the value of name");
                String del_id = sc.nextLine();
                sc.nextLine();
                
                String del_query="delete from student where name=?";
                int del_response= template1.update(del_query,del_id);
                
                System.out.println(del_response + " rows affected");
                System.out.println("Data Deleted");
            	//break;
               
                break;
            	
            case 3:
            	System.out.println("Which field you want to update:");
            	System.out.println("\n1: phone number\n2: Address");
            	int ch = sc.nextInt();
                
                String configFile2 = "maven_project/spring_jdbc/ApplicationConfigurationFile.xml";
                ApplicationContext context2 = new ClassPathXmlApplicationContext(configFile2);
                JdbcTemplate template2 = (JdbcTemplate)context2.getBean("template2");
                
                switch(ch) {
                case 1:
                	System.out.println("Enter the name whose phone number you want to update?");
                	String id_value=sc.nextLine();
                	sc.nextLine(); // Consume the newline character
                	
                	System.out.println("Enter the new number:");
                	int number_value=sc.nextInt();
                	
                	String query2 = "UPDATE student SET phone = ? WHERE name = ?";
                	
                	int response3 = template2.update(query2, number_value, id_value);
                	System.out.println(response3 + " rows affected");
                    System.out.println("Data Updated");
                   // table();
                    break;
                	
                case 2:
                	System.out.println("Enter the name whose address you want to update?");
                	String id_value1=sc.nextLine();
                	sc.nextLine(); // Consume the newline character
                	
                	System.out.println("Enter the new address:");
                	String address_value=sc.nextLine();
                	
                	String query3 = "UPDATE student SET address = ? WHERE name = ?";
                	
                	int response4 = template2.update(query3, address_value, id_value1);
                	System.out.println(response4 + " rows affected");
                    System.out.println("Data Updated");
                    //table();
                    break;
                }
                
                
            	break;
         

            	
            default:
            System.out.println("Invalid choice");
        }

        sc.close();
    }

}
