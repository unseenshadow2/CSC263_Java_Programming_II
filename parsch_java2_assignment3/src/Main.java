/**
 * Created by unseenshadow2 on 10/22/2016.
 */
public class Main
{
    public static void main (String[] args)
    {
        ASCIICharacter toPrint[] = {ASCIICharacter.U, ASCIICharacter.A, ASCIICharacter.T};

        for (int i = 0; i < toPrint[0].getValue().length; i++) // Line by line
        {
            for (ASCIICharacter x : toPrint) // Character line
            {
                for (int j = 0; j < x.getValue()[i].length(); j++) // Character by character
                {
                    System.out.print(x.getValue()[i].charAt(j)); // Print the character
                }

                System.out.print(""); // The space between the characters
            }

            System.out.print(System.lineSeparator()); // End the line
        }
    }
}
