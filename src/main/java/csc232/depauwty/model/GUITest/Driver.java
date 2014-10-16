package csc232.depauwty.model.GUITest;

import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;
import java.io.IOException;

/**
 ** The Driver class creates a location and adds items to it. Then it goes into an infinite loop
 ** that gets commands from the user. The commands are read line by line and then processed. For example,
 ** if the user types in the command "quit", the loop breaks and the program ends.
 **
 ** @author Michael Davidson
 ** @author Cody Watson
 ** @author Kevin Yean
 ** @version 1
 **
 **/

public class Driver {
   
      private Reader reader;
      private Writer writer;
      
      public Driver( Reader r, Writer w)
      {
         reader = r;
         writer = w;
      }
      
      public void run() throws IOException
      {
         Scanner in = new Scanner(reader);
         PrintWriter out = new PrintWriter( writer );
         
        
           DefaultGame game = new DefaultGame(); //Initializes stuff
           GameState state = new GameState();//Initializes the player
        
         //First Message
         out.println("Hello! Welcome to Surviving DePauwty, a text-based adventure. Please start by typing 'help' or 'h' to find out more about the " +
         		       "objective of the \ngame and the possible commands to input.");
         printPrompt();
         
         while(in.hasNextLine())  // infinite command line loop
         {
            String line = in.nextLine();
            
            line = line.trim().toLowerCase();  //deletes unneeded spaces at beginning and end of linesListOfItems and converts to lower case
            String[] words = line.split (" ");
            
            if(line.equals("help") || line.equals("h"))
            {
               
               try
               {
                  new ProcessBuilder("gedit","DePauwty Help File.txt").start();
               
               } catch (IOException ex)
               {
                    ex.printStackTrace();
               }
            }
            
            else if(words.length > 1 && words[0].equals("examine")) //Checks to see if first word is "examine"
            { 
              boolean itemPresent = false;  // keeps track whether or not item is present
              String shortName = words[1];  // makes second word in array equal to shortName\
              
              for (int i = 0; i< game.currentLocation.items.size() ; i++) //This checks the list of items in the location
              {
                 if (game.currentLocation.items.get(i).getShortName().toLowerCase().equals(shortName)) // if the shortName equals getShortName
                 {
                    out.println(game.currentLocation.items.get(i).getDescription());  //print out the item's description.
                    itemPresent = true;
                 }
              }
              if (itemPresent == false) //This checks the list of items and containers in the location
              {
                 for (int i = 0; i < game.currentLocation.container.size(); i++)
                    if (game.currentLocation.container.get(i).getShortName().toLowerCase().equals(shortName))
                    {
                       out.print(game.currentLocation.container.get(i).getDescription("not inventory"));
                       itemPresent = true;
                    }
              }
              if (itemPresent == false) //This checks the items and container in the inventory
              {
                 for (int i = 0; i < (state).itemsInInventory.size() ; i++)//Goes throught the whole inventory
                 {
                    if (state.itemsInInventory.get(i).getShortName().toLowerCase().equals(shortName)) // if the shortName equals getShortName
                    {
                         out.println((state).itemsInInventory.get(i).getDescription());
                         itemPresent = true;
                    }
                 }
                for (int i = 0; i < state.containersInInventory.size() ; i++)//Goes throught the whole container
                {
                    if (state.containersInInventory.get(i).getShortName().toLowerCase().equals(shortName)) // if the shortName equals getShortName
                    {
                       out.print(state.containersInInventory.get(i).getDescription("not inventory"));
                       itemPresent = true;
                    }
                }
              }
              if (itemPresent == false) //Finally if it can find anything it does this.
              {
                 out.println("I can't examine that. Try another examine command.");
              }
              out.flush();
            }
           
////////////////////////////////////////////////////////////////////////////////////////////////
            // Following code is for taking and putting simple Item objects
            
            else if(words.length == 2 && words[0].equals("take")) // Take
            {
               boolean itemPresent = false;  // keeps track whether or not item is present
               String shortName = words[1];  // makes second word in array equal to shortName
               for (int i = 0; i< game.currentLocation.items.size() ; i++) //Goes throught the whole location item arraylist
               {
                  if (game.currentLocation.items.get(i).getShortName().toLowerCase().equals(shortName)) // if the shortName equals getShortName of the item
                  {
                     if (game.currentLocation.items.get(i).getDeadly())
                     {
                        out.println (game.currentLocation.items.get(i).getDeadlyDescription());
                        itemPresent = true;
                        run();
                     }
                     else if(game.currentLocation.items.get(i).requirements.size() > 0)
                     {
                        int required = 0;
                        for(int a = 0 ; a < game.currentLocation.items.get(i).requirements.size(); a++ )
                        {
                           for(int b = 0; b < state.itemsInInventory.size(); b++)
                           {
                              if (game.currentLocation.items.get(i).requirements.get(a) == state.itemsInInventory.get(b).getShortName())
                              {
                                 required ++;
                                 out.println(state.itemsInInventory.get(b).getShortName() + " has been removed from the inventory.");
                                 state.itemsInInventory.remove(b);
                                 
                              }
                           }
                           for (int c = 0 ; c < state.containersInInventory.size(); c++ )
                           {
                              for (int d = 0 ; d < state.containersInInventory.get(c).listOfItems.size() ; d++)
                              {
                                 if (game.currentLocation.items.get(i).requirements.get(a) == state.containersInInventory.get(c).listOfItems.get(d).getShortName())
                                 {
                                    required ++;
                                    out.println(state.containersInInventory.get(c).listOfItems.get(d).getShortName() + " has been removed from the container.");
                                    state.containersInInventory.get(c).listOfItems.remove(d);
                                    
                                 }
                              }
                           }
                        }
                        if (required !=  game.currentLocation.items.get(i).requirements.size())
                        {
                           out.println("I am sorry, you are missing an item to allow your requested action." );
                           itemPresent = true;
                        }
                        else if (required == game.currentLocation.items.get(i).requirements.size())
                        {
                           state.addItem(game.currentLocation.items.get(i));
                           out.println(game.currentLocation.items.get(i).getShortName() + " taken from " + game.currentLocation.getName()); // Displays from where the item was taken
                           game.currentLocation.items.remove(i);//removes the items from the location
                           itemPresent = true;//Present
                        }
                     }
                     else
                     {
                        state.addItem(game.currentLocation.items.get(i));
                        out.println(game.currentLocation.items.get(i).getShortName() + " taken from " + game.currentLocation.getName()); // Displays from where the item was taken
                        game.currentLocation.items.remove(i);//removes the items from the location
                        itemPresent = true;//Present
                     }
                  }
               }
               if (itemPresent == false) //This checks the list of containers
               {
                  for (int i = 0; i < game.currentLocation.container.size(); i++)
                     if (game.currentLocation.container.get(i).getShortName().toLowerCase().equals(shortName)) // if the shortName equals getShortName of the item
                     {
                        state.addContainer(game.currentLocation.container.get(i));  //adds the given item to the inventory
                        out.println(game.currentLocation.container.get(i).getShortName() + " taken from " + game.currentLocation.getName()); // Displays from where the item was taken
                        game. currentLocation.container.remove(i);//removes the items from the location
                        itemPresent = true;//Present
                     }
               }
               if (itemPresent == false)//Not present
               {
                  out.println("I can't take that. Try another take command.");
               }
               out.flush();
            }
            
            else if(words.length == 2 && words[0].equals("put")) //Put
            {
               boolean itemPresent = false;  // keeps track whether or not item is present
               String shortName = words[1];  // makes second word in array equal to shortName
               for (int i = 0; i < state.itemsInInventory.size() ; i++)//Goes throught the whole inventory
               {
                  if (state.itemsInInventory.get(i).getShortName().toLowerCase().equals(shortName)) // if the shortName equals getShortName
                  {
                    game.currentLocation.addItem((state).itemsInInventory.get(i));  //adds given item to location and removes it from inventory
                    out.println((state).itemsInInventory.get(i).getShortName() + " put into " + game.currentLocation.getName());//Displays where the item was put into
                    state.itemsInInventory.remove(i);//Removes the item from the inventory
                    itemPresent = true;//Present
                  }
               }
               for (int i = 0; i < state.containersInInventory.size(); i++)//Goes throught the whole inventory
               {
                  if (state.containersInInventory.get(i).getShortName().toLowerCase().equals(shortName)) // if the shortName equals getShortName
                  {
                     game.currentLocation.addContainer((state).containersInInventory.get(i));  //adds given item to location and removes it from inventory
                    out.println((state).containersInInventory.get(i).getShortName() + " put into " + game.currentLocation.getName());//Displays where the item was put into
                    (state).containersInInventory.remove(i);//Removes the item from the inventory
                    itemPresent = true;//Present
                  }
               }
               if (itemPresent == false)//Not present
               {
                  out.println("I can't put that. Try another put command.");
               }
               //out.flush();
            }
            
            else if(line.equals("inventory") || line.equals("i")) //Inventory display
            {
               out.print(state.getInventory());
               out.flush();
            }
 ///////////////////////////////////////////////////////////////////////
            //Take and put for containers
            else if(words.length == 4 && words[0].equals("take") && words [2].equals("from"))// Take ____ from ______
            {
               boolean itemPresent = false;  // keeps track whether or not item is present
               String shortName = words[1];  // makes second word in array equal to shortName
               String containerName = words[3];//make fourth word in array equal to container
               for(int i = 0; i <  state.containersInInventory.size(); i++)//Goes through the whole array of containers
               {
                  if(containerName.equals(state.containersInInventory.get(i).getShortName().toLowerCase())) //If container name matches
                  {
                     for(int x = 0; x <  state.containersInInventory.get(i).listOfItems.size(); x++) //Looks through the items in t//      {
//                      
                    //  he container and if found puts in the inventory and removes it from container
                     {
                        if (shortName.equals(state.containersInInventory.get(i).listOfItems.get(x).getShortName().toLowerCase()))
                        {
                           state.addItem(state.containersInInventory.get(i).listOfItems.get(x));
                           state.containersInInventory.get(i).removeItem(shortName);
                           out.println(shortName + " has been removed from " + containerName + "\n" +
                                       shortName + " has been added to the inventory");
                           itemPresent = true;
                        }
                     }
                  }
               }
               if ( itemPresent == false) //Check containers in the location
               {
                  for(int i = 0; i < game.currentLocation.container.size(); i++)
                  {
                     if(containerName.equals(game.currentLocation.container.get(i).getShortName().toLowerCase())) //If container name matches
                     {
                        for(int x = 0; x <  game.currentLocation.container.get(i).listOfItems.size(); x++) //Looks through the items in the container and if found puts in the inventory and removes it from container
                        {
                           if (shortName.equals(game.currentLocation.container.get(i).listOfItems.get(x).getShortName().toLowerCase()))
                           {
                              state.addItem(game.currentLocation.container.get(i).listOfItems.get(x));
                              game.currentLocation.container.get(i).removeItem(shortName);
                              out.println(shortName + " has been removed from " + containerName + "\n" +
                                          shortName + " has been added to the inventory");
                              itemPresent = true;
                           }
                        }
                     }
                  }
               }                  
               if (itemPresent == false)
               {
                  out.println("I can't take that. Try another take command.");
               }
               out.flush();
            }
            else if(words.length == 4 && words[0].equals("put") && words[2].equals("in")) // Put ____ in _______
            {
               boolean itemPresent = false;  // keeps track whether or not item is present
               String shortName = words[1];  // makes second word in array equal to shortName
               String containerName = words[3];
               for(int i = 0; i < state.itemsInInventory.size(); i++) //Goes throught all the items in the inventory
               {
                  if(shortName.equals(state.itemsInInventory.get(i).getShortName().toLowerCase()) && state.itemsInInventory.size() > 0) //Checks if the name in the inventory is present
                  {
                     for(int x = 0; x < state.containersInInventory.size(); x++)
                     {
                        if(containerName.equals(state.containersInInventory.get(x).getShortName().toLowerCase())) // Check for container and if it exists put its in it
                        {
                           state.containersInInventory.get(x).addItem(state.itemsInInventory.get(i));
                           state.removeItem(shortName);
                           out.println(shortName + " has been removed from the inventory \n" +
                                       shortName + " has been added to the " + containerName);
                           itemPresent = true;
                        }
                     }
                  }
               }
               if(itemPresent == false) //Check for containers in location
               {
                  for(int i = 0; i < game.currentLocation.container.size(); i++) //Goes throught all the items in the inventory
                  {
                     if(containerName.equals(game.currentLocation.container.get(i).getShortName().toLowerCase())) //Checks if the name of the container is present
                     {
                        for(int x = 0; x < state.itemsInInventory.size(); x++)
                        {
                           if(shortName.equals(state.itemsInInventory.get(x).getShortName().toLowerCase())) // Check for item and if it exists put its in it
                           {
                              game.currentLocation.container.get(i).addItem(state.itemsInInventory.get(x));
                              state.removeItem(shortName);
                              out.println(shortName + " has been removed from inventory \n" +
                                          shortName + " has been added to the " + containerName);
                              itemPresent = true;
                           }
                        }
                     }
                  }
               }
              if (itemPresent == false)
               {
                  out.println("I can't help you with that. Please try again.");
               }
               out.flush();
            }
             
            //The following code checks if the direction the player asks exist for the currentLocation and if it is the player moves theres.
            else if(line.equals("north") || line.equals("n"))
            {
               if(game.currentLocation.checkNeighbors("north") != null)
               {
                  if(game.currentLocation.checkNeighbors("north").requiredKey(state.itemsInInventory, state.containersInInventory))
                  {
                     game.currentLocation = game.currentLocation.checkNeighbors("north");
                     state.presentLocation(game.currentLocation.checkNeighbors("north"));
                     state.addingMoves();
                     out.println("Location: " + game.currentLocation.getDescription() + "\n" +
                              "Current Moves:" + state.getMoves());
                  }
                  else
                  {
                     out.println ("You seem to be missing an object to continue.");
                  }
               }
               // else if(location.hub.getshortname() == "Hub"
               // call "boollean 
               else
               {
                  out.println("This direction is blocked.");
               }
            }
            else if(line.equals("east") || line.equals("e"))
            {
               if(game.currentLocation.checkNeighbors("east") != null)
               {
                  if(game.currentLocation.checkNeighbors("east") != null)
                  {
                     if(game.currentLocation.checkNeighbors("east").requiredKey(state.itemsInInventory, state.containersInInventory))
                     {
                        game.currentLocation = game.currentLocation.checkNeighbors("east");
                        state.presentLocation(game.currentLocation.checkNeighbors("east"));
                        state.addingMoves();
                        out.println("Location: " + game.currentLocation.getDescription() + "\n" +
                                 "Current Moves:" + state.getMoves());
                        
                        if(game.currentLocation.getName().equals("Public Safety"))
                        {
                           run();
                        }
                        if(game.currentLocation.getName().equals("Safe Ride"))
                        {
                           break;
                        }
                     }
                     else
                     {
                        out.println ("You seem to be missing an object to continue.");
                     }
                  }
               }
               else
               {
                  out.println("This direction is blocked.");
               }
            }
            else if(line.equals("south") || line.equals("s"))
            {
               if(game.currentLocation.checkNeighbors("south") != null)
               {
                  if(game.currentLocation.checkNeighbors("south").requiredKey(state.itemsInInventory, state.containersInInventory))
                  {
                     game.currentLocation = game.currentLocation.checkNeighbors("south");
                     state.presentLocation(game.currentLocation.checkNeighbors("south"));
                     state.addingMoves();
                     out.println("Location: " + game.currentLocation.getDescription() + "\n" +
                              "Current Moves:" + state.getMoves());
                     if(game.currentLocation.getName().equals("Public Safety"))
                     {
                        run();
                     }
                  }
                  else
                  {
                     out.println ("You seem to be missing an object to continue.");
                  }
               }
               else
               {
                  out.println("This direction is blocked.");
               }
            }
            else if(line.equals("west") || line.equals("w"))
            {
               if(game.currentLocation.checkNeighbors("west") != null)
               {
                  if(game.currentLocation.checkNeighbors("west").requiredKey(state.itemsInInventory, state.containersInInventory))
                  {
                     game.currentLocation = game.currentLocation.checkNeighbors("west");
                     state.presentLocation(game.currentLocation.checkNeighbors("west"));
                     state.addingMoves();
                     out.println("Location: " + game.currentLocation.getDescription() + "\n" +
                              "Current Moves:" + state.getMoves());
                  }
                  else
                  {
                     out.println ("You seem to be missing an object to continue.");
                  }
               }
               else
               {
                  out.println("This direction is blocked.");
               }
             }
               
     
  /////////////////////////////////////////////////////////////          
            else if(line.equals("quit") || line.equals("q"))
               break;
            
            else if(line.equals("look") || line.equals("l"))
            {
               out.println(game.currentLocation.getDescription()); //Prints location description
               out.print(game.currentLocation.getAllItemsNames());//Print objects in location in description
               out.flush();
            }
            else
               out.println("I don't know how to do that. Please try another command.");
            out.flush();
            printPrompt();
         }
         out.flush();
         in.close();
         out.close();
      }
      
      public void printPrompt()
      {
         PrintWriter temp = new PrintWriter( writer ); //temp value 
         temp.print("Enter a command:");
         temp.flush();
      }
}
