
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class HelloWorld {

    static Boolean running = true;
    static ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
    public static void main(String[] args){
        Queue<String> myQue = new LinkedList<String>();
        
        String myS = "\\n234\\m789";

        System.out.println(myS.substring(2,myS.indexOf("\\", 2)));
        System.out.println(myS.substring(myS.indexOf("\\", 2) + 2));
        String us ="username";
        String msg = "\\uMyName\\mMessage";

        

    }
}
