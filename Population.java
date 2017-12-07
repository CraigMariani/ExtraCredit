// Class Population - EGR222 - Craig Mariani - CBU - November 15, 2017
// Uses array list commands and declaration in code below to organize an array of Strings
//
import java.awt.*;
import java.util.*;

public class Population
{
    private Graphics myG;
    public int x;
    public int y;
    public int i;
    public int j;
    public Random rand = new Random();
    public int location;
    public boolean[][] populated = new boolean[700][700];//two dimensional array of booleans for old population
    public boolean[][] newPopulated = new boolean[700][700]; //two dimensional array for new population
    public int populatedCount;

    public boolean[][] oldPopulation() //just use this method to determine the population use a separate one to draw it to the panel
    {

        for(x = 0; x < 700; x = x + 4)
        {
            for(y = 0; y < 700; y = y + 4)
            {

                location = rand.nextInt(11);
                if(location > 5)
                {
                    //the selected area is true and it is populated
                    populated[x][y] = true;
                }
                else
                {
                    //the selected area is false and it is not populated
                    populated[x][y] = false;
                }
            }
        }
        return populated;
    }

    public boolean[][] newPopulation(boolean[][] populated)//this will carry over the array from the old population and determine the new one
    {
        for(x = 0; x < 699; x = x + 4)
        {
            for(y = 0; y < 699; y = y + 4)
            {
                if(populated[x][y] == false)
                {
                    for(i = x - 4; i <= x + 4; i += 4)
                    {
                        for(j = y - 4; j <= y + 4; y += 4)
                        {
                            if(populated[x][y] == true)
                            {
                                populatedCount++;
                            }
                        }
                    }

                    if(populatedCount == 3)
                    {
                        newPopulated[x][y] = true;
                    }
                }
            }
        }
        return newPopulated;

    }

    public void drawCurrentPopulation(boolean[][] drawPopulated)
    {
        for(x = 0; x < 699; x = x + 4)
        {
            for(y = 0; y < 699; y = y + 4)
            {
                if(drawPopulated[x][y] == true)
                {
                    myG.fillRect(x, y, 4, 4);
                }
            }
        }



    }

    public static void main(String args[])
    {
        DrawingPanel P = new DrawingPanel(700, 700);
        P.setBackground(Color.WHITE);
        Population D = new Population(); //creates a new constructor
        D.myG = P.getGraphics(); //accesses myG the private method from inside of the class draw

        Scanner console = new Scanner(System.in);


        /*
        while(true)
        {
            D.oldPopulation(); //old population is determined
            D.drawCurrentPopulation(); //draws current population to panel
            D.newPopulation(); //new population is shown on the panel
        }
        */


       // D.oldPopulation(); //old population is determined
        D.drawCurrentPopulation(D.oldPopulation()); //draws current population to panel
        //D.newPopulation(D.oldPopulation()); //new population is determined
        System.out.println("Next Population");
        int usrInput = console.nextInt();
        System.out.println(usrInput);
        D.drawCurrentPopulation(D.newPopulation(D.oldPopulation()));

    }
}
