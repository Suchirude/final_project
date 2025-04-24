package company;

public class Printer {
    private static int _currentIndent = 0;


    public static void printGroup(String value){
        System.out.println(" ".repeat(_currentIndent) + value);
    }
    public static void addIndent(int value){
        _currentIndent += value;
    }
    public static void printLine(String message){
        System.out.println(message);
    }
    public static void print(String message){
        System.out.print(message);
    }
    public static void printWorker(String message, int addedIndent){
        System.out.println(" ".repeat(_currentIndent + addedIndent) + message);
    }
}


