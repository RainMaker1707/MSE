package features.conversation;


import database.Features;
import behaviour.FeatureBehavior;
import features.profile.Profile;


import java.util.List;
import java.util.ArrayList;

public class GroupList{
    private final Profile owner;
    List<Group> groupList;
    FeatureBehavior behavior = Features.INSTANCE.get("listgroup");

    public GroupList(Profile profile){
        this.owner = profile;
        this.groupList = new ArrayList<>();
    }

    public List<Group> getGroups() {
        return this.groupList;
    }

    public void addGroup(Group group){
        this.groupList.add(group);
    }
    public void removeGroup(Group group){
        this.groupList.remove(group);
    }

    public List<String> getGroupsName() {
        List<String> groupNames = new ArrayList<>();
        for (Group group : this.getGroups()) {
            groupNames.add(group.getGroupName());
        }
        return groupNames;
    }

    public Group getGroup(String name) {
        return this.groupList.stream()
                .filter(group -> group.getGroupName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        StringBuilder groupsInfo = new StringBuilder();
        groupsInfo.append("[");
        for (Group group : this.getGroups()) {
            groupsInfo.append(group.getGroupName());
            groupsInfo.append(", ");
        }
        if (groupsInfo.length() > 2) groupsInfo.delete(groupsInfo.length() - 2, groupsInfo.length());
        groupsInfo.append("]");
        return groupsInfo.toString();
    }
}
