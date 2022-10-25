package TP2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class EpicDoubleHashMapTest {
// =====================  METODOS ==========================

    // METODO BOOLEANO PARA VALORES REPETIDOS
    /* valores vacios */
    @Test
    void haveRepeatedValue_empty() {
        EpicDoubleHashMap<Integer,Person,Pet> map =  new EpicDoubleHashMap<>();
        assertFalse(map.haveRepeatedValues());
    }
    /* valores NO repetidos */
    @Test
    void haveRepeatedValue_false() throws ExistingKeyException, RepeatedValuesException, NotExistingKeyExeption {
        Person user1 = new Person("Agus");
        Pet pet1 = new Pet("Capri");

        EpicDoubleHashMap<Integer,Person,Pet> map =  new EpicDoubleHashMap<>();
        map.addValueV(6, user1);
        map.addValueT(11, pet1);

        assertFalse(map.haveRepeatedValues());
    }
    /* Valores repetidos de tipo V */
    @Test
    void haveRepeatedValue_true_repeated_user() throws ExistingKeyException, RepeatedValuesException, NotExistingKeyExeption {
        Person user1 = new Person("Agus");
        Pet pet1 = new Pet("Capri");

        EpicDoubleHashMap<Integer,Person,Pet> map =  new EpicDoubleHashMap<>();
        map.addValueV(1, user1);
        map.addValueV(2, user1);
        map.addValueT(11, pet1);
        assertTrue(map.haveRepeatedValues());
    }
    /* Valores repetidos de tipo T */
    @Test
    void haveRepeatedValue_true_repeated_pet() throws ExistingKeyException, RepeatedValuesException, NotExistingKeyExeption {
        Person user1 = new Person("Agus");
        Pet pet1 = new Pet("Capri");

        EpicDoubleHashMap<Integer,Person,Pet> map =  new EpicDoubleHashMap<>();
        map.addValueV(1, user1);
        map.addValueT(11, pet1);
        map.addValueT(12, pet1);
        assertTrue(map.haveRepeatedValues());
    }
    /* Valores repetidos de AMBOS tipos */
    @Test
    void haveRepeatedValue_true_repeated_both() throws ExistingKeyException, RepeatedValuesException {
        Person user1 = new Person("Agus");
        Pet pet1 = new Pet("Capri");

        EpicDoubleHashMap<Integer,Person,Pet> map =  new EpicDoubleHashMap<>();
        map.addValues(1, user1, pet1);
        map.addValues(2, user1, pet1);

        assertTrue(map.haveRepeatedValues());
    }

    // METODO PARA DEVOLVER V o T DADA UNA KEY
    /* Pido V devuelve V */
    @Test
    void getValueV_ok() throws ExistingKeyException, RepeatedValuesException, NotExistingKeyExeption {
        //given:
        Person user1 = new Person("Agus");
        EpicDoubleHashMap<Integer,Person,Pet> map = new EpicDoubleHashMap<>();
        map.addValueV(1, user1);
        //when:
        Person result = map.getValueV(1);
        //then:
        assertTrue(result.equals(user1));
    }
    /* Pido V cuando almacena un valor T */
    @Test
    void getValueV_throws_not_existing_value_exception() throws ExistingKeyException, RepeatedValuesException, NotExistingKeyExeption {
        //given:
        Pet pet1 = new Pet("Capri");
        EpicDoubleHashMap<Integer,Person,Pet> map = new EpicDoubleHashMap<>();
        map.addValueT(1, pet1);
        //when:
        Executable result = () -> map.getValueV(1);
        //then:
        assertThrows(NotExistingKeyExeption.class, result);
    }
    /* Pido T devuelve T*/
    @Test
    void getValueT_ok() throws ExistingKeyException, RepeatedValuesException, NotExistingKeyExeption, NotExistingValueException {
        //given:
        Pet pet1 = new Pet("Capri");
        EpicDoubleHashMap<Integer,Person,Pet> map = new EpicDoubleHashMap<>();
        map.addValueT(1, pet1);
        //when:
        Pet result = map.getValueT(1);
        //then:
        assertTrue(result.equals(pet1));
    }
    /* Pido T cuando almacena un valor V */
    @Test
    void getValueT_throws_not_existing_value_exception() throws ExistingKeyException, RepeatedValuesException, NotExistingKeyExeption {
        //given:
        Person user1 = new Person("Agus");
        EpicDoubleHashMap<Integer,Person,Pet> map = new EpicDoubleHashMap<>();
        map.addValueV(1, user1);
        //when:
        Executable result = () -> map.getValueT(1);
        //then:
        assertThrows(NotExistingKeyExeption.class, result);
    }

    // METODO PARA REMOVER UN ITEM DADA UNA KEY
    /* Elimino un item que contiene solo valor V */
    @Test
    void deleteItem_v() throws NotExistingKeyExeption, ExistingKeyException, RepeatedValuesException {
        //given:
        Person user1 = new Person("Agus");
        EpicDoubleHashMap<Integer,Person,Pet> map = new EpicDoubleHashMap<>();
        map.addValueV(1, user1);
        //when:
        map.deleteItem(1);
        //then:
        assertThrows(NotExistingKeyExeption.class, ()->map.getValueV(1));
        assertThrows(NotExistingKeyExeption.class, ()->map.getValueT(1));
    }
    /* Elimino un item que contiene solo valor T */
    @Test
    void deleteItem_t() throws NotExistingKeyExeption, ExistingKeyException, RepeatedValuesException {
        //given:
        Pet pet1 = new Pet("Capri");
        EpicDoubleHashMap<Integer,Person,Pet> map = new EpicDoubleHashMap<>();
        map.addValueT(1, pet1);
        //when:
        map.deleteItem(1);
        //then:
        assertThrows(NotExistingKeyExeption.class, ()->map.getValueV(1));
        assertThrows(NotExistingKeyExeption.class, ()->map.getValueT(1));
    }
    /* Elimino un item que contiene dos valores, V y T */
    @Test
    void deleteItem_v_t() throws NotExistingKeyExeption, ExistingKeyException, RepeatedValuesException {
        //given:
        Person user1 = new Person("Agus");
        Pet pet1 = new Pet("Capri");
        EpicDoubleHashMap<Integer,Person,Pet> map = new EpicDoubleHashMap<>();
        map.addValues(2, user1, pet1);
        //when:
        map.deleteItem(2);
        //then:
        assertThrows(NotExistingKeyExeption.class, ()->map.getValueV(2));
        assertThrows(NotExistingKeyExeption.class, ()->map.getValueT(2));
    }

    // METODO QUE ME DICE CUANTOS VALORES IGUALES EXISTEN AL VALOR ASOCIADO A LA KEY
    /* Se repiten tanto valores de tipo V como T*/
    @Test
    void repeatedValues() throws ExistingKeyException, RepeatedValuesException, NotExistingKeyExeption {
        // given:
        Person user1 = new Person("Agus");
        Pet pet1 = new Pet("Capri");
        EpicDoubleHashMap<Integer,Person,Pet> map = new EpicDoubleHashMap<>();
        map.addValues(1, user1, pet1);
        map.addValues(2, user1, pet1);
        map.addValues(3, user1, pet1);
        //when:
        String result = map.repeatedValues(1);
        //then:
        assertEquals("Given the key: 1 exists 3 values type V and 3 values type T", result);
    }
    /* Se repiten solo valores de tipo V */
    @Test
    void repeatedValues_only_v_values() throws ExistingKeyException, RepeatedValuesException, NotExistingKeyExeption {
        //given:
        Person user1 = new Person("Agus");
        EpicDoubleHashMap<Integer,Person,Pet> map = new EpicDoubleHashMap<>();
        map.addValueV(1, user1);
        map.addValueV(2, user1);
        map.addValueV(3, user1);
        //when:
        String result = map.repeatedValues(1);
        //then:
        assertEquals("Given the key: 1 exists 3 values type V and 0 values type T", result);
    }
    /* Se repiten solo valores de tipo T */
    @Test
    void repeatedValues_only_t_values() throws ExistingKeyException, RepeatedValuesException, NotExistingKeyExeption {
        //given:
        Pet pet1 = new Pet("Capri");
        EpicDoubleHashMap<Integer,Person,Pet> map = new EpicDoubleHashMap<>();
        map.addValueT(1, pet1);
        map.addValueT(2, pet1);
        map.addValueT(3, pet1);
        //when:
        String result = map.repeatedValues(1);
        //then:
        assertEquals("Given the key: 1 exists 0 values type V and 3 values type T", result);
    }
    /* No se repite ningun valor */
    @Test
    void notRepeatedValues() throws ExistingKeyException, RepeatedValuesException {
        //given:
        Pet pet1 = new Pet("Capri");
        Pet pet2 = new Pet("Pistachio");
        Person user1 = new Person("Agus");
        Person user2 = new Person("Juan");
        EpicDoubleHashMap<Integer,Person,Pet> map = new EpicDoubleHashMap<>();
        map.addValues(1, user1, pet1);
        map.addValues(2, user2, pet2);
        //when:
        String result = map.repeatedValues(1);
        //then:
        assertEquals("Given the key: 1 exists 1 values type V and 1 values type T", result);
    }

    //=============================== EXCEPCIONES ============================

    //AGREGAR UN ITEM CON UNA KEY EXISTENTE
    /* Agregando 2 valores */
    @Test
    void addValues_ExistingKeyException() throws ExistingKeyException, RepeatedValuesException {
        //given:
        Pet pet1 = new Pet("Capri");
        Pet pet2 = new Pet("Pistachio");
        Person user1 = new Person("Agus");
        Person user2 = new Person("Juan");
        EpicDoubleHashMap<Integer,Person,Pet> map = new EpicDoubleHashMap<>();
        map.addValues(1, user1, pet2);
        //then:
        assertThrows(ExistingKeyException.class, ()->map.addValues(1, user2, pet1));
    }
    /* Agregando item valor V */
    @Test
    void addValueV_ExistingKeyException() throws ExistingKeyException, RepeatedValuesException {
        //given:
        Person user1 = new Person("Agus");
        Person user2 = new Person("Juan");
        EpicDoubleHashMap<Integer,Person,Pet> map = new EpicDoubleHashMap<>();
        map.addValueV(1, user1);
        //then:
        assertThrows(ExistingKeyException.class, ()->map.addValueV(1, user2));
    }
    /* Agregando item valor T */
    @Test
    void addValueT_ExistingKeyException() throws ExistingKeyException, RepeatedValuesException {
        //given:
        Pet pet1 = new Pet("Capri");
        Pet pet2 = new Pet("Pistachio");
        EpicDoubleHashMap<Integer,Person,Pet> map = new EpicDoubleHashMap<>();
        map.addValueT(1, pet2);
        //then:
        assertThrows(ExistingKeyException.class, ()->map.addValueT(1, pet1));
    }

    //AGREGAR UN ITEM CON UN VALOR QUE SE REPITA POR LO MENOS 3 VECES
    /* Se repite 3 veces el valor V */
    @Test
    void addValueV_RepeatedValuesException() throws ExistingKeyException, RepeatedValuesException {
        //given:
        Person user1 = new Person("Agus");
        EpicDoubleHashMap<Integer,Person,Pet> map = new EpicDoubleHashMap<>();
        map.addValueV(1, user1);
        map.addValueV(2, user1);
        map.addValueV(3, user1);
        //then:
        assertThrows(RepeatedValuesException.class, ()->map.addValueV(4, user1));
    }
    /* Se repite 3 veces el valor T */
    @Test
    void addValueT_RepeatedValuesException() throws ExistingKeyException, RepeatedValuesException {
        //given:
        Pet pet1 = new Pet("Capri");
        EpicDoubleHashMap<Integer,Person,Pet> map = new EpicDoubleHashMap<>();
        map.addValueT(1, pet1);
        map.addValueT(2, pet1);
        map.addValueT(3, pet1);
        //then:
        assertThrows(RepeatedValuesException.class, ()->map.addValueT(4, pet1));
    }
    /* Se repite 3 veces AMBOS valores */
    @Test
    void addValues_RepeatedValuesException() throws ExistingKeyException, RepeatedValuesException {
        //given:
        Person user1 = new Person("Agus");
        Pet pet1 = new Pet("Capri");

        EpicDoubleHashMap<Integer,Person,Pet> map = new EpicDoubleHashMap<>();
        map.addValues(1, user1, pet1);
        map.addValues(2, user1, pet1);
        map.addValues(3, user1, pet1);
        //then:
        assertThrows(RepeatedValuesException.class, ()->map.addValueV(4, user1));
        assertThrows(RepeatedValuesException.class, ()->map.addValueT(5, pet1));
        assertThrows(RepeatedValuesException.class, ()->map.addValues(6, user1, pet1));
    }


    //REMOVER UN ITEM CON UNA KEY INEXISTENTE
    @Test
    void deleteItem() throws ExistingKeyException, RepeatedValuesException, NotExistingKeyExeption {
        //given:
        Person user1 = new Person("Agus");
        Pet pet1 = new Pet("Capri");
        EpicDoubleHashMap<Integer,Person,Pet> map = new EpicDoubleHashMap<>();
        map.addValues(2, user1, pet1);
        //then:
        assertThrows(NotExistingKeyExeption.class, ()->map.deleteItem(15));
    }
}