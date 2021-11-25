
//Scrum Framework _ Phase 2

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.Vector;

public class Scrum_Framework extends Input_Phase1 {
    public Stack<Stack<String>> SprintBacklog;       //Stores the entire sprint backlog with time slots
    public Stack<Stack<String>> SprintBacklogTasks;  //Stores the tasks on a daily basis
    public Stack<Stack<Integer>> SprintBacklogTime;  //Stores the time allocated to the tasks on a daily basis
    public Stack<String> FinalSlots = new Stack<>(); //Stack to store all the tasks divided into 1 hour slots
    public Stack<String> Tslot;                      //Stores the time slots available in a day for work
    public Stack<Integer> Tslot2;                    //Stores the positions of the time slots (for ascending order)
    public Stack<Float> SprintEff;                   //Stores the overall efficiency of each sprint
    public Vector<Vector<Integer>> Reviews;          //Stores the feedback answers on a daily basis
    public float efficiency;                         //a variable to temporarily store the efficiency of a task
    public Stack<Stack<Integer>> M,N,O;              //For feedback purposes
    public Stack<Stack<Float>> Eff ;                 //Stores the individual efficiency of each task on a daily basis

    void Display_Prod_Backlog()
    {
        int choice,i = 0;

        S1="\n------------------ PRODUCT BACKLOG -----------------------\n\n";
        T1.displayString(S1);

        while (i < tasks.size())
        {
            S1 = (i + 1) + ". " + tasks.get(i) + "\n";
            T1.displayString(S1);
            i++;
        }
        S1="\n----------------------------------------------------------\n";
        T1.displayString(S1);

        SlotMaking();
        Sprints();
        Display_Sprint();

        while(true)
        {
            S1 = "\nDo you want to a different schedule suggestion?\t1.YES\t2.NO\nYour Choice - ";
            T1.displayString(S1);
            choice = ExpHandling();
            while (choice>2)
            {
                S1="Invalid entry...please enter choice between (1-2) - ";
                T1.displayString(S1);
                choice = ExpHandling();
            }
            if (choice == 1)
            {
                SlotMaking();
                Sprints();
                Display_Sprint();
            }
            else
                break;
        }

        space2();

        i=0;
        S1="\n------------------ PRODUCT BACKLOG -----------------------\n\n";
        T1.displayString(S1);
        while (i < tasks.size())
        {
            S1 = (i + 1) + ". " + tasks.get(i) + "\n";
            T1.displayString(S1);
            i++;
        }
        S1="\n"+Name+" - Your DAILY SPRINTS are - \n";
        T1.displayString(S1);

        Display_Sprint();

        space2();

        Reviews = new Vector<>();
        Eff = new Stack<>();

        SprintReview();

        ProjectReport();

        AfterMath();
    }

