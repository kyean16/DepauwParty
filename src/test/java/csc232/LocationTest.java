package csc232;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import csc232.depauwty.model.Item;
import csc232.depauwty.model.Location;

public class LocationTest
{

   @Before
   public void setUp() throws Exception
   {
     loc = new Location("Greencastle, Indiana" , "In the middle of nowhere");
     item = new Item("sword", "weapon", "The Sword of Gryffindor", false);
     item2 = new Item("steak", "consumable", "meat", false);
     loc.addItem(item);
     loc.addItem(item2);
     
   }

   @Test
   public void testSetName()
   {
      loc.setName("Greencastle, Indiana");
      assertTrue(loc.getName() == "Greencastle, Indiana");
   }
   
   @Test
   public void testGetName()
   {
      assertEquals("Greencastle, Indiana", loc.getName());
   }
   
   @Test
   public void testSetDescription()
   {
      loc.setDescription("Depauwty");
      assertTrue(loc.getDescription() == "Depauwty");
   }

   @Test
   public void testGetDescription()
   {
      assertEquals("In the middle of nowhere", loc.getDescription());
   }
   @Test
   public void testAddItem()
   {
      assertTrue(loc.getNumberItems() == 2);
   }

   @Test
   public void testGetAllItemsNames()
   {
      assertEquals(item.getShortName(),"sword");
   }
   
   @Test
   public void testItemRetrieval()
   {
      assertEquals(item, loc.itemRetrieval("sword"));
   }

   @Test
   public void testGetNumberItems()
   {
      assertEquals(2, loc.getNumberItems());
   }

   @Test
   public void testItemIndex()
   {
      assertEquals(item2, loc.itemIndex(1));
   }
   
   private Location loc;
   private Item item;
   private Item item2;

}