package csc232;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import csc232.depauwty.model.ContainerItem;
import csc232.depauwty.model.Item;

public class ContainerItemTest
{
  
     
     @Before
     public void setUp() throws Exception
     {
        listOfItems = new ArrayList<Item>();
        listOfContainers = new ArrayList <ContainerItem>();
        item = new Item("sword", "weapon", "The Sword of Gryffindor", false);
        container = new ContainerItem("case", "holder", "Allows the Sword of Gryffindor to be easily carried", false);
     }
   

   @Test
   public void testAddItem()
   {
      listOfItems.add(0, item);  
      assertTrue(listOfItems.size() == 1);
      
   }
   @Test
   public void testaddContainer()
   {
      listOfContainers.add(0, container);  
      assertTrue(listOfContainers.size() == 1);
   }
   @Test 
   public void testGetNumberItems()
   {
      listOfItems.add(item);
      listOfItems.add(item);
      assertTrue(listOfItems.size() == 2);
   }
   @Test
   public void testRemoveItem()
   {
      listOfItems.add(item);
      listOfItems.add(item);
      listOfItems.remove(item);
      assertTrue(listOfItems.size() == 1);
   }
   @Test
   public void testGetDescription()
   {
      assertEquals("The Sword of Gryffindor", item.getDescription());
   }
  
   private ArrayList<Item> listOfItems = new ArrayList<Item>(); //ArrayList for the Items the user has in its inventory   
   private ArrayList<ContainerItem> listOfContainers = new ArrayList <ContainerItem>();//ArrayList for the containerItem the user has in its inventory
   private Item item;
   private ContainerItem container; 
}
