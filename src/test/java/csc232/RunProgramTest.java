package csc232;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import static org.junit.Assert.*;

import org.junit.Test;

import csc232.depauwty.model.Driver;

public class RunProgramTest 
{

   @Test
   public void testLook() throws IOException, InterruptedException
   {
      String testInput = "look";
      String expectedOutput = "Hello! Welcome to Surviving DePauwty, a text-based adventure. Please start by typing 'help' or 'h' to find out more about the " +
                      "objective of the \ngame and the possible commands to input.\n" +
            "Enter a command:Humbert Hall - In a first-year dorm room. " +
            "Directions open: West\nMoney\nIDcard\niPhone\nGrenade\nKeys\nWallet\nBackpack\n" +
            "Enter a command:";
      Reader reader = new StringReader(testInput);
      Writer writer = new StringWriter();
      Driver driver = new Driver(reader, writer);
      driver.run();
      assertEquals(expectedOutput, writer.toString().trim());
   }
   
   @Test
   public void testExamine() throws IOException //Object doesn't exist
, InterruptedException
   {
      String testInput = "examine steak";
      String expectedOutput = "Hello! Welcome to Surviving DePauwty, a text-based adventure. Please start by typing 'help' or 'h' to find out more about the " +
                      "objective of the \ngame and the possible commands to input.\n"
            + "Enter a command:I can't examine that. Try another examine command.\n"
            + "Enter a command:";
      Reader reader = new StringReader(testInput);
      Writer writer = new StringWriter();
      Driver driver = new Driver(reader, writer);
      driver.run();
      assertEquals(expectedOutput, writer.toString().trim());
   }
   @Test
   public void testTake() throws IOException //Object exist
, InterruptedException
   {
      String testInput = "take keys";
      String expectedOutput = "Hello! Welcome to Surviving DePauwty, a text-based adventure. Please start by typing 'help' or 'h' to find out more about the " +
                      "objective of the \ngame and the possible commands to input.\n"
            + "Enter a command:Keys taken from Humbert Hall dorm room\n"
            + "Enter a command:";
      Reader reader = new StringReader(testInput);
      Writer writer = new StringWriter();
      Driver driver = new Driver(reader, writer);
      driver.run();
      assertEquals(expectedOutput, writer.toString().trim());
   }
   @Test
   public void testNorth() throws IOException //Go North, a direction that doesn't exist
, InterruptedException
   {
      String testInput = "n";
      String expectedOutput = "Hello! Welcome to Surviving DePauwty, a text-based adventure. Please start by typing 'help' or 'h' to find out more about the " +
                      "objective of the \ngame and the possible commands to input.\n"
            + "Enter a command:This direction is blocked.\n"
            + "Enter a command:";
      Reader reader = new StringReader(testInput);
      Writer writer = new StringWriter();
      Driver driver = new Driver(reader, writer);
      driver.run();
      
      assertEquals(expectedOutput, writer.toString().trim());
   }
   @Test
   public void testWest() throws IOException //Go west a direction that exists, but is blocked without the key
, InterruptedException
   {
      String testInput = "w";
            String expectedOutput = "Hello! Welcome to Surviving DePauwty, a text-based adventure. Please start by typing 'help' or 'h' to find out more about the " +
                      "objective of the \ngame and the possible commands to input.\n"
                  + "Enter a command:You seem to be missing an object to continue.\n"
                  + "Enter a command:";
            Reader reader = new StringReader(testInput);
            Writer writer = new StringWriter();
            Driver driver = new Driver(reader,writer);
            driver.run();
            assertEquals(expectedOutput, writer.toString().trim());        
   }
   @Test
   public void testContainer() throws IOException //Taking a container
, InterruptedException
   {
      String testInput = "take wallet";
      String expectedOutput = "Hello! Welcome to Surviving DePauwty, a text-based adventure. Please start by typing 'help' or 'h' to find out more about the " +
                      "objective of the \ngame and the possible commands to input.\n"
            + "Enter a command:Wallet taken from Humbert Hall dorm room\n"
            + "Enter a command:";
      Reader reader = new StringReader(testInput);
      Writer writer = new StringWriter();
      Driver driver = new Driver(reader,writer);
      driver.run();
      assertEquals(expectedOutput, writer.toString().trim());        
   }
   @Test
   public void testWestwithRequirement() throws IOException//Going west with the key
, InterruptedException
   {
      String testInput = "take keys \n w";
      String expectedOutput = "Hello! Welcome to Surviving DePauwty, a text-based adventure. Please start by typing 'help' or 'h' to find out more about the " +
                      "objective of the \ngame and the possible commands to input.\n"
            + "Enter a command:Keys taken from Humbert Hall dorm room\n"
            + "Enter a command:Location: In Humbert Hall's hallway - 2nd floor. Directions open: East and North\n"
            + "Current Moves:1\n"
            + "Enter a command:";
      Reader reader = new StringReader(testInput);
      Writer writer = new StringWriter();
      Driver driver = new Driver(reader,writer);
      driver.run();
      assertEquals(expectedOutput, writer.toString().trim());        
   }
   @Test
   public void testDying() throws IOException //If you take a grenade
, InterruptedException
   {
      String testInput = "take grenade";
      String expectedOutput = "Hello! Welcome to Surviving DePauwty, a text-based adventure. Please start by typing 'help' or 'h' to find out more about the " +
                      "objective of the \ngame and the possible commands to input.\n"
            + "Enter a command:When you took this grenade, the pin fell out and Humbart Hall ceased to exist. Many years later the building was rebuilt and you restarted the game.\n"
            + "Hello! Welcome to Surviving DePauwty, a text-based adventure. Please start by typing 'help' or 'h' to find out more about the " +
                      "objective of the \ngame and the possible commands to input.\n"
            + "Enter a command:"
            + "Enter a command:";
      Reader reader = new StringReader(testInput);
      Writer writer = new StringWriter();
      Driver driver = new Driver(reader,writer);
      driver.run();
      assertEquals(expectedOutput, writer.toString().trim());
   }
   @Test
   public void testPut() throws IOException //Put keys
, InterruptedException
   {
      String testInput = "take keys \nput keys";
      String expectedOutput = "Hello! Welcome to Surviving DePauwty, a text-based adventure. Please start by typing 'help' or 'h' to find out more about the " +
                      "objective of the \ngame and the possible commands to input.\n"
            + "Enter a command:Keys taken from Humbert Hall dorm room\n"
            + "Enter a command:Keys put into Humbert Hall dorm room\n"
            + "Enter a command:";
      Reader reader = new StringReader(testInput);
      Writer writer = new StringWriter();
      Driver driver = new Driver(reader,writer);
      driver.run();
      assertEquals(expectedOutput, writer.toString().trim());        
   }
   @Test
   public void testTakeKeyputInWallet() throws IOException, InterruptedException
   {
      String testInput = "take keys \ntake wallet\nput keys in wallet";
      String expectedOutput = "Hello! Welcome to Surviving DePauwty, a text-based adventure. Please start by typing 'help' or 'h' to find out more about the " +
                      "objective of the \ngame and the possible commands to input.\n" +
            "Enter a command:Keys taken from Humbert Hall dorm room\n" +
            "Enter a command:Wallet taken from Humbert Hall dorm room\n" +
            "Enter a command:keys has been removed from the inventory \n"+
            "keys has been added to the wallet\n" +
            "Enter a command:";
      Reader reader = new StringReader(testInput);
      Writer writer = new StringWriter();
      Driver driver = new Driver(reader,writer);
      driver.run();
      assertEquals(expectedOutput, writer.toString().trim());  
   }
   
