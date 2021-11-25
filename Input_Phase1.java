
//Input Phase _ 1

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class Input_Phase1
{
    public Stack<String> tasks;         //Stores the tasks given by the user
    public Stack<Integer> taskDuration; //Stores the duration of the tasks given by the user
    public Scanner sc3;                 //Scanner object for taking inputs
    public String S1,Name;              //S1 - String used for display effect  Name - Stores the user's name
    public Typing_effect_test T1;       //Object of class Typing effect
    int time1;                          //Stores the time(in hours) the user wants to work each day

    public void Login()
    {
        T1 = new Typing_effect_test();
        sc3= new Scanner(System.in);
        String str;
        int duration,i,choice,num;

        S1 = "\nPlease enter your Name - ";
        T1.displayString(S1);
        Name = sc3.nextLine();

        S1="\nMention the NUMBER OF TASKS to be accomplished (Can be modified later) - ";
        T1.displayString(S1);

        num = ExpHandling();

        tasks = new Stack<>();
        taskDuration = new Stack<>();

        for (i = 0; i < num; i++)
        {
            S1 = "\nENTER TASK " + (i + 1) + " - ";
            T1.displayString(S1);
            sc3.nextLine();
            str = sc3.nextLine();
            tasks.push(str);

            S1 = "ENTER THE DURATION YOU WANT TO WORK ON THIS TASK (in hours) - ";
            T1.displayString(S1);
            duration = ExpHandling();
            taskDuration.push(duration);
        }

        DisplayTasks();     //Displays the current task list

        S1 = "\nDo you want to modify the list\t1. YES\t2. NO\nYour choice - ";
        T1.displayString(S1);

        choice = ExpHandling();
        while (choice>2)
        {
            S1="Invalid entry...please enter choice between (1-2) - ";
            T1.displayString(S1);
            choice = ExpHandling();
        }

        if (choice==1)
            ModifyTask();

        S1 = "\nTotal Hours required = " + HoursTotal() +"\n" ;
        T1.displayString(S1);
        S1 = "\nDo you want to specify the number of hours/day (default: 6 hours/day)\t1. YES\t2. NO\nYour choice - ";
        T1.displayString(S1);

        choice = ExpHandling();
        while(choice>2)
        {
            S1="Invalid entry...please enter choice between (1-3) - ";
            T1.displayString(S1);
            choice = ExpHandling();
        }

        if (choice == 1)
        {
            S1 = "\nEnter the number of hours/day - ";
            T1.displayString(S1);
            time1 = ExpHandling();
            while(time1>12)
            {
                S1="We suggest you to not enter more than 12 hours of work/day - ";
                T1.displayString(S1);
                time1 = ExpHandling();
            }
        }

        else
            time1 = 6;

        if(HoursTotal()%time1 ==0) {
            S1 = "\nNumber of Days required to complete tasks = " + (HoursTotal() / time1);
        }

        else {
            S1 = "\nNumber of Days required to complete tasks = " + ((HoursTotal() / time1) + 1);
        }
        T1.displayString(S1);
        space2();
    }

    void DisplayTasks()
    {
        int i=0;
        S1 = "\n------------------ TASK LIST -----------------------\n\n";
        T1.displayString(S1);
        while(i< tasks.size()) {
            S1 = (i+1)+". "+tasks.get(i) + "\t---->\t" + taskDuration.get(i) + "\n";
            T1.displayString(S1);
            i++;
        }
        S1="\n----------------------------------------------------\n";
        T1.displayString(S1);
    }


    void DeleteTask(int ch)
    {
        tasks.remove(ch-1);
        taskDuration.remove(ch-1);
        DisplayTasks();
    }

    void ModifyTask()
    {
        int ch, pos, ch2, duration,choice;
        String str;

        sc3 = new Scanner(System.in);

        do {
            DisplayTasks();

            S1="\n---------------- TASK(S) MODIFICATION PANEL  -------------\n";
            T1.displayString(S1);
            S1="\n1.Alter tasks\n2.Insert new tasks\n3.Delete tasks\n4.Exit Panel";
            T1.displayString(S1);
            S1 = "\nYour choice - ";
            T1.displayString(S1);
            ch = ExpHandling();

            while (ch>4)
            {
                S1="Invalid entry...please enter choice between (1-3) - ";
                T1.displayString(S1);
                ch = ExpHandling();
            }

            switch (ch) {

                case 1 -> {
                    S1="\n------ ALTER TASKS ------\n";
                    T1.displayString(S1);
                    S1 = "\nEnter the position of the task you want to MODIFY - ";
                    T1.displayString(S1);

                    pos = ExpHandling();
                    while (pos > tasks.size()) {
                        S1="Invalid entry...please enter position between (1-" + tasks.size() + ") - ";
                        T1.displayString(S1);
                        pos = ExpHandling();
                    }

                    S1 = "\nDo you want to Modify the -\n1.Task\n2.Duration\n3.Both\nYour choice - ";
                    T1.displayString(S1);
                    ch2 = ExpHandling();
                    while (ch2 > 3) {
                        S1="Invalid entry...please enter choice between (1-3) - ";
                        T1.displayString(S1);
                        ch2 = ExpHandling();
                    }

                    switch (ch2) {

                        case 1 -> {
                            S1 = "\nEnter MODIFIED task - ";
                            T1.displayString(S1);
                            sc3.nextLine();
                            str = sc3.nextLine();
                            tasks.set((pos - 1), str);
                        }

                        case 2 -> {
                            S1 = "\nEnter MODIFIED duration - ";
                            T1.displayString(S1);
                            duration = ExpHandling();
                            taskDuration.set((pos - 1), duration);
                        }

                        case 3 -> {
                            S1 = "\nEnter MODIFIED task - ";
                            T1.displayString(S1);
                            sc3.nextLine();
                            str = sc3.nextLine();
                            tasks.set((pos - 1), str);
                            S1 = "\nEnter MODIFIED duration - ";
                            T1.displayString(S1);
                            duration = sc3.nextInt();
                            taskDuration.set((pos - 1), duration);
                        }

                        default -> System.out.print("\nInvalid choice\n");
                    }
                }

                case 2 -> {
                    S1="\n------ INSERT TASKS ------\n";
                    T1.displayString(S1);

                    do {
                        S1 = "\nENTER new TASK - ";
                        T1.displayString(S1);
                        sc3.nextLine();
                        str = sc3.nextLine();
                        tasks.push(str);

                        S1="ENTER THE DURATION YOU WANT TO WORK ON THIS TASK - ";
                        T1.displayString(S1);
                        duration = ExpHandling();
                        taskDuration.push(duration);

                        S1="\nDo you want to enter another task?\t1.YES\t2.NO\nYour Choice - ";
                        T1.displayString(S1);
                        sc3.nextLine();
                        choice = ExpHandling();
                        while (choice > 2) {
                            S1="Invalid entry...please enter choice between (1-2) - ";
                            T1.displayString(S1);
                            choice = ExpHandling();
                        }

                    } while (choice == 1);
                }
                case 3 -> {
                    S1="\n------ DELETE TASKS ------\n";
                    T1.displayString(S1);
                    S1="\nEnter the position of the task you want to DELETE - ";
                    T1.displayString(S1);
                    pos = ExpHandling();
                    while (pos > tasks.size()) {
                        S1="Invalid entry...please enter position between (1-" + tasks.size() + ") - ";
                        T1.displayString(S1);
                        pos = ExpHandling();
                    }
                    DeleteTask(pos);
                }
                case 4 -> {
                    S1 = "\n\t\tCURRENT TASK LIST\n";
                    T1.displayString(S1);
                    DisplayTasks();
                }
                default -> System.out.print("\nInvalid choice\n");
            }
        }while(ch!=4);
    }

    int  HoursTotal()
    {
        int total=0;int i;
        for(i=0;i< taskDuration.size();i++)
        {
            total+=taskDuration.get(i);
        }
        return total;
    }

    int ExpHandling()// Exception Handling function for integer inputs
    {
        int x;
        while(true)
        {
            try
            {
                x = sc3.nextInt();
                while (x <= 0)
                {
                    S1="Invalid entry please enter a positive integer (>0) - ";
                    T1.displayString(S1);
                    x = sc3.nextInt();
                }
                break;
            }
            catch (InputMismatchException e2)
            {
                S1="Invalid entry please enter a positive integer (>0) - ";
                T1.displayString(S1);
                sc3.next();
            }
        }
        return x;
    }

    public void space2()
    {
        System.out.print("\n\n\n\n\n\n");
    }
}