package Exception;

public class OutOfResourcesException extends Exception{
    public OutOfResourcesException() {
        System.out.println("OutOfResourcesException 梓端 持失");
    }

    public OutOfResourcesException(String msg) {
        super(msg);
        System.out.println("OutOfResourcesException 梓端 持失");
    }
}