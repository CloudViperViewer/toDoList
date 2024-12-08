package server;


import com.sun.net.httpserver.HttpHandler;

public class serverUtils {
    

    public static HttpHandler createHandle(createHttpHandle handle)
    {
        return exchange -> handle.create(exchange);
    }
}
