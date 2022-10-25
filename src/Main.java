import TP2.*;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) throws ExistingKeyException, NotExistingKeyExeption, RepeatedValuesException, NotExistingValueException {
        Person user1 = new Person("Agus");
        Person user2 = new Person("Juan");
        Person user3 = new Person("Pepe");
        Person user4 = new Person("Luis");
        Pet pet1 = new Pet("Capri");
        Pet pet2 = new Pet("Lola");
        Pet pet3 = new Pet("Pistachio");
        Pet pet4 = new Pet("Coco");

        EpicDoubleHashMap<Integer, Person, Pet> map1 = new EpicDoubleHashMap<>();

        map1.addValueV(1, user1);
        map1.addValueT(2, pet1);
        map1.addValues(3, user2, pet2);
        map1.addValues(4, user3, pet3);
        map1.addValues(5, user4, pet4);
        map1.deleteItem(5);
        map1.addValues(6, user4, pet4);
        map1.addValues(7, user4, pet4);
        map1.addValues(8, user4, pet4);

        System.out.println(map1.haveRepeatedValues());
        System.out.println(map1.getValueV(3));
        System.out.println(map1.getValueT(3));

        //Metodos auxiliares
        System.out.println(map1.typeMostEntered()); // a
        System.out.println(map1.repeatedValues(4)); // b
        System.out.println(map1.haveRepeatedValues()); // c

    /* EXCEPCIONES */
    //a. Añadir un item con una key ya existente
        try {
            map1.addValues(3, user1, pet1);
        }
        catch (ExistingKeyException e) {
            System.err.println(e);
        }
    //b. Remover un item con una key inexistente ==>> SI FUNCIONA
       try {
            map1.deleteItem(5);
        }
        catch (NotExistingKeyExeption e){
            System.err.println(e);
        }
    //c. Añadir un item con un valor/valores que se repita/repitan por lo menos 3 veces
        try {
            map1.addValues(9, user4, pet4);
        }
        catch (RepeatedValuesException e){
            System.err.println(e);
        }
    //d. Tratar de obtener el valor V o T de una key, sin que este exista. (Esto se da en caso de que se haya insertado por ejemplo, una key y un valor V, y se esté pidiendo un valor T)"
        try {
            System.out.println(map1.getValueV(2));
        }
        catch (NotExistingKeyExeption e){
            System.err.println(e);
        }
        System.out.println(map1);
    }
}


