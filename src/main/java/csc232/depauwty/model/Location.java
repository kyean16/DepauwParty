package csc232.depauwty.model;
import java.util.ArrayList;

/**
 ** The Location class contains fields for the item's shortName, its description,
 ** and an array list of items in the location. This class also provides a basic means for accessing 
 ** these fields and storing values for them, as well as methods for adding an item to the location, 
 ** retrieving an item given its shortName, getting a count of the number of items in the location, 
 ** and returning an item given its index in the collection.
 ** 
 ** @author Michael Davidson
 ** @author Cody Watson
 ** @author Kevin Yean
 ** @version 1
 ** 
 **/

public class Location {
   
   private String name;
   private String description;
   public ArrayList<Item> items = new ArrayList<Item>(); //ArrayList for Item objects
   public ArrayList<ContainerItem> container = new ArrayList <ContainerItem>(); //ArrayList of ContainerItem objects
   //public HashMap<String, Location> map = new HashMap<String, Location>();
   public ArrayList <String> requirements = new ArrayList<String>();
   
   private Location north;
   private Location south;
   private Location east;
   private Location west;
   
   //Constructor
   public Location(String name, String description)
   {
      this.name = name;
      this.description = description;
   }
   
   public void addtoRequirements(ArrayList<String> requirement)
   {
      this.requirements = requirement;
   }
   
   public void setName(String name) // mutator method for the name field
   {
      this.name = name;
   }
   
   public String getName() // accessor method for the name field
   {
      return name;
   }
   
   public void setDescription(String description)
   {
      this.description = description;
   }
   
   public String getDescription()
   {
      return description;
   }
   
   // Adds item to array list of items.
   public void addItem(Item temp)
   {
      items.add(temp);
   }
   //Add containers to arrayList of items.
   public void addContainer(ContainerItem temp)
   {
      container.add(temp);
   }
   
   // Prints all the shortNames of the items in the array list of items.
   public String getAllItemsNames()
   {
      String output = "";
      for (int i = 0; i < items.size(); i++) //Print items
      {
        output += (items.get(i).getShortName() + "\n");
      }
      for (int i = 0; i <container.size(); i++)//Print ArrayList
      {
        output += (container.get(i).getShortName () + "\n");
      }
      return output;   
   }
   
   // Returns the Item given its shortName. If item isn't found, returns Null.
   public Item itemRetrieval(String shortName)
   {
     
     for(int i = 0; i < items.size(); i++)
     {
        if(items.get(i).getShortName().equals(shortName))
        {
           return items.get(i);
        }
     } 
     return null;
   }
   
   
   // returns the number of items in the array list of items.
   public int getNumberItems()
   {
      return items.size();
   }
  
   // Returns the Item in the number index given by the parameter. If there
   // is not an item in the given index, returns Null.
   public Item itemIndex(int index)
   {
      if (items.size() >= index)
      {      
         return items.get(index);
      }
      else
      {
         return null;
      }
   }
   
   //Set ups the direction in which the location may go in
   public void setNeighbors(Location north, Location east, Location south, Location west)
   {
      this.north = north;
      this.east = east;
      this.south = south;
      this.west = west;
   }
   
   //Check if location exists or not given the direction
   public Location checkNeighbors( String direction)
   {
      if(direction == "north")
      {
         return north;
      }
      else if(direction == "south")
      {
         return south;
      }
      else if(direction == "west")
      {
         return west;
      }
      else if(direction == "east")
      {
         return east;
      }
      else
      {
         return null;
      }
   }
   public boolean requiredKey(ArrayList <Item> keys, ArrayList <ContainerItem> keyStorage)
   {
      if(this.requirements.size() > 0)
      {
         int required = 0;
         for(int a = 0 ; a < this.requirements.size(); a++ )
         {
            for(int b = 0; b < keys.size(); b++) //Checks for items arrayList
            {
               if (this.requirements.get(a) == keys.get(b).getShortName())
               {
                  required ++;
               }
            }
            for (int c = 0 ; c < keyStorage.size(); c++ ) //Checks for items in the containers
            {
               for (int d = 0 ; d < keyStorage.get(c).listOfItems.size() ; d++)
               {
                  if (this.requirements.get(a) == keyStorage.get(c).listOfItems.get(d).getShortName())
                  {
                     required ++;
                     
                  }
               }
            }
         }
         if (required !=  this.requirements.size())
         {
           return false;
         }
         else if (required == this.requirements.size())
         {
           return true;
         }
      }
      return true;
   }
}