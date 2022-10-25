package TP2;

import java.util.Objects;

public class Pet {
    private String name;

    public Pet(String name) {
        this.name = name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet pet)) return false;
        return Objects.equals(name, pet.name);
    }

    @Override
    public String toString() {
        return "Pet name:" + name ;
    }
}
