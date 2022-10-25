package TP2;

public class RepeatedValuesException extends Exception{

    public RepeatedValuesException(){
        super();
    }
    public RepeatedValuesException(String message){
        super(message);
    }
    @Override
    public String toString(){
        return "The following Exception was thrown " + this.getClass().getName() +"\n" +
                " Message: " + this.getMessage() + "\n" ;
    }
}
