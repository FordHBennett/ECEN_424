import java.util.Scanner;

class calculator
{
    private String name;
    public Float addition ( Float A, Float B)
    {
        return A+B;
    }
    public Float subtraction ( Float A, Float B)
    {
        return A-B;
    }
    public Float multiplication ( Float A, Float B)
    {
        return A*B;
    }
    public void setname ( String N)
    {
        name = N;
    }
    public String getname ( )
    {
        return name;
    }

    public static void main(String[] args)
    {
        System.out.println("Welcome to the Calculator designed by Ford Bennett and _____.");
        Scanner input = new Scanner(System.in);
        welcome_mes:
        while(true)
        {
            System.out.println("Enter A to Add, S to Subtract , M to Multiply , and Q to quit. ");
            String choice = input.next();
            if(choice.equals("Q"))
            {
                System.out.println("Goodbye");
                break;
            }
            else if(choice.equals("S"))
            {
               System.out.println("Enter argument 1: ");
                Float A = Float.parseFloat(input.next());
                System.out.println("Enter argument 2: ");
                Float B = Float.parseFloat(input.next());
                calculator calc = new calculator();
                System.out.println("The difference is: " + calc.subtraction(A,B));
            }
            else if(choice.equals("M"))
            {
                System.out.println("Enter argument 1: ");
                Float A = Float.parseFloat(input.next());
                System.out.println("Enter argument 2: ");
                Float B = Float.parseFloat(input.next());
                calculator calc = new calculator();
                System.out.println("The product is: " + calc.multiplication(A,B));
            }
            else if(choice.equals("A"))
            {
                System.out.println("Enter argument 1: ");
                Float A = Float.parseFloat(input.next());
                System.out.println("Enter argument 2: ");
                Float B = Float.parseFloat(input.next());
                calculator calc = new calculator();
                System.out.println("The sum is: " + calc.addition(A,B));
            }
            else
            {
                System.out.println("Invalid input");
                continue welcome_mes;
            }
        }
        input.close();
    }
}
