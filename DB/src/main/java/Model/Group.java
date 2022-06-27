package Model;

public class Group {
    private int groupId;
    private String groupName;
    private int groupLeader;
    private String groupDescription;
    private int groupLabel;

    public Group() {
    }

    public Group(int id, String name, int leader, String description) {
        this.groupId = id;
        this.groupName = name;
        this.groupLeader = leader;
        this.groupDescription = description;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupLeader() {
        return groupLeader;
    }

    public void setGroupLeader(int groupLeader) {
        this.groupLeader = groupLeader;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public int getGroupLabel() {
        return groupLabel;
    }

    public void setGroupLabel(int groupLabel) {
        this.groupLabel = groupLabel;
    }
}
