package test;

public class StaticB
{
    public static StaticB t1 = new StaticB();
    public static StaticB t2 = new StaticB();
    {
        System.out.println("¹¹Ôì¿é");
    }
    static
    {
        System.out.println("¾²Ì¬¿é");
    }
    public static void main(String[] args)
    {
    	StaticB t = new StaticB();
    }
}