    void SprintReview()
    {
        int count,choice;

        ArrayList<String> Statements = new ArrayList<>();
        Statements.add("\nIT'S A SLOW PROCESS, BUT QUITTING WON'T SPEED IT UP\n");
        Statements.add("\nBELIEVE YOU CAN AND YOU ARE HALFWAY THERE\n");
        Statements.add("\nEITHER YOU RUN THE DAY OR THE DAY RUNS YOU\n");
        Statements.add("\nKEEP WORKING HARD AND YOU CAN GET ANYTHING YOU WANT\n");
        Statements.add("\nYOU KEEP WORKING HARD FOR SOMETHING IMPORTANT EVEN IF THE ODDS ARE NOT IN YOUR FAVOUR\n");

        for(int i=0;i<SprintBacklog.size();i++)
        {
            S1="\n\n============================================\n";
            T1.displayString(S1);
            float sum = 0;
            S1 = "\n\t\tSPRINT " + (i + 1) + " in progress ...\n\n";
            T1.displayString(S1);
            for (int j = 0; j < SprintBacklog.get(i).size(); j++) {
                S1 = SprintBacklog.get(i).get(SprintBacklog.get(i).size() - j - 1) + "\n";
                T1.displayString(S1);
            }
            S1="\n============================================\n";
            T1.displayString(S1);
            do
            {
                S1 = "\nARE you done with this SPRINT?\t1.YES\t2.NO\nYour choice - ";
                T1.displayString(S1);
                choice = ExpHandling();
                while (choice > 2) {
                    S1="Invalid entry...please enter choice between (1-2) - ";
                    T1.displayString(S1);
                    choice = ExpHandling();
                }
                if (choice == 1)
                {
                    S1 = "\nPlease give the feedback for this SPRINT NOW\n\n";
                    T1.displayString(S1);

                    Feedback_Questions(i+1); //Feedback taken for each sprint

                    if (Reviews.get(Reviews.size() - 1).get(0) == 1 && Reviews.get(Reviews.size() - 1).get(1) == 1) {
                        for(int l=0;l<SprintBacklogTasks.get(i).size();l++)
                        {
                            Eff.get(i).add((float)100);
                        }
                        SprintEff.add((float)100);
                        S1="\nEfficiency for the SPRINT = "+100.0+"%\n";
                        T1.displayString(S1);
                    }
                    else if (Reviews.get(Reviews.size() - 1).get(0) == 1 && Reviews.get(Reviews.size() - 1).get(1) == 2)
                    {
                        for (int l = 0; l < SprintBacklogTasks.get(i).size(); l++)
                        {
                            count = 0;
                            for (int k = 0; k < Reviews.get(Reviews.size() - 1).get(2); k++)
                            {
                                if (l == M.get(i).get(k))
                                {
                                    Eff.get(i).add(Efficiency(N.get(i).get(k), SprintBacklogTime.get(i).get(l)));
                                    S1 = "\nEfficiency for - " + SprintBacklogTasks.get(i).get(l) + " = " + Efficiency(N.get(i).get(k), SprintBacklogTime.get(i).get(l)) + "%\n";
                                    T1.displayString(S1);
                                    count++;
                                    break;
                                }
                            }
                            if (count == 0)
                            {
                                Eff.get(i).add((float) 100.0);
                                S1 = "\nEfficiency for - " + SprintBacklogTasks.get(i).get(l) + " = " + 100.0 + "%\n";
                                T1.displayString(S1);
                            }
                        }
                        for (int k = 0; k < Eff.get(i).size(); k++)
                        {
                            sum += Eff.get(i).get(k);
                        }
                        SprintEff.add(sum / SprintBacklogTasks.get(i).size());
                        S1 = "\nEfficiency for the SPRINT = " + (sum / SprintBacklogTasks.get(i).size()) + "%\n";
                        T1.displayString(S1);
                    }
                    else if (Reviews.get(Reviews.size() - 1).get(0) == 2)
                    {
                        for (int l=0;l<SprintBacklogTasks.get(i).size();l++)
                        {
                            count=0;
                            for(int k =0;k<O.get(i).size();k++)
                            {
                                if(l==O.get(i).get(k))
                                {
                                    Eff.get(i).add((float)0);
                                    count++;
                                    break;
                                }
                            }
                            if(count==0)
                            {
                                Eff.get(i).add((float)100);
                                S1 = "\nEfficiency for - " + SprintBacklogTasks.get(i).get(l) + " = " + 100.0 + "%\n";
                                T1.displayString(S1);
                            }

                        }
                        for (int k = 0; k < Eff.get(i).size(); k++)
                        {
                            sum += Eff.get(i).get(k);
                        }
                        SprintEff.add(sum / SprintBacklogTasks.get(i).size());
                        S1 = "\nEfficiency for the SPRINT = " + (sum / SprintBacklogTasks.get(i).size()) + "%\n";
                        T1.displayString(S1);
                    }
                }
                else {
                    Collections.shuffle(Statements);
                    S1 = Statements.get(0);
                    T1.displayString(S1);
                }

            }while (choice==2);
        }

    }

    float Efficiency(int extraTime, int allocTime)
    {
        efficiency = (float)(((allocTime)*100)/(allocTime+extraTime));
        return efficiency;
    }

    void SlotMaking()
    {
        for (int i = 0; i < tasks.size(); i++) {
            for (int j = 0; j < taskDuration.get(i); j++) {
                FinalSlots.push(tasks.get(i));
            }
        }
        Collections.shuffle(FinalSlots); //Shuffle the slots
    }

