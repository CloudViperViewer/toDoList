function callAddTask()
{
    const xhr = new XMLHttpRequest();

    xhr.open("get", "tasks/createTask");
    xhr.send();
    console.log("Create new task")
}