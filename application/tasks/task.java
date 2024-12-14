package application.tasks;

public class task {
    
    /*data fields*/
    private int id; /*Primary key should be set by db*/
    private String taskName;
    private String taskDescription;


    /*Character limit for strings*/
    private final int nameCharacterLimit = 255;
    private final int descriptionCharacterLimit = 500;

    


    /*Getters*/
    public int getId()
    {
        return id;
    }

    public String getTaskName()
    {
        return taskName;
    }

    public String getTaskDescription()
    {
        return taskDescription;
    }

    public int getNameCharacterLimit()
    {
        return nameCharacterLimit;
    }

    public int getDescriptionCharacterLimit()
    {
        return descriptionCharacterLimit;
    }

    /*Setters*/
    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }

    public void setTaskDescription(String taskDescription)
    {
        this.taskDescription = taskDescription;
    }
  
}