    void Sprints()
    {
        int m,count;
        TimeSlots();
        Stack<Integer> temp1;
        SprintBacklog = new Stack<>();
        SprintBacklogTasks = new Stack<>();
        SprintBacklogTime = new Stack<>();
        SprintEff = new Stack<>();
        M = new Stack<>();  //Stores the positions of the tasks you have taken extra time for on a daily basis
        N = new Stack<>(); //Stores the amount of extra time taken(in hours) mentioned in m
        O = new Stack<>(); //Stores the positions of the tasks which weren't completed on a daily basis
        if(HoursTotal()%time1 ==0)
            m= HoursTotal()/time1;
        else
            m=(HoursTotal()/time1)+1;
        for (int i =0;i<m;i++)
        {
            temp1 = new Stack<>();
            SprintBacklog.push(new Stack<>());
            SprintBacklogTasks.push(new Stack<>());
            SprintBacklogTime.push(new Stack<>());
            M.push(new Stack<>());
            N.push(new Stack<>());
            O.push(new Stack<>());
            Collections.shuffle(Tslot2);
            for(int k=0;k<time1;k++)
            {
                temp1.push(Tslot2.get(k));
            }
            Collections.sort(temp1);
            for (int j=0;j<time1;j++)
            {
                count=0;
                if(!FinalSlots.empty()) {
                    SprintBacklog.get(i).push("\t\t" + FinalSlots.get(FinalSlots.size()-1) + "\t---->\t" + Tslot.get(temp1.get(time1-j-1)));
                    for(int k=0;k<SprintBacklogTasks.get(i).size();k++)
                    {
                        if(FinalSlots.get(FinalSlots.size()-1).equals(SprintBacklogTasks.get(i).get(k)))
                        {
                            SprintBacklogTime.get(i).set(k,SprintBacklogTime.get(i).get(k)+1);
                            count++;
                        }
                    }
                    if(count==0)
                    {
                        SprintBacklogTasks.get(i).push(FinalSlots.get(FinalSlots.size()-1));
                        count++;
                        SprintBacklogTime.get(i).push(count);
                    }

                    FinalSlots.pop();

                }
            }
        }
    }

    void Display_Sprint()
    {
        S1="\n------------------ SPRINT BACKLOG -----------------------\n";
        T1.displayString(S1);
        for(int i = 0;i<SprintBacklog.size();i++)
        {
            S1="\n\t\t\tDAY "+(i+1)+"\n\n";
            T1.displayString(S1);
            if(!SprintBacklog.get(i).empty())
            {
                for(int j=0;j<SprintBacklog.get(i).size();j++) {
                    S1 = SprintBacklog.get(i).get(SprintBacklog.get(i).size() - j - 1) + "\n";
                    T1.displayString(S1);
                }
            }
        }
        S1="\n----------------------------------------------------------\n";
        T1.displayString(S1);
    }

    void TimeSlots()
    {
        Tslot = new Stack<>();
        Tslot2 = new Stack<>();
        Tslot.push("08:00 - 09:00");Tslot.push("09:00 - 10:00");Tslot.push("11:00 - 12:00");
        Tslot.push("12:00 - 13:00");Tslot.push("13:00 - 14:00");Tslot.push("15:00 - 16:00");
        Tslot.push("16:00 - 17:00");Tslot.push("17:00 - 18:00");Tslot.push("19:00 - 20:00");
        Tslot.push("20:00 - 21:00");Tslot.push("22:00 - 23:00");Tslot.push("23:00 - 00:00");
        for(int i=0;i<12;i++)
            Tslot2.push(i);
    }

