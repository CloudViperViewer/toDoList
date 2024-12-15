package application.tasks;




public class Task {
    
    /*data fields*/
    private int id; /*Primary key should be set by db*/
    private String taskName; /*Cannot be null*/
    private String taskDescription; 


    /*Character limit for strings*/

    /*
     * Maximum Character limit for Task Name
     */
    private final int NAME_CHARACTER_LIMIT = 255;

    /*
     * Maximum Character limit of Task Description
     */
    private final int DESCRIPTION_CHARACTER_LIMIT = 500;

    
    /*Constructors*/
    
    /*Default constructor*/
    public Task(){};

    /*
     * Constructor with taskName set
     * @param taskName - new Task Name;
     */
    public Task(String taskName)
    {
        setTaskName(taskName);
    }

    /*
     * Constructor with taskNAme and taskDescription set
     * @param taskName - new task name
     * @param taskDescription - new task description
     */
    public Task(String taskName, String taskDescription)
    {
        setTaskName(taskName);
        setTaskDescription(taskDescription);
    }



    /*Getters*/

    /*
     * @return The Unique Identifier of the task
     */
    public int getId()
    {
        return id;
    }

    /*
     * @return The task Name
     */
    public String getTaskName()
    {
        return taskName;
    }
    
    /*
     * @return The task Description
     */
    public String getTaskDescription()
    {
        return taskDescription;
    }

    /*
     * @return The Character limit of the name
     */
    public int getNameCharacterLimit()
    {
        return NAME_CHARACTER_LIMIT;
    }

    /*
     * @return The Description Character limit
     */
    public int getDescriptionCharacterLimit()
    {
        return DESCRIPTION_CHARACTER_LIMIT;
    }

    /*Setters*/

    /*
     * Sets task name
     * @param taskName - new Name of task
     * @return void
     */
    public void setTaskName(String taskName)
    {
        if(taskName == null)
        {
            throw new IllegalArgumentException("Task name cannot be null");
            
        }

        if(taskName.length() > NAME_CHARACTER_LIMIT)
        {
            throw new IllegalArgumentException(String.format("Task name cannot exceed %d characters", NAME_CHARACTER_LIMIT));
            
        }

        this.taskName = taskName;
    }

    /*
     * Set task description
     * @param taskDescription - new description of task
     * @return void
     */
    public void setTaskDescription(String taskDescription)
    {
        if(taskName.length() > DESCRIPTION_CHARACTER_LIMIT)
        {
            throw new IllegalArgumentException(String.format("Task description cannot exceed %d characters", DESCRIPTION_CHARACTER_LIMIT));
            
        }

        this.taskDescription = taskDescription;
    }
  
}
