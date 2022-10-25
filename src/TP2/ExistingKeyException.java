package TP2;

public class ExistingKeyException extends Exception{

    public ExistingKeyException(){
        super();
    }
    public ExistingKeyException(String message){
        super(message);
    }
    @Override
    public String toString(){
        return "The following Exception was thrown " + this.getClass().getName() +"\n" +
                " Message: " + this.getMessage() + "\n" ;
    }
}