    void Feedback_Questions(int x)
    {
        int answer,temp,count;
        Eff.add(new Stack<>());
        Reviews.add(new Vector<>());
        S1 ="\nQ. Were you able to complete all the tasks mentioned in the sprint?\n1.YES\t2.NO\nAnswer - ";
        T1.displayString(S1);
        answer=ExpHandling();
        while(answer>2)
        {
            S1="Invalid entry...please enter choice between (1-2) - ";
            T1.displayString(S1);
            answer = ExpHandling();
        }
        Reviews.get(Reviews.size()-1).add(answer);
        if(answer == 1)
        {
            S1 = "\nQ. Were you able to complete all the tasks in the alloted time?\n1.YES\t2.NO\nAnswer - ";
            T1.displayString(S1);
            answer=ExpHandling();
            while(answer>2)
            {
                S1="Invalid entry...please enter choice between (1-2) - ";
                T1.displayString(S1);
                answer = ExpHandling();
            }
            Reviews.get(Reviews.size()-1).add(answer);
            if(answer == 2)
            {
                S1 = "\n------------- TODAY's TASKS -----------------\n\n";
                T1.displayString(S1);
                for(int i=0;i<SprintBacklogTasks.get(x-1).size();i++)
                {
                    S1=(i+1)+". "+SprintBacklogTasks.get(x-1).get(i)+"\t\t---->\t\t"+SprintBacklogTime.get(x-1).get(i) +" Hr(s)\n";
                    T1.displayString(S1);
                }
                S1 = "\n\n---------------------------------------------";
                T1.displayString(S1);
                S1 = "\nQ. How many tasks did you take extra time for?\nAnswer - ";
                T1.displayString(S1);
                answer = ExpHandling();
                while(answer>SprintBacklogTasks.get(x-1).size())
                {
                    S1="Invalid entry...please enter choice between (1-"+SprintBacklogTasks.get(x-1).size()+") - ";
                    T1.displayString(S1);
                    answer = ExpHandling();
                }
                Reviews.get(Reviews.size()-1).add(answer);
                S1 = "\nQ. Mention the position(s) of the task(s) which you took extra time for?\n";
                T1.displayString(S1);
                for (int i = 0; i < answer; i++)
                {
                    S1="Position " + (i + 1) + " - ";
                    T1.displayString(S1);
                    do {
                        count = 0;
                        temp = ExpHandling() - 1;
                        for (int k = 0; k < M.get(x - 1).size(); k++) {
                            if (M.get(x - 1).get(k) == temp) {
                                S1 = "Invalid entry...you have already entered this position. Please enter a different one - ";
                                T1.displayString(S1);
                                count++;
                                break;
                            }
                        }
                        if(temp>=SprintBacklogTasks.get(x-1).size())
                        {
                            S1="Invalid entry...please enter position between (1-"+SprintBacklogTasks.get(x-1).size()+") - ";
                            T1.displayString(S1);
                        }
                    }while (count==1||temp>=SprintBacklogTasks.get(x-1).size());
                    M.get(x-1).push(temp);
                }
                S1 = "\nQ. How much extra time did you take (in hours)?\n";
                T1.displayString(S1);
                for (int i = 0; i < answer; i++)
                {
                    S1=(i+1)+". For  " + SprintBacklogTasks.get(x-1).get(M.get(x-1).get(i)) + " - ";
                    T1.displayString(S1);
                    N.get(x-1).push(ExpHandling());
                    while(N.get(x-1).peek()>(24-time1))
                    {
                        S1="Invalid entry...please enter number of hours between (1-"+(24-time1)+") - ";
                        T1.displayString(S1);
                        N.get(x-1).pop();
                        N.get(x-1).push(ExpHandling());
                    }
                }
            }
            else
            {
                S1 = "\nGreat you did well (completed all tasks on time today)..\n";
                T1.displayString(S1);
            }
        }
        else
        {
            S1 = "\n------------- TODAY's TASKS -----------------\n\n";
            T1.displayString(S1);
            for(int i=0;i<SprintBacklogTasks.get(x-1).size();i++)
            {
                S1=(i+1)+". "+SprintBacklogTasks.get(x-1).get(i)+"\t\t---->\t\t"+SprintBacklogTime.get(x-1).get(i) +" Hr(s)\n";
                T1.displayString(S1);
            }
            S1 = "\n\n---------------------------------------------";
            T1.displayString(S1);
            S1 = "\nQ. How many tasks were you not able to complete?\nAnswer - ";
            T1.displayString(S1);
            answer = ExpHandling();
            while (answer>SprintBacklogTasks.get(x-1).size())
            {
                S1="Invalid entry...please enter choice between (1-"+SprintBacklogTasks.get(x-1).size()+") - ";
                T1.displayString(S1);
                answer = ExpHandling();
            }
            Reviews.get(Reviews.size()-1).add(answer);
            S1 = "\nQ. Mention the position(s) of the task(s) which you couldn't complete?\n";
            T1.displayString(S1);
            for (int i = 0; i < answer; i++)
            {
                S1="Position " + (i + 1) + " - ";
                T1.displayString(S1);
                do {
                    count = 0;
                    temp = ExpHandling() - 1;
                    for (int k = 0; k < O.get(x - 1).size(); k++) {
                        if (O.get(x - 1).get(k) == temp) {
                            S1 = "Invalid entry...you have already entered this position. Please enter a different one - ";
                            T1.displayString(S1);
                            count++;
                            break;
                        }
                    }
                    if(temp>=SprintBacklogTasks.get(x-1).size())
                    {
                        S1="Invalid entry...please enter position between (1-"+SprintBacklogTasks.get(x-1).size()+") - ";
                        T1.displayString(S1);
                    }
                }while (count==1||temp>=SprintBacklogTasks.get(x-1).size());
                O.get(x-1).push(temp);
            }
        }
    }

