package TP2;

public class NotExistingKeyExeption extends Exception{

    public NotExistingKeyExeption(){
        super();
    }
    public NotExistingKeyExeption(String message){
        super(message);
    }

    @Override
    public String toString(){
        return "The following Exception was thrown " + this.getClass().getName() +"\n" +
                " Message: " + this.getMessage() + "\n" ;
    }
}
