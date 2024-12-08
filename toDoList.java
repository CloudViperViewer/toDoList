import server.server;
import application.application;

public class toDoList
{

    

    public static void main(String args[])
    {
        //Application
        application app;
        //Create server
        server myServer = new server();
        


        //start server
        try{
            myServer.start();
        }
        catch(Exception error)
        {
            System.out.println(error.getMessage());
        }

        app = new application(myServer);
        app.start();

    }
}