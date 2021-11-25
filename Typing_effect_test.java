
//Typing effect

public class Typing_effect_test
{
    public void displayString(String str)
    {
        int i;//long j;
        for(i=0;i<str.length();i++)
        {
            System.out.print(str.charAt(i));
            //for(j=0;j<250000000;j++){}
            try
            {
                Thread.sleep(5);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }

        }
    }
}
