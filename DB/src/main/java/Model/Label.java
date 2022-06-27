package Model;

public class Label {
    private int labelId;
    private String labelTitle;
    private int GroupTask;
    private String labelColor;
    private int userId;

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public String getLabelTitle() {
        return labelTitle;
    }

    public void setLabelTitle(String labelTitle) {
        this.labelTitle = labelTitle;
    }

    public int getGroupTask() {
        return GroupTask;
    }

    public void setGroupTask(int groupTask) {
        GroupTask = groupTask;
    }

    public String getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(String labelColor) {
        this.labelColor = labelColor;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
