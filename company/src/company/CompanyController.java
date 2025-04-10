package company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CompanyController {
    private static ArrayList<Component> _company = new ArrayList<>();

    private static final Scanner _inputScanner = new Scanner(System.in);
    private static final String _quitInput = "Q";
    private static final Map<String, Action> _inputActions = new HashMap<>();
    static{
        _inputActions.put("1", CompanyController::createAndPrintCompany);
        _inputActions.put("2", CompanyController::printAddAndPrintCompany);
        _inputActions.put("3", CompanyController::printRemoveAndPrintCompany);
    }

    public static void main(String[] args) {
        var isRunning = true;
        setupCompany();

        System.out.println("Organization management system");
        System.out.println("------------------------------\n");

        while(isRunning){
            printOptions();

            System.out.print("Your choice: ");
            var input = _inputScanner.nextLine();

            if(input.equalsIgnoreCase(_quitInput)){
                isRunning = false;
                return;
            }

            System.out.println();

            if(!_inputActions.containsKey(input)) return;
            _inputActions.get(input).invoke();
        }


    }

    private static void setupCompany(){
        var worker1 = new Worker("Susanna");

        var group1 = new Group("the cool group", "Lasse");

        group1.Add(worker1);

        _company.add(worker1);
        _company.add(group1);
    }

    private static void printOptions(){
        System.out.print(
                """
                1. Create and print hard coded organization
                2. Print organization, add person to it and finally print it
                3. Print organization, remove person from it and finally print it
                Q. Quit the application
                
                """);
    }

    private static void createAndPrintCompany() {
        System.out.println("Choice 1 not implemented");
    }

    private static void printAddAndPrintCompany() {
        System.out.println("Choice 2 not implemented");
    }

    private static void printRemoveAndPrintCompany() {
        System.out.println("Choice 3 not implemented");
    }
}
