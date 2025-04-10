package company;

public class Worker implements Component{
    private final String _name;
    private String _role;
    public Worker(String name){
        _name = name;
    }
    public void setRole(String role){
        _role = role;
    }
    @Override
    public void show() {
        Printer.printWorker(_role != null ? "Worker: " + _name + " (" + _role + ")" : "Worker: " + _name, 2);
    }
}
