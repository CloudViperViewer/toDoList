package server;


import java.io.OutputStream;
import java.util.Map;
import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class serverUtils {
    
      /**
+     * Maps file extensions to their corresponding MIME types with UTF-8 charset.
+     * Used for setting Content-Type headers in HTTP responses.
+     */
    public static final Map<String, String> CONTENT_TYPES = Map.of(
        "html", "text/html; charset=UTF-8",
        "css", "text/css; charset=UTF-8",
        "js", "application/javascript; charset=UTF-8",
        "txt", "text/plain; charset=UTF-8"
    );

    public static HttpHandler createHandle(createHttpHandle handle)
    {
        return exchange -> handle.create(exchange);
    }


    public static void sendResponse(HttpExchange exchange, int responseCode, byte[] content, String contentType) throws IOException
    {
        //Check for null parameters
        if (exchange == null ||  content == null || contentType == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }

        if (responseCode < 100 || responseCode > 599) {
            throw new IllegalArgumentException("Invalid response code");
        }
        
        exchange.getResponseHeaders().add("Content-Type", contentType);
        exchange.sendResponseHeaders(responseCode, content.length);
            
        //Send response and flush
        try(OutputStream os = exchange.getResponseBody())
        {
            os.write(content);
            os.flush();
        }
    }
}
