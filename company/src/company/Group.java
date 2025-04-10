package company;

import java.util.ArrayList;

public class Group implements Component{
    private String _groupName;
    private String _bossName;
    private ArrayList<Component> _members = new ArrayList<Component>();

    public Group(String groupName, String bossName){
        _groupName = groupName;
        _bossName = bossName;
    }

    public void Add(Component component){
        _members.add(component);
    }

    @Override
    public void show() {

    }
}
