package csc232.depauwty.model.GUITest;

import java.util.ArrayList;

/*Keeps track of the current location, the player inventory, number of moves take, and other
 * 
 */
public class GameState
{
   //Creates the inventory
   public ArrayList<Item> itemsInInventory = new ArrayList<Item>(); //ArrayList for the Items the user has in its inventory   
   public ArrayList<ContainerItem> containersInInventory = new ArrayList <ContainerItem>();//ArrayList for the containerItem the user has in its inventory
   public Location currentLocation;//keep track of the current location
   public int moves = 0; //Keep tracks of movement
   
   public GameState()
   {
   }
   //Changes the currentlocation
   public void presentLocation(Location temp)
   {
      currentLocation = temp;
   }
   //Keep track of the movement
   public void addingMoves()
   {
      moves ++;
   }
   //Return the number of Moves
   public int getMoves()
   {
      return moves;
   }
   //Adds item to arrayList
   public void addItem(Item temp)
   {
      itemsInInventory.add(temp);
   }
   //Add container to arrayList
   public void addContainer(ContainerItem temp)
   {
      containersInInventory.add(temp);
   }
   //Removes item from arraylist
   public void removeItem(String shortName)
   {
      for(int i = 0; i < itemsInInventory.size() ; i++) //Checks the whole array of items
      {
         if(shortName.equals(itemsInInventory.get(i).getShortName().toLowerCase()))
         {
            itemsInInventory.remove(i);//Removes items if it matches with the shortName
         }
      }
   }
   public String getInventory() //Print what is in the inventory
   {
      String listDescription = "";
      boolean empty = true;
      if (itemsInInventory.size() != 0)
      {
         for(int i = 0; i < itemsInInventory.size();i++)
         {
            listDescription += itemsInInventory.get(i).getShortName() + "\n";
         }
         empty = false;
      }
      if (containersInInventory.size() != 0)
      {
         for(int i = 0; i < containersInInventory.size();i++)
         {
            listDescription += containersInInventory.get(i).getShortName() + "\n";
         }
         empty = false;
      }
      if (empty == true) //Print the message if the container is empty
      {
         listDescription += ("Inventory is currently empty. \n");
      }
      return listDescription;
   }
 }