   @Test
   public void testTakeKeyPutInWalletput() throws IOException, InterruptedException
   {
      String testInput = "take keys \ntake wallet\nput keys in wallet\nput wallet";
      String expectedOutput = "Hello! Welcome to Surviving DePauwty, a text-based adventure. Please start by typing 'help' or 'h' to find out more about the " +
                      "objective of the \ngame and the possible commands to input.\n" +
            "Enter a command:Keys taken from Humbert Hall dorm room\n" +
            "Enter a command:Wallet taken from Humbert Hall dorm room\n" +
            "Enter a command:keys has been removed from the inventory \n"+
            "keys has been added to the wallet\n" +
            "Enter a command:Wallet put into Humbert Hall dorm room\n"+
            "Enter a command:";
      Reader reader = new StringReader(testInput);
      Writer writer = new StringWriter();
      Driver driver = new Driver(reader,writer);
      driver.run();
      assertEquals(expectedOutput, writer.toString().trim());  
   }
   @Test
   public void testTakeKeyPutInWalletTake() throws IOException, InterruptedException
   {
      String testInput = "take keys \ntake wallet\nput keys in wallet\ntake keys from wallet";
      String expectedOutput = "Hello! Welcome to Surviving DePauwty, a text-based adventure. Please start by typing 'help' or 'h' to find out more about the " +
                      "objective of the \ngame and the possible commands to input.\n" +
            "Enter a command:Keys taken from Humbert Hall dorm room\n" +
            "Enter a command:Wallet taken from Humbert Hall dorm room\n" +
            "Enter a command:keys has been removed from the inventory \n"+
            "keys has been added to the wallet\n" +
            "Enter a command:keys has been removed from wallet\n"+
            "keys has been added to the inventory\n"+
            "Enter a command:";
      Reader reader = new StringReader(testInput);
      Writer writer = new StringWriter();
      Driver driver = new Driver(reader,writer);
      driver.run();
      assertEquals(expectedOutput, writer.toString().trim());  
   }
   @Test
   public void testInventory() throws IOException //Checks inventory
, InterruptedException
   {
      String testInput = "i";
      String expectedOutput ="Hello! Welcome to Surviving DePauwty, a text-based adventure. Please start by typing 'help' or 'h' to find out more about the " +
                      "objective of the \ngame and the possible commands to input.\n" +
            "Enter a command:Inventory is currently empty. \n" +
            "Enter a command:";
      Reader reader = new StringReader(testInput);
      Writer writer = new StringWriter();
      Driver driver = new Driver(reader,writer);
      driver.run();
      assertEquals(expectedOutput, writer.toString().trim());
   } 
   
}
