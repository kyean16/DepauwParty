package csc232.depauwty.model;

import java.util.ArrayList;

public class DefaultGame
{
   //Location Objects
   public Location humbertRoom = new Location("Humbert Hall dorm room", "Humbert Hall - In a first-year dorm room. Directions open: West");
   public Location humbertHallway = new Location("Humbert Hall hallway", "In Humbert Hall's hallway - 2nd floor. Directions open: East and North");
   public Location humbertStaircase = new Location("Humbert Hall staircase", "In Humbert Hall's staircase. Directions open: South and North");
   public Location gcpa = new Location("GCPA", "GCPA - The music building on campus. Directions open: South, North and West");
   public Location julian = new Location("Julian Math and Science Building", "Julian Center - The math and science building on campus. Directions open: East");
   public Location hub = new Location("The Hub", "The Hub - A building for food, Public Safety, and other things. Directions open: South and East");
   public Location ubbenQuad = new Location("Ubben Quad", "Ubben Quad - A quad of residence halls for students. Directions open: North, East, South and West");
   public Location fraternity = new Location("Campus Fraternity", "A fraternity on campus hosting a social event. Directions open: East");
   public Location publicSafety = new Location("Public Safety", "Public Safety - You've been caught by Public Safety. They have escorted you back to your " +
                                      "room in Humbert Hall. The game has restarted.");
   public   Location safeRide = new Location("Safe Ride", "Safe Ride - You won! Safe Ride has taken you back to Humbert to enjoy a nice night of sleep.");
   
   //This Location object will be the permanent location the player is in
   Location currentLocation = humbertRoom;//Starting location is current room
   
   public DefaultGame()
   {
   
    //This sets up the neighbors of the location using maps
    humbertRoom.setNeighbors(null, null, null, humbertHallway);
    {
       ArrayList<String> temp = new ArrayList<String>();
       temp.add("Keys");
       humbertRoom.addtoRequirements(temp);
    }
    humbertHallway.setNeighbors(humbertStaircase, humbertRoom, null, null);
    {
       ArrayList<String> temp = new ArrayList<String>();
       temp.add("Keys");
       humbertHallway.addtoRequirements(temp);
    }
    humbertStaircase.setNeighbors(gcpa, null, humbertHallway, null);
    gcpa.setNeighbors(hub, null, humbertStaircase, julian);
    julian.setNeighbors(null, gcpa, null, null);
    hub.setNeighbors(null, ubbenQuad, gcpa, null);
    {
       ArrayList<String> temp = new ArrayList<String>();
       temp.add("IDcard");
       hub.addtoRequirements(temp);
    }
    
    ubbenQuad.setNeighbors(fraternity, publicSafety, publicSafety, hub);
    fraternity.setNeighbors(null, safeRide, null, null);
    {
       ArrayList<String> temp = new ArrayList<String>();
       temp.add("Bobby_Bobbington");
       fraternity.addtoRequirements(temp);
    }
    publicSafety.setNeighbors(null, null, null, humbertHallway);
    
    //Creates ContainersItem objects
    ContainerItem wallet = new ContainerItem("Wallet", "Equipment", "A small item used to carry cards and money", false );
    ContainerItem backpack = new ContainerItem("Backpack", "Equipment", "A normal sized backpack used to carry items", false);
    
    //Creates items objects
    Item keys = new Item("Keys", "Keys", "It allows student's to enter the room after leaving", false);
    Item idCard = new Item("IDcard", "Card", "The student's ID cash", false); 
    Item iPhone = new Item("iPhone", "Phone", "A white iPhone 5", false);
    Item money = new Item("Money", "U.S. Dollars", "Five U.S. Dollars", false);
    Item harp = new Item("Harp", "Instrument","A golden harp standing alone in the middle of Kresge with a spotlight set on it, ready for you to play a tune.", true);
          harp.setDeadlyDescription("A beautiful golden harp. When you took the harp, the Ghost of Dead Professors appeared and killed you." +
                                    "The game has restarted."); 
    Item coffee = new Item("Coffee", "Consumable", "12 oz. of Cafe Allegro's finest coffee for $5.00.", false);
    {
       ArrayList<String> temp1 = new ArrayList<String>();
       temp1.add("Money");
       coffee.addtoRequirements(temp1);
    }
    Item grenade = new Item("Grenade", "Explosive", "A prohibited item your roommate brought from home.", true);
          grenade.setDeadlyDescription("When you took this grenade, the pin fell out and Humbart Hall ceased to exist. " +
                                       "Many years later the building was rebuilt and you restarted the game.");
    Item cat = new Item("Cat", "Infection", "Stray cats are a problem on campus - Your RA told you they are prohibited in the building", true);
          cat.setDeadlyDescription("You're allergic to cats. By picking up the cat, you got sick and died. The game has restarted.");
    Item friend = new Item("Bobby_Bobbington", "Friend", "Necessary to get into the fraternity.", false);
    Item professor = new Item("Scotty_Theodore", "Professor", "A mentor during your computer science education.", true);
          professor.setDeadlyDescription("You were assigned a text-based project, and after reading the instructions you died from confusion. The game has restared.");
    Item gcb = new Item("GCB", "Consumable", "A half-eaten garlic cheeseburger from Marvin's. Use it for increased satisfaction.", false);
    Item medal = new Item("Gold_Medal", "Prize", "The prize you receive for surviving DePauwty!", false);
    
    //Adds containers and items in arrayLists of HumbertRoom location
    humbertRoom.addContainer(wallet);
    humbertRoom.addContainer(backpack);
    humbertRoom.addItem(money); 
    humbertRoom.addItem(idCard);
    humbertRoom.addItem(iPhone);
    humbertRoom.addItem(grenade);
    humbertRoom.addItem(keys);
    
    //Adds container and items in arrayList of HumbertHall location
    humbertHallway.addItem(cat);

    //Adds container and items in arrayList of GCPA location
    gcpa.addItem(harp);
    gcpa.addItem(coffee);
    
    //Adds container and items in arrayList of Julian location
    julian.addItem(friend);
    julian.addItem(professor);
    julian.addItem(gcb);
    
    //Adds container and items in arrayList of fraternity location
    fraternity.addItem(medal);   
   
   }
}

