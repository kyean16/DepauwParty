package csc232.depauwty.model;

import java.util.ArrayList;


/**
 ** The Item class models a basic adventure game item. For now, it contains fields for the item's
 ** shortName, its type and its description. This class also provides a basic means for accessing 
 ** these fields and storing values for them. 
 ** 
 ** @author Michael Davidson not really
 ** @version 1
 ** 
 **/


public class Item {
   
   private String shortName;
   private String type;
   private String description;
   private boolean deadly;
   public ArrayList <String> requirements = new ArrayList<String>();
   private String deadlyDescription; 
   
   public Item(String shortName, String type, String description, boolean deadly)
   {
      this.shortName = shortName;
      this.type = type;
      this.description = description;
      this.deadly = deadly;
   }
   
   public void addtoRequirements(ArrayList<String> requirement)
   {
      this.requirements = requirement;
   }
   public void setDeadlyDescription(String temp)
   {
      deadlyDescription = temp; 
   }
   
   public String getDeadlyDescription()
   {
      return deadlyDescription; 
   }
   public void setShortName(String shortName) // mutator method for the shortName field
   {
      this.shortName = shortName;
   }
   
   public String getShortName() // accessor method for the shortName field
   {
      return shortName;
   }
   
   public void setType(String type)
   {
      this.type = type;
   }
   
   public String getType()
   {
      return type;
   }
   
   public void setDescription(String description)
   {
      this.description = description;
   }
   
   public String getDescription()
   {
      return description;
   }
   public boolean getDeadly()
   {
      return deadly;
   }
   public String toString() // prints out the item's shortName, it's type, and it's description.
   {
      return "Item:" + "\n \t" + "shortName: " + shortName + "\n \t" + "type: " 
               + type + "\n \t" + "description: " + description; 
   }
   
   
   


}