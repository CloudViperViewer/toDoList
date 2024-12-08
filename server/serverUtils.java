package server;


import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class serverUtils {
    

    public static HttpHandler createHandle(createHttpHandle handle)
    {
        return exchange -> handle.create(exchange);
    }


    public static void sendResponse(HttpExchange exchange, int responseCode, byte[] content, String contentType) throws Exception
    {
            exchange.getResponseHeaders().add("Content-Type", contentType);
            exchange.sendResponseHeaders(responseCode, content.length);
            OutputStream os = exchange.getResponseBody();
            os.write(content);
    }
}
