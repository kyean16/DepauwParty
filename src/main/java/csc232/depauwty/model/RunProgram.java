package csc232.depauwty.model;

import java.io.*;

public class RunProgram
{
   
   public static void main(String[] args) throws IOException, InterruptedException
   {
      Driver driver = new Driver( new InputStreamReader( System.in ), new OutputStreamWriter( System.out ));
      
      driver.run();
   }

}
