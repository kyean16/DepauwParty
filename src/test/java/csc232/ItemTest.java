package csc232;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import csc232.depauwty.model.Item;

public class ItemTest
{
   
   @Before
   public void setUp() throws Exception
   {
      item = new Item("sword", "weapon", "The Sword of Gryffindor", false);
   }

   @Test
   public void testSetShortName()
   {
      item.setShortName("sword");
      assertTrue(item.getShortName() == "sword");
   }
   
   @Test
   public void testGetShortName()
   {
      assertEquals("sword", item.getShortName());
   }

   @Test
   public void testSetType()
   {
      item.setType("weapon");
      assertTrue(item.getType() == "weapon");
   }
   
   @Test
   public void testGetType()
   {
      assertEquals("weapon", item.getType());
   }
   
   @Test
   public void testSetDescription()
   {
      item.setDescription("The Sword of Gryffindor");
      assertTrue(item.getDescription() == "The Sword of Gryffindor");
   }

   @Test
   public void testGetDescription()
   {
      assertEquals("The Sword of Gryffindor", item.getDescription());
   }

   @Test
   public void testToString()
   {
      assertEquals("Item:" + "\n \t" + "shortName: " + "sword" + "\n \t" + "type: " 
               + "weapon" + "\n \t" + "description: " + "The Sword of Gryffindor" , item.toString());
      
   }
   
   private Item item;

}