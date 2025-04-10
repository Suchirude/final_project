package company;

import java.util.ArrayList;

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
