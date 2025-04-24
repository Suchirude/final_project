package company;

import java.util.*;

public class CompanyController {
    private static Component _company;
    private static List<Group> _groupList;

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


        while(isRunning){

            Printer.printLine("");
            Printer.printLine("Organization management system");
            Printer.printLine("------------------------------\n");

            printOptions();

            Printer.print("Your choice: ");
            var input = _inputScanner.nextLine();

            if(input.equalsIgnoreCase(_quitInput)){
                isRunning = false;
                return;
            }

            if(_inputActions.containsKey(input)){
                _inputActions.get(input).invoke();
            }
            else{
                wrongInputError();
            }
        }


    }

    private static void printOptions(){
        Printer.print(
                """
                1. Create and print hard coded organization
                2. Print organization, add person to it and finally print it
                3. Print organization, remove person from it and finally print it
                Q. Quit the application
                
                """);
    }

    private static void createAndPrintCompany() {
        setupCompany();
        printCompany();
    }

    private static void printAddAndPrintCompany() {
        if(_company == null){
            noOrganizationError();
            return;
        }

        printCompany();

        Printer.printLine("");

        Printer.print("Give unit name: ");
        var unitName = _inputScanner.nextLine();
        Printer.print("Give person name: ");
        var personName = _inputScanner.nextLine();

        if(!personName.matches("^[A-Z][a-z]*\\s[A-Z][a-z]*$")){
            invalidNameError();
            return;
        }

        if(!addWorker(new Worker(personName), unitName)){
            organizationNotFoundError();
            return;
        };

        printCompany();
    }

    private static void printRemoveAndPrintCompany() {
        if(_company == null){
            noOrganizationError();
            return;
        }

        printCompany();
        Printer.printLine("");

        Printer.print("Give person name: ");
        var personName = _inputScanner.nextLine();

        if(!personName.matches("^[A-Z][a-z]*\\s[A-Z][a-z]*$")){
            invalidNameError();
            return;
        }

        if(!removeWorker(new Worker(personName))){
            personNotFoundError();
            return;
        }

        printCompany();
    }

    private static void setupCompany(){
        var secretaryGrandma = new Worker("Grandma Duck");
        secretaryGrandma.setRole("secretary");
        var workerGus = new Worker("Gus Goose");
        var workerHuey = new Worker("Huey Duck");
        var workerDewey = new Worker("Dewey Duck");
        var workerLouie = new Worker("Louie Duck");
        var workerGyro = new Worker("Gyro Gearloose");
        var workerMagica = new Worker("Magica De Spell");
        var workerLaunchpad = new Worker("Launchpad McQuack");

        var managementGroup = new Group("Top management", "Scrooge McDuck");
        managementGroup.Add(secretaryGrandma);

        var marketingGroup = new Group("Marketing", "Donald Duck");
        marketingGroup.Add(workerGus);

        var softwareGroup = new Group("Software Development", "Daisy Duck");
        softwareGroup.Add(workerHuey);
        softwareGroup.Add(workerDewey);
        softwareGroup.Add(workerLouie);

        var cutomerGroup = new Group("Customer Support", "Gladstone Gander");
        cutomerGroup.Add(workerGyro);
        cutomerGroup.Add(workerMagica);
        cutomerGroup.Add(workerLaunchpad);

        managementGroup.Add(marketingGroup);
        managementGroup.Add(softwareGroup);
        managementGroup.Add(cutomerGroup);

        _company = managementGroup;
        _groupList = Arrays.asList(managementGroup, marketingGroup, softwareGroup, cutomerGroup);

    }

    private static boolean addWorker(Worker worker, String unit){
        for (int i = 0; i < _groupList.size(); i++) {
            var group = _groupList.get(i).getGroupName();
            if(Objects.equals(group, unit)){
                _groupList.get(i).Add(worker);
                return true;
            }
        }
        return false;
    }
    private static boolean removeWorker(Worker worker){
        for (int i = 0; i < _groupList.size(); i++) {
            var group = _groupList.get(i);
            if(group.remove(worker)){
                return true;
            }
        }
        return false;
    }

    private static void printCompany(){
        if(_company == null){
            return;
        }

        Printer.setIndent(0);
        _company.show();
    }

    private static void wrongInputError(){
        Printer.printLine("ERROR: Invalid input. Please enter " + "1, 2, 3, q or Q" + ".");
    }
    private static void noOrganizationError(){
        Printer.printLine("ERROR: Organization is not created yet. Create it first in step 1.");
    }
    private static void organizationNotFoundError(){
        Printer.printLine("ERROR: Organization not found. Give it again.");
    }
    private static void invalidNameError(){
        Printer.printLine("ERROR: Invalid name. Please enter a valid name like John Smith.");
    }
    private static void personNotFoundError(){
        Printer.printLine("ERROR: Person not found. Give it again.");
    }
}
