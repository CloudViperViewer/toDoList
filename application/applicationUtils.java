package application;

public class applicationUtils {
    
    

    public static String getFileExtension(String filePath)
    {

        if (filePath == null) {
            return "";
        }

        int extIndex = filePath.lastIndexOf(".");

        if (extIndex != -1 && extIndex <= filePath.length()) {
            return filePath.substring(extIndex+1);
        }

        return "";

    }   
}
