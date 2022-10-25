package TP2;

import java.util.*;

public class EpicDoubleHashMap<K extends Number, V, T>{
    private Map<K, V> map1;
    private Map<K, T> map2;

    public EpicDoubleHashMap(){
        this.map1 = new HashMap<>();
        this.map2 = new HashMap<>();
    }
//Funciones que permitan agregar para dada una key, un valor o dos dependiendo de lo que se quiera

    //Con un solo valor
    public void addValueV(K key, V value) throws ExistingKeyException, RepeatedValuesException {
        if (map1.containsKey(key) || map2.containsKey(key)){
            throw new ExistingKeyException("It is not allowed to add items whose key already exists in the map");
        }
        if (this.repeatedValuesV(value)  >= 3){
            throw new RepeatedValuesException("The map already have 3 times this value repeated");
        }
        map1.put(key, value);
    }
    public void addValueT(K key, T value) throws ExistingKeyException, RepeatedValuesException {
        if (map1.containsKey(key) || map2.containsKey(key)){
            throw new ExistingKeyException("It is not allowed to add items whose key already exists in the map");
        }
        if (this.repeatedValuesT(value) >= 3){
            throw new RepeatedValuesException("The map already have 3 times this value repeated");
        }
        else {
            map2.put(key, value);
        }
    }
    //Con dos valores
    public void addValues(K key, V value1, T value2) throws ExistingKeyException, RepeatedValuesException {
        if (map1.containsKey(key) || map2.containsKey(key)){
            throw new ExistingKeyException("It is not allowed to add items whose key already exists in the map");
        }
        if (this.repeatedValuesV(value1)  >= 3){
            throw new RepeatedValuesException("The map already have 3 times this value repeated");
        }
        if (this.repeatedValuesT(value2)  >= 3){
            throw new RepeatedValuesException("The map already have 3 times this value repeated");
        }
        map1.put(key, value1);
        map2.put(key, value2);
    }
//Dos funciones para devolver el valor V o T
    public V getValueV(K key) throws NotExistingKeyExeption{
        if (!map1.containsKey(key)){
            throw new NotExistingKeyExeption("The key does not exist or does not have this type of value");
        }
        return map1.get(key);
    }
    public T getValueT(K key) throws NotExistingKeyExeption{
        if (!map2.containsKey(key)){
            throw new NotExistingKeyExeption("The key does not exist or does not have this type of value");
        }
        return map2.get(key);
    }
//Una función para remover el item que contenga un key en específico.
    public void deleteItem(K key) throws NotExistingKeyExeption{
        if (!map2.containsKey(key) && !map1.containsKey(key)) {
            throw new NotExistingKeyExeption("It is not possible to delete an item, whose key is non-existent");
        }
        if (map1.containsKey(key)) {
            map1.remove(key);
        }
        if (map2.containsKey(key)) {
            map2.remove(key);
        }
    }
 //Un método que printee si existen más valores de tipo T, V o si tienen la misma cantidad
    /*Se intento que en los return devuelva el nombre del tipo de objeto contenido, pero no pudimos encontrar el metodo correcto. Ya que usando
    * .getClass().getSimpleName() nos devolvia "Values", en vez de "Person o Pet"*/
    public String typeMostEntered(){
        Collection<V> valuesMap1 = map1.values();
        Collection<T> valuesMap2 = map2.values();

        if (valuesMap1.size() > valuesMap2.size()){
            return "Most of the values contained in the double epic hashmap are of type V";
        }
        else if (valuesMap2.size() > valuesMap1.size()){
            return "Most of the values contained in the double epic hashmap are of type T";
        }
        return "It has the same amount of values of type 'T' and 'V'";
    }

//c. Un método booleano que devuelva true si existen values repetidos, y false en caso contrario
    public boolean haveRepeatedValues(){
        //convierto los valores de ambos en un Array. Para luego ingresarlos a un Set inicializado vacio, que no permite valores duplicados.
        List<V> listMap1 = new ArrayList<>(map1.values());
        List<T> listMap2 = new ArrayList<>(map2.values());
        Set<V> setMap1 = new HashSet<>();
        Set<T> setMap2 = new HashSet<>();
        boolean list1HasRepeatedValues = false;
        boolean list2HasRepeatedValues = false;
        for (V value: listMap1) {
            // devuelve verdadero si se encuentra un duplicado
            if (setMap1.contains(value)){
                list1HasRepeatedValues = true;
                break;
            }
            if (value != null){
                setMap1.add(value);
            }
        }
        for (T value: listMap2) {
            // devuelve verdadero si se encuentra un duplicado
            if (setMap2.contains(value)){
                list2HasRepeatedValues = true;
                break;
            }
            if (value != null){
                setMap2.add(value);
            }
        }
        return (list1HasRepeatedValues || list2HasRepeatedValues);
    }
//b. Un método que dado una key, devuelva cuantos valores existen iguales al valor asociado a la key.
    private int repeatedValuesV(K key) {
        List<V> listMap1 = new ArrayList<>(map1.values());
        V value1 = map1.get(key);
        int count = 0;
        if (value1 != null) {
            for (V value : listMap1) {
                if (value1.equals(value)) {
                    count++;
                }
            }
        }
        return count;
    }
    private int repeatedValuesV(V value1) {
        List<V> listMap1 = new ArrayList<>(map1.values());
        int count = 0;
        if (value1 != null) {
            for (V value : listMap1) {
                if (value1.equals(value)) {
                    count++;
                }
            }
        }
        return count;
    }
    private int repeatedValuesT(K key) {
        List<T> listMap2 = new ArrayList<>(map2.values());
        T value2 = map2.get(key);
        int count = 0;
        if(value2 != null){
            for (T value: listMap2) {
                if (value2.equals(value)){
                    count++;
                }
            }
        }
        return count;
    }
    private int repeatedValuesT(T value2) {
        List<T> listMap2 = new ArrayList<>(map2.values());
        int count = 0;
        if(value2 != null){
            for (T value: listMap2) {
                if (value2.equals(value)){
                    count++;
                }
            }
        }
        return count;
    }
    //b. Un método que dado una key, devuelva cuantos valores existen iguales al valor asociado a la key. (De vuelta, es necesario la implementación de equals en las clases que generalicen V y T)
    public String repeatedValues(K key) {
        return "Given the key: "+key.toString()+" exists "+repeatedValuesV(key)+" values type V and "+repeatedValuesT(key)+" values type T";
    }
    @Override
    public String toString() {
        String result = "EpicDoubleHashMap \n";

        Set<K> keys = new HashSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());

        for(K key : keys){
            result+= key.toString() + " : " +
                    (map1.get(key) != null ? map1.get(key).toString() + " " : "" )+
                    (map2.get(key) != null ? map2.get(key).toString() : "" )+
                    "\n";
        }
        return result;
    }
}
