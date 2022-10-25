package TP2;

public class NotExistingValueException extends Exception{
    public NotExistingValueException(){
        super();
    }
    public NotExistingValueException(String message){
        super(message);
    }
    @Override
    public String toString(){
        return "The following Exception was thrown " + this.getClass().getName() +"\n" +
                " Message: " + this.getMessage() + "\n" ;
    }
}