    void ProjectReport()
    {
        S1 = "\n\n************************************************************************************************\n";
        T1.displayString(S1);
        float sum=(float)0;
        S1="\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ PROJECT REPORT ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n";
        T1.displayString(S1);
        S1="\n\t------------------ PRODUCT BACKLOG -----------------------\t\n\n";
        T1.displayString(S1);
        int i=0,j;
        while (i < tasks.size())
        {
            S1 ="\t| "+(i + 1) + ". " + tasks.get(i) + "\n";
            T1.displayString(S1);
            i++;
        }
        S1="\n\t----------------------------------------------------------\t\n";
        T1.displayString(S1);

        Display_Sprint();

        S1 ="\n----------------------------- DAILY ANALYSIS -----------------------------\n";
        T1.displayString(S1);

        for(i=0;i<SprintBacklog.size();i++)
        {
            S1 ="\n\t\t------ DAY "+(i+1)+" SPRINT ------\t\t\n";
            T1.displayString(S1);

            for(j=0;j<SprintBacklog.get(i).size();j++)
            {
                S1=SprintBacklog.get(i).get(SprintBacklog.get(i).size() - j - 1) + "\n";
                T1.displayString(S1);
            }

            S1 = "\n\t\t----------------------------\t\t\n";
            T1.displayString(S1);
            S1 = "\n\t\t------ TASKS FOR THE DAY WERE ------\t\t\n\n";
            T1.displayString(S1);

            for(j=0;j<SprintBacklogTasks.get(i).size();j++)
            {
                S1=" |\t"+(j+1)+". "+SprintBacklogTasks.get(i).get(j)+"\t---->\t"+SprintBacklogTime.get(i).get(j) +" Hr(s)\n";
                T1.displayString(S1);
            }
            S1 = "\n\t\t----------------------------\t\t\n";
            T1.displayString(S1);

            if(!M.get(i).empty()) //Tasks which you took extra time for on a daily basis
            {
                S1="\nYou completed all the tasks but looks like you took some extra time..\n";
                T1.displayString(S1);
                S1="\n\t\t------ TASKS YOU TOOK EXTRA TIME FOR ------\t\t\n\n";
                T1.displayString(S1);
                for(j=0;j<M.get(i).size();j++)
                {
                    S1 =" |\t"+(j+1)+". "+SprintBacklogTasks.get(i).get(M.get(i).get(j))+"\t---->\t"+N.get(i).get(j)+" Hr(s)(extra)"+
                            "\t---->\t"+(SprintBacklogTime.get(i).get(M.get(i).get(j))+N.get(i).get(j))+" Hr(s)(total)\n";
                    T1.displayString(S1);
                }
                S1 = "\n\t\t----------------------------\t\t\n";
                T1.displayString(S1);
            }

            if(!O.get(i).empty()) //Tasks which you couldn't complete on a daily basis
            {
                S1="\nLooks like you couldn't complete all the tasks..\n";
                T1.displayString(S1);
                S1="\n\t\t------ TASKS YOU COULDN'T COMPLETE -----\t\t\n\n";
                T1.displayString(S1);
                for(j=0;j<O.get(i).size();j++)
                {
                    S1 = " |\t"+(j+1)+". "+SprintBacklogTasks.get(i).get(O.get(i).get(j))+"\t---->\t"+
                            SprintBacklogTime.get(i).get(O.get(i).get(j))+" Hr(s)\n";
                    T1.displayString(S1);
                }
            }
            if(M.get(i).empty() && O.get(i).empty())
            {
                S1="\nGreat job you completed all the tasks on time..\n";
                T1.displayString(S1);
            }
            S1 = "\n\t\t------ EFFICIENCY PARAMETERS FOR THE DAY ------\t\t\n\n";
            T1.displayString(S1);
            for(j=0;j<Eff.get(i).size();j++)
            {
                S1 = " | "+(j+1)+". "+SprintBacklogTasks.get(i).get(j)+"\t---->\t"+Eff.get(i).get(j)+"%\n";
                T1.displayString(S1);
            }
            S1 = "\n\tDAY "+(i+1)+" EFFICIENCY = "+SprintEff.get(i)+"%\n";
            T1.displayString(S1);
            S1 = "\n\t\t----------------------------\t\t\n";
            T1.displayString(S1);
        }
        for(j=0;j<SprintBacklog.size();j++)
        {
            sum+=SprintEff.get(j);
        }
        S1 = "\nMISSION ACCOMPLISHED WITH THE EFFICIENCY OF "+(sum/SprintBacklog.size())+"%\n";
        T1.displayString(S1);
        S1="\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        T1.displayString(S1);
        S1 = "\n************************************************************************************************\n";
        T1.displayString(S1);
    }

