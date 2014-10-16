package csc232.depauwty.model.GUITest;

import java.io.*;

import csc232.depauwty.model.GUITest.RunnableGUI;

public class RunProgram
{
   
   public static void main(String[] args) throws IOException
   {
      //Driver driver = new Driver( new InputStreamReader( System.in ), new OutputStreamWriter( System.out ));
      RunnableGUI text = new RunnableGUI();
      
      //driver.run();
      text.run();
   }

}

