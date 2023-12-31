package learn.learn.TaskManager.ui;

import learn.learn.TaskManager.models.Status;
import learn.learn.TaskManager.models.Task;

import java.util.List;
import java.util.Scanner;

public class View {
    Scanner console = new Scanner(System.in);

    public int getMenuOption(){
        displayHeader("Welcome to the Main Menu");
        displayText("1. add a task.");
        displayText("2. view tasks.");
        displayText("3. update a task.");
        displayText("4. delete a task.");
        displayText("5. exit");
        return readInt("What would you like to d0?[1-5]",1,5);
    }

    public Task makeTask(){

        Task res = new Task();
        res.setCreatedOn(readString("Enter today's date: "));
        res.setTitle(readString("Enter Title: "));
        res.setDescription(readString("Enter description: "));
        res.setDueDate(readString("Enter due date: "));
        res.setStatus(readStatus("Enter Status: "));
        return res;
    }
    public void displayTasks(List<Task> tasks) {
              for (Task task : tasks){
            displayText(String.format(
                    "id: %s%n date: %s%n title: %s%n description: %s%n due date: %s%n status: %s%n",
            task.getId(),
            task.getCreatedOn(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getDueDate(),
                    task.getStatus()));
        }
    }
    public int updateById(){
        displayText("Which Id would you like to modify?");
        int id = readInt("Enter id: ", 1, 999999);
        return id;
    }

    public void displayHeader(String header){
        System.out.println();
        System.out.println(header);
        System.out.println("*".repeat(header.length()));
    }
//fixed
    public void displayText(String line){
        System.out.println();
        System.out.println(line);
    }
    public void displayErrors(List<String> errs){
        displayHeader("Errors:");
        for(String err : errs){
            displayText(err);
        }
        displayText("");
    }

    public String readString(String prompt){
        displayText(prompt);
        String string = console.nextLine();
        if(string == null || string.isBlank()){
            displayText("You must enter a value");
            string = readString(prompt);
        }
        return string;
    }
    public int readInt(String prompt, int min, int max){
        while(true){
            String val = readString(prompt);
            try{
                int intVal = Integer.parseInt(val);
                if(intVal < min || intVal > max){
                    System.out.printf("Not valid, please choose value between %s and %s%n", min, max);
                }else{
                    return intVal;
                }
            }catch(NumberFormatException e){
                System.out.printf("%s is not a valid number%n", val);
            }
        }
    }

    public Status readStatus(String prompt){
        displayHeader("Task Status:");
        for(Status status: Status.values()){
            displayText(status.getDisplayText());
        }
        while(true){
            String selection = readString(prompt);
            selection = selection.toUpperCase();
            try{
                return Status.valueOf(selection);
            }catch(IllegalArgumentException e){
                System.out.printf("%s is not a status%n", selection);
            }
        }
    }
}