    void AfterMath()
    {
        int count,choice,choice2,choice3,temp;

        //Modifying task list based on project report
        tasks = new Stack<>();
        taskDuration = new Stack<>();

        for(int i =0;i<SprintBacklog.size();i++)
        {
            for(int j=0;j<O.get(i).size();j++)
            {
                count=0;
                for(int k =0;k<tasks.size();k++) {
                    if (SprintBacklogTasks.get(i).get(O.get(i).get(j)).equals(tasks.get(k)))
                    {
                        taskDuration.set(k,taskDuration.get(k)+SprintBacklogTime.get(i).get(O.get(i).get(j)));
                        count++;
                    }
                }
                if(count==0)
                {
                    tasks.push(SprintBacklogTasks.get(i).get(O.get(i).get(j)));
                    taskDuration.push(SprintBacklogTime.get(i).get(O.get(i).get(j)));
                }
            }
        }

        if(!tasks.empty()) //INCOMPLETE TASKS
        {
            S1="\n"+Name+", we see you have some unfinished tasks. Do you want to make a new Sprint?\n1.YES\t2.NO\nYour Choice - ";
            T1.displayString(S1);
            choice =ExpHandling();
            while (choice>2)
            {
                S1="Invalid entry...please enter choice between (1-2) - ";
                T1.displayString(S1);
                choice = ExpHandling();
            }
            if(choice==1)
            {
                S1 = "\nQ. Do you think these Sprints are overburdening?\n1.YES\t2.NO\nAnswer - ";
                T1.displayString(S1);
                choice3=ExpHandling();
                while (choice3>2)
                {
                    S1="Invalid entry...please enter choice between (1-2) - ";
                    T1.displayString(S1);
                    choice3 = ExpHandling();
                }
                if(choice3==1) {
                    S1 = "So now mention the REDUCED number of hours you want to work each day?\nAnswer - ";
                    T1.displayString(S1);
                    temp = ExpHandling();
                    while (temp > time1) {
                        S1="Invalid entry...please enter choice between (1-" + time1 + ") - ";
                        T1.displayString(S1);
                        temp = ExpHandling();
                    }
                    time1 = temp;
                }
                else {
                    S1 = "Do you want to increase the number of work hours/day?\n1.YES\t2.NO\nYour choice - ";
                    T1.displayString(S1);
                    choice2 = ExpHandling();
                    while (choice2>2)
                    {
                        S1="Invalid entry...please enter choice between (1-2) - ";
                        T1.displayString(S1);
                        choice2 = ExpHandling();
                    }
                    if(choice2==1)
                    {
                        S1 = "So now mention the INCREASED number of hours you want to work each day?\nAnswer - ";
                        T1.displayString(S1);
                        temp = ExpHandling();
                        while (temp <= time1||temp>12) {
                            S1="Invalid entry...please enter choice between ("+(time1+1)+" - 12) - ";
                            T1.displayString(S1);
                            temp = ExpHandling();
                        }
                        time1 = temp;
                    }
                }

                Display_Prod_Backlog();
            }
            else
            {
                S1="\nThank you for using the Software...\n";
                T1.displayString(S1);
                S1="\nDo you want to -\n1. GO TO LOGIN SCREEN\t\t2. EXIT\nYour choice - ";
                T1.displayString(S1);
                choice2 =ExpHandling();
                while (choice2>2)
                {
                    S1="Invalid entry...please enter choice between (1-2) - ";
                    T1.displayString(S1);
                    choice2 = ExpHandling();
                }
                if(choice2==1)
                {
                    return;
                }
                else {
                    S1="\n#############  EXITING THE SOFTWARE #############\n";
                    T1.displayString(S1);
                    System.exit(0);
                }
            }
        }
        else
        {
            S1 = "\n"+Name+", congratulations. Looks like you completed all your tasks.\n";
            T1.displayString(S1);
            S1 ="\nDo you want to -\n1. GO TO LOGIN\t\t2. EXIT\nYour choice - ";
            T1.displayString(S1);
            choice2 =ExpHandling();
            while (choice2>2)
            {
                S1="Invalid entry...please enter choice between (1-2) - ";
                T1.displayString(S1);
                choice2 = ExpHandling();
            }
            if(choice2==1)
            {
                return;
            }
            else {
                S1="\nEXITING THE SOFTWARE...\n";
                T1.displayString(S1);
                System.exit(0);
            }
        }
    }
}