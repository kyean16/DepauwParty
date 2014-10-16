package csc232;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import csc232.depauwty.model.ContainerItem;
import csc232.depauwty.model.Item;
import csc232.depauwty.model.Location;

public class GameStateTest
{

   @Before
   public void setUp() throws Exception
   {
      itemsInInventory = new ArrayList<Item>();
      containersInInventory = new ArrayList <ContainerItem>();
      moves = 0;
      money = new Item("Money", "U.S. Dollars", "Five U.S. Dollars", false);
      wallet = new ContainerItem("Wallet", "Equipment", "A small item used to carry cards and money", false );
      humbertRoom = new Location("Humbert Hall dorm room", "Humbert Hall - In a first-year dorm room. Directions open: West");
      currentLocation = humbertRoom;
   }
   
   @Test
   public void testPresentLocation()
   {
      assertTrue(currentLocation.getName().equals("Humbert Hall dorm room"));
   }
   
   @Test
   public void testGetMoves()
   {
      assertEquals(0, moves);
   }
   
   @Test
   public void testAddingMoves()
   {
      moves++;
      assertTrue(moves == 1);      
   }
   
   @Test
   public void testAddItem()
   {
      itemsInInventory.add(money);
      assertTrue(itemsInInventory.size() == 1);
   }
   
   @Test
   public void testAddContainer()
   {
      containersInInventory.add(wallet);
      assertTrue(containersInInventory.size() == 1);
   }
   
   @Test
   public void testRemoveItem()
   {
      itemsInInventory.add(money);
      itemsInInventory.remove(money);
      assertTrue(itemsInInventory.size() == 0);      
   }
   
   @Test
   public void testGetInventory()
   {
      itemsInInventory.add(money);
      assertEquals("Money", itemsInInventory.get(0).getShortName());
      containersInInventory.add(wallet);
      assertEquals("Wallet", containersInInventory.get(0).getShortName());
      
   }

   private ArrayList<Item> itemsInInventory;   
   private ArrayList<ContainerItem> containersInInventory;
   private Location currentLocation;
   private int moves;
   private Item money;
   private ContainerItem wallet;
   private Location humbertRoom;


}
