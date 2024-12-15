function callAddTask()
{
    const xhr = new XMLHttpRequest();

    xhr.open("get", "/api/v1/create-task");
    xhr.send();
    console.log("Create new task")
}