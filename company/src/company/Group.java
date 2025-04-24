package company;

import java.util.ArrayList;
import java.util.Objects;

public class Group implements Component{
    private final String _groupName;
    private final String _bossName;
    private ArrayList<Component> _components = new ArrayList<>();

    public Group(String groupName, String bossName){
        _groupName = groupName;
        _bossName = bossName;
    }

    public String getGroupName(){
        return _groupName;
    }

    public void Add(Component component){
        _components.add(component);
    }
    public boolean remove(Worker worker){
        var workerName = worker.getName();
        var workerToRemove = new Worker("");
        var workerFound = false;

        for (int i = 0; i < _components.size(); i++) {
            if(_components.get(i).getClass() == Group.class){
                continue;
            }
            var selectedWorker = (Worker)_components.get(i);
            if(Objects.equals(selectedWorker.getName(), workerName)){
                workerToRemove = selectedWorker;
                workerFound = true;
                break;
            }
        }

        _components.remove(workerToRemove);
        return workerFound;
    }

    @Override
    public void show() {
        if(_components.isEmpty()) return;

        var groupFound = false;
        Printer.printLine("");

        Printer.printGroup("Group: " + _groupName + ", boss's name: " + _bossName);

        for (int i = 0; i < _components.size(); i++) {
            var component = _components.get(i);
            if(component.getClass() == Group.class && !groupFound){
                Printer.addIndent(2);
                groupFound = true;
            }
            component.show();
        }
    }
}
