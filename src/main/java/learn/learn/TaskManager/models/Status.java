package learn.learn.TaskManager.models;

public enum Status {
    TODO("To Do"),
    IN_PROGRESS("In Progress"),
    COMPLETE("Completed");
     private String displayText;
     Status(String displayText){
         this.displayText = displayText;
     }

    public String getDisplayText() {
        return displayText;
    }
}
