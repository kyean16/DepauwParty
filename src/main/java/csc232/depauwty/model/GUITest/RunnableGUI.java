package csc232.depauwty.model.GUITest;
/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 


/* TextDemo.java requires no other files. */



/* TextDemo.java requires no other files. */

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class RunnableGUI extends JPanel implements ActionListener 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel jLabel1;
	private JLabel jLabel2;
   protected JTextField textField;
   protected JTextArea textArea;
   private DefaultGame game;
   private GameState state;
   private static JFrame frame;

    public RunnableGUI() 
    {
        super(new GridBagLayout());
        
        textField = new JTextField(70);
        textField.addActionListener(this);

        textArea = new JTextArea(20, 70);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        
        jLabel1 = new JLabel();
        jLabel1.setText("Enter a Command:");
        
        jLabel2 = new JLabel("<html><p align=center>Surviving DePauwty <br> by Michael, Cody, Kevin</p></html>", SwingConstants.CENTER);
        //jLabel2.setText("Surviving DePauwTy  by Michael, Cody, Kevin");

        
        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
         
       restart();
       
        c.fill = GridBagConstraints.BOTH;
        c.weightx =0.0;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 0;
        add(jLabel2, c);
        
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 100;      //make this component tall
        c.weightx = 0.0;
        c.weighty = 1;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        add(scrollPane, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.ipady = 0;       //reset to default
        c.weighty =0.0;   //request any extra vertical space
        c.gridwidth = 1 ;
        c.gridx = 0;
        c.gridy = 3;
        add(jLabel1,c);
        
        c.fill = (GridBagConstraints.HORIZONTAL);
        c.gridheight = 1;
        c.weightx = 1;
        c.gridx = (GridBagConstraints.RELATIVE);;
        c.gridy = 3;
        add(textField, c);
        
       
    
        textArea.append("Hello! Welcome to Surviving DePauwty, a text-based adventure. Please start by typing 'help' or 'h' to find out more about the " +
                 "objective of the game and the possible commands to input. \n ---------------  \n");
    }
    public void restart()
    {
       game = new DefaultGame(); //Initializes stuff
       state = new GameState();//Initializes the player
    }
    public void actionPerformed(ActionEvent evt)
    {
        String text = textField.getText();
        text = text.trim().toLowerCase();  //deletes unneeded spaces at beginning and end of linesListOfItems and converts to lower case
        String[] words = text.split (" ");
        
        textField.setCaretPosition(textField.getDocument().getLength());
        
        if(words.length > 1 && words[0].equals("examine")) //Checks to see if first word is "examine"
        { 
          boolean itemPresent = false;  // keeps track whether or not item is present
          String shortName = words[1];  // makes second word in array equal to shortName\
          
          for (int i = 0; i< game.currentLocation.items.size() ; i++) //This checks the list of items in the location
          {
             if (game.currentLocation.items.get(i).getShortName().toLowerCase().equals(shortName)) // if the shortName equals getShortName
             {
                textArea.append(game.currentLocation.items.get(i).getDescription());  //print out the item's description.
                itemPresent = true;
             }
          }
          if (itemPresent == false) //This checks the list of items and containers in the location
          {
             for (int i = 0; i < game.currentLocation.container.size(); i++)
                if (game.currentLocation.container.get(i).getShortName().toLowerCase().equals(shortName))
                {
                   textArea.append(game.currentLocation.container.get(i).getDescription("not inventory"));
                   itemPresent = true;
                }
          }
          if (itemPresent == false) //This checks the items and container in the inventory
          {
             for (int i = 0; i < (state).itemsInInventory.size() ; i++)//Goes throught the whole inventory
             {
                if (state.itemsInInventory.get(i).getShortName().toLowerCase().equals(shortName)) // if the shortName equals getShortName
                {
                   textArea.append((state).itemsInInventory.get(i).getDescription());
                     itemPresent = true;
                }
             }
            for (int i = 0; i < state.containersInInventory.size() ; i++)//Goes throught the whole container
            {
                if (state.containersInInventory.get(i).getShortName().toLowerCase().equals(shortName)) // if the shortName equals getShortName
                {
                   textArea.append(state.containersInInventory.get(i).getDescription("not inventory"));
                   itemPresent = true;
                }
            }
          }
          if (itemPresent == false) //Finally if it can find anything it does this.
          {
             textArea.append("I can't examine that. Try another examine command.");
          }
        }
     
        // This is for displaying the help window
        else if(text.equals("help") || text.equals("h"))
        {
           JFrame helpFrame = new JFrame();
           helpFrame = new JFrame("HelpDocument");
           
           JTextArea textHelp = new JTextArea(25, 60);
           textHelp.setEditable(false);
           textHelp.append("\t\t\tSurviving DePauwty - Text Adventure Game\n\n" + 

            "Objective of the game:\n" +
            "Get your player to their first fraternity party on Campus! Pick up items to take with you throughout the game.\n" +
            "Try to get to the party in the smallest number of moves. Good luck!\n\n" +
 
            "List of Possible Commands:\n" +
            "look or l - Tells your location and the items in the location\n" +
            "examine name - Prints out a description of the item with the given name you want to examine\n" +
            "take name - Take item with given name from location and add it to your inventory\n" +
            "put name - Put item with given name in your current location (taken from inventory)\n" +
            "take name from container - Take item with given name from given container\n" +
            "put name in container - Put item with given name in given container\n" +
            "inventory or i - Lists all of the items in your inventory\n" +
            "north or n - Moves player north\n" +
            "east or e - Moves player east\n" +
            "south or s - Moves player south\n" +
            "west or w - Moves player west\n" +
            "help or h - Opens this text file\n" +
            "quit or q - Quits the game\n\n" +
            
            "Please return to the previous window to begin your adventure.\nKeep in mind of this text file when working through the game. Have fun!");

           //Add contents to the window.
           helpFrame.add(textHelp);

           //Display the window.
           helpFrame.pack();
           helpFrame.setVisible(true);
        }
        
        // Following code is for taking and putting simple Item objects
        else if(words.length == 2 && words[0].equals("take")) // Take
        {
           boolean itemPresent = false;  // keeps track whether or not item is present
           String shortName = words[1];  // makes second word in array equal to shortName
           for (int i = 0; i< game.currentLocation.items.size() ; i++) //Goes throughout the whole location item arraylist
           {
              if (game.currentLocation.items.get(i).getShortName().toLowerCase().equals(shortName)) // if the shortName equals getShortName of the item
              {
                 if (game.currentLocation.items.get(i).getDeadly())
                 {
                    textArea.append (game.currentLocation.items.get(i).getDeadlyDescription());
                    itemPresent = true;                 
                    restart();
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
                             textArea.append(state.itemsInInventory.get(b).getShortName() + " has been removed from the inventory.");
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
                                textArea.append(state.containersInInventory.get(c).listOfItems.get(d).getShortName() + " has been removed from the container.");
                                state.containersInInventory.get(c).listOfItems.remove(d);
                                
                             }
                          }
                       }
                    }
                    if (required !=  game.currentLocation.items.get(i).requirements.size())
                    {
                       textArea.append("I am sorry, you are missing an item to allow your requested action." );
                       itemPresent = true;
                    }
                    else if (required == game.currentLocation.items.get(i).requirements.size())
                    {
                       state.addItem(game.currentLocation.items.get(i));
                       textArea.append(game.currentLocation.items.get(i).getShortName() + " taken from " + game.currentLocation.getName()); // Displays from where the item was taken
                       game.currentLocation.items.remove(i);//removes the items from the location
                       itemPresent = true;//Present
                    }
                 }
                 else
                 {
                    state.addItem(game.currentLocation.items.get(i));
                    textArea.append(game.currentLocation.items.get(i).getShortName() + " taken from " + game.currentLocation.getName()); // Displays from where the item was taken
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
                    textArea.append(game.currentLocation.container.get(i).getShortName() + " taken from " + game.currentLocation.getName()); // Displays from where the item was taken
                    game. currentLocation.container.remove(i);//removes the items from the location
                    itemPresent = true;//Present
                 }
           }
           if (itemPresent == false)//Not present
           {
              textArea.append("I can't take that. Try another take command.");
           }
         
        }
        else if(words.length == 2 && words[0].equals("put")) //Put
        {
           boolean itemPresent = false;  // keeps track whether or not item is present
           String shortName = words[1];  // makes second word in array equal to shortName
           for (int i = 0; i < state.itemsInInventory.size() ; i++)//Goes throughout the whole inventory
           {
              if (state.itemsInInventory.get(i).getShortName().toLowerCase().equals(shortName)) // if the shortName equals getShortName
              {
                game.currentLocation.addItem((state).itemsInInventory.get(i));  //adds given item to location and removes it from inventory
                textArea.append((state).itemsInInventory.get(i).getShortName() + " put into " + game.currentLocation.getName());//Displays where the item was put into
                state.itemsInInventory.remove(i);//Removes the item from the inventory
                itemPresent = true;//Present
              }
           }
           for (int i = 0; i < state.containersInInventory.size(); i++)//Goes throughout the whole inventory
           {
              if (state.containersInInventory.get(i).getShortName().toLowerCase().equals(shortName)) // if the shortName equals getShortName
              {
                 game.currentLocation.addContainer((state).containersInInventory.get(i));  //adds given item to location and removes it from inventory
                 textArea.append((state).containersInInventory.get(i).getShortName() + " put into " + game.currentLocation.getName());//Displays where the item was put into
                (state).containersInInventory.remove(i);//Removes the item from the inventory
                itemPresent = true;//Present
              }
           }
           if (itemPresent == false)//Not present
           {
              textArea.append("I can't put that. Try another put command.");
           }
        }
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
                 for(int x = 0; x <  state.containersInInventory.get(i).listOfItems.size(); x++) //Looks through the items in 
                    // the container and if found puts in the inventory and removes it from container
                 {
                    if (shortName.equals(state.containersInInventory.get(i).listOfItems.get(x).getShortName().toLowerCase()))
                    {
                       state.addItem(state.containersInInventory.get(i).listOfItems.get(x));
                       state.containersInInventory.get(i).removeItem(shortName);
                       textArea.append(shortName + " has been removed from " + containerName + "\n" +
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
                          textArea.append(shortName + " has been removed from " + containerName + "\n" +
                                      shortName + " has been added to the inventory");
                          itemPresent = true;
                       }
                    }
                 }
              }
           }                  
           if (itemPresent == false)
           {
              textArea.append("I can't take that. Try another take command.");
           }
        }
        else if(words.length == 4 && words[0].equals("put") && words[2].equals("in")) // Put ____ in _______
        {
           boolean itemPresent = false;  // keeps track whether or not item is present
           String shortName = words[1];  // makes second word in array equal to shortName
           String containerName = words[3];
           for(int i = 0; i < state.itemsInInventory.size(); i++) //Goes throughout all the items in the inventory
           {
              if(shortName.equals(state.itemsInInventory.get(i).getShortName().toLowerCase()) && state.itemsInInventory.size() > 0) //Checks if the name in the inventory is present
              {
                 for(int x = 0; x < state.containersInInventory.size(); x++)
                 {
                    if(containerName.equals(state.containersInInventory.get(x).getShortName().toLowerCase())) // Check for container and if it exists put its in it
                    {
                       state.containersInInventory.get(x).addItem(state.itemsInInventory.get(i));
                       state.removeItem(shortName);
                       textArea.append(shortName + " has been removed from the inventory \n" +
                                   shortName + " has been added to the " + containerName);
                       itemPresent = true;
                    }
                 }
              }
           }
           if(itemPresent == false) //Check for containers in location
           {
              for(int i = 0; i < game.currentLocation.container.size(); i++) //Goes throughout all the items in the inventory
              {
                 if(containerName.equals(game.currentLocation.container.get(i).getShortName().toLowerCase())) //Checks if the name of the container is present
                 {
                    for(int x = 0; x < state.itemsInInventory.size(); x++)
                    {
                       if(shortName.equals(state.itemsInInventory.get(x).getShortName().toLowerCase())) // Check for item and if it exists put its in it
                       {
                          game.currentLocation.container.get(i).addItem(state.itemsInInventory.get(x));
                          state.removeItem(shortName);
                          textArea.append(shortName + " has been removed from inventory \n" +
                                      shortName + " has been added to the " + containerName);
                          itemPresent = true;
                       }
                    }
                 }
              }
           }
          if (itemPresent == false)
           {
             textArea.append("I can't help you with that. Please try again.");
           }
        }
         
        //The following code checks if the direction the player asks exist for the currentLocation and if it is the player moves there.
        else if(text.equals("north") || text.equals("n"))
        {
           if(game.currentLocation.checkNeighbors("north") != null)
           {
              if(game.currentLocation.checkNeighbors("north").requiredKey(state.itemsInInventory, state.containersInInventory))
              {
                 game.currentLocation = game.currentLocation.checkNeighbors("north");
                 state.presentLocation(game.currentLocation.checkNeighbors("north"));
                 state.addingMoves();
                 textArea.append("Location: " + game.currentLocation.getDescription() + "\n" +
                          "Current Moves:" + state.getMoves());
              }
              else
              {
                 textArea.append ("You seem to be missing an object to continue.");
              }
           }
           else
           {
              textArea.append("This direction is blocked.");
           }
        }
        else if(text.equals("east") || text.equals("e"))
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
                    textArea.append("Location: " + game.currentLocation.getDescription() + "\n" +
                             "Current Moves:" + state.getMoves());
                    
                    if(game.currentLocation.getName().equals("Public Safety"))
                    {
                       restart();
                    }
                    if(game.currentLocation.getName().equals("Safe Ride"))
                    {
                       textField.setEditable(false);
                    }
                 }
                 else
                 {
                    textArea.append ("You seem to be missing an object to continue.");
                 }
              }
           }
           else
           {
              textArea.append("This direction is blocked.");
           }
        }
        else if(text.equals("south") || text.equals("s"))
        {
           if(game.currentLocation.checkNeighbors("south") != null)
           {
              if(game.currentLocation.checkNeighbors("south").requiredKey(state.itemsInInventory, state.containersInInventory))
              {
                 game.currentLocation = game.currentLocation.checkNeighbors("south");
                 state.presentLocation(game.currentLocation.checkNeighbors("south"));
                 state.addingMoves();
                 textArea.append("Location: " + game.currentLocation.getDescription() + "\n" +
                          "Current Moves:" + state.getMoves());
                 if(game.currentLocation.getName().equals("Public Safety"))
                 {
                    restart();
                 }
              }
              else
              {
                 textArea.append ("You seem to be missing an object to continue.");
              }
           }
           else
           {
              textArea.append("This direction is blocked.");
           }
        }
        else if(text.equals("west") || text.equals("w"))
        {
           if(game.currentLocation.checkNeighbors("west") != null)
           {
              if(game.currentLocation.checkNeighbors("west").requiredKey(state.itemsInInventory, state.containersInInventory))
              {
                 game.currentLocation = game.currentLocation.checkNeighbors("west");
                 state.presentLocation(game.currentLocation.checkNeighbors("west"));
                 state.addingMoves();
                 textArea.append("Location: " + game.currentLocation.getDescription() + "\n" +
                          "Current Moves:" + state.getMoves());
              }
              else
              {
                 textArea.append("You seem to be missing an object to continue.");
              }
           }
           else
           {
              textArea.append("This direction is blocked.");
           }
         }
        
        else if(text.equals("inventory") || text.equals("i")) //Inventory display
        {
           textArea.append(state.getInventory());
        }
        
        else if(text.equals("look") || text.equals("l"))
        {
           textArea.append(game.currentLocation.getDescription() + "\n"); //Prints location description
           textArea.append(game.currentLocation.getAllItemsNames());//Print objects in location in description
        }

        else if(text.equals("quit") || text.equals("q"))
        {
           System.exit(ERROR);
        }
        else if(words.length == 2 && words[0].equals("use") && words[1].equals("gcb"))
        {
           textArea.append("You found a cheat code. By consuming the GCB, you had an amazing night and you automatically won the game!");
           textField.setEditable(false);
           
        }
        else
        {
           textArea.append("I do not understand, please try again.");
        }
        
        textArea.append("\n");
        textArea.append("--------------- " + "\n");
        textField.setText("");

        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Text Adventure Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
        frame.add(new RunnableGUI());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    public void run() 
    {
                createAndShowGUI();
    }
}
