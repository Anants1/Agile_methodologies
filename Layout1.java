
//MAIN CLASS

import java.io.IOException;

public class Layout1
{

    public void space()
    {
        System.out.print("\n\n*************************************************************************************\n");
        System.out.print("\n\n\n\n\n\n\n\n");
        System.out.print("\n\n*************************************************************************************\n");
    }

    public static void title()
    {

        Typing_effect_test T2 = new Typing_effect_test();

        System.out.print("\n\n*************************************************************************************\n\n");
        T2.displayString("\t\t   //===========\\\\  \\\\================//   //===========\\\\                  \n");
        T2.displayString("\t\t   ||                        ||           ||            \n");
        T2.displayString("\t\t   ||                        ||           ||            \n");
        T2.displayString("\t\t   ||                        ||           ||            \n");
        T2.displayString("\t\t   \\\\==========\\\\            ||           ||     //=====\\\\ \n");
        T2.displayString("\t\t                ||           ||           ||            || \n");
        T2.displayString("\t\t                ||           ||           ||            || \n");
        T2.displayString("\t\t                ||           ||           \\\\============||           \n");
        T2.displayString("\t\t    ===========//            ||                         ||\n");
        T2.displayString("\n\t\t\tSCHEDULE TRACKER AND GENERATOR USING SCRUM");
        System.out.print("\n\n*************************************************************************************\n");
    }

    public static void main(String[] Args)
    {

        clrscr(); //Clears the terminal screen
        Scrum_Framework Scrum1 = new Scrum_Framework();
        title();
        Scrum1.Login(); //INPUT PHASE 1
        Scrum1.Display_Prod_Backlog(); //SCRUM FRAMEWORK (PHASE 2)
        main(Args); //Entire code recurses based on user preference

    }

    public static void clrscr()
    {

        //Clears Screen in java

        try {

            if (System.getProperty("os.name").contains("Windows"))

                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

            else

                Runtime.getRuntime().exec("clear");

        } catch (InterruptedException | IOException ignored) {}

    }
}