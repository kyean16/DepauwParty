package csc232.depauwty.model;

import java.util.ArrayList;

public class ContainerItem extends Item
{
   public ArrayList<Item> listOfItems = new ArrayList<Item>(); //ArrayList for the Items the user has in its inventory   
   public ArrayList<ContainerItem> listOfContainers = new ArrayList <ContainerItem>();//ArrayList for the containerItem the user has in its inventory
   
   public ContainerItem(String shortName, String type, String description, boolean deadly)
   {
      super (shortName,type,description,deadly);
      
   }   
   
   //Adds item to arrayList
   public void addItem(Item temp)
   {
      listOfItems.add(temp);
   }
   //Add container to arrayList
   public void addContainer(ContainerItem temp)
   {
      listOfContainers.add(temp);
   }
   //Removes item from arraylist
   public void removeItem(String shortName)
   {
      for(int i = 0; i < listOfItems.size() ; i++) //Checks the whole array of items
      {
         if(shortName.equals(listOfItems.get(i).getShortName().toLowerCase()))
         {
            listOfItems.remove(i);//Removes items if it matches with the shortName
         }
      }
   }
   // returns the number of items in the array list of items.
   public int getNumberItems()
   {
      return listOfItems.size();
   }
  
   public String getDescription(String temp) //Overrides getDescription method and print the list of items in the container
   {
      String listDescription = "";
      boolean empty = true; //Variable for state of container
      if (temp != "inventory") //If temp variable is not inventory it prints a description of it.
      {
         listDescription = super.getDescription() +"\n" ;
      }
      if (listOfItems.size() != 0)
      {
         for(int i = 0; i < listOfItems.size();i++)
         {
            listDescription += listOfItems.get(i).getShortName() + "\n";
         }
         empty = false;
      }
      if (listOfContainers.size() != 0)
      {
         for(int i = 0; i < listOfContainers.size();i++)
         {
            listDescription += listOfContainers.get(i).getShortName() + "\n";
         }
         empty = false;
      }
      if (empty == true) //Print the message if the container is empty
      {
         listDescription += (this.getShortName() + " is currently empty. \n");
      }
      return listDescription;
   }
}
