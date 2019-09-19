package com.pojo.jackson;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.io.IOException;
import java.util.List;

public class PolymorphicDeserializationUsingClass {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String json = "[" +
                "{\"name\":\"Ferrari\",\"type\":\"CAR\",\"sunRoof\":false}," +
                "{\"name\":\"Volvo\",\"type\":\"BUS\"}," +
                "{\"name\":\"Tata\",\"type\":\"TRUCK\"}," +
                "{\"name\":\"Boeing 750\",\"type\":\"PLANE\",\"wingspan\":19.25}" +
                "]";

        List<Vehicle> vehicles = mapper.readValue(json, new TypeReference<List<Vehicle>>(){});

        System.out.println(vehicles);
    }


}


enum VehicleType {
    CAR,
    PLANE,
    BUS,
    TRUCK
}


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        visible = true,
        property = "type"
)
@JsonSubTypes( {
        @JsonSubTypes.Type(value = Car.class, name = "CAR"),
        @JsonSubTypes.Type(value = Plane.class, name = "PLANE"),
        @JsonSubTypes.Type(value = Vehicle.class, name = "BUS"),
        @JsonSubTypes.Type(value = Vehicle.class, name = "TRUCK")
})
@NoArgsConstructor
@AllArgsConstructor
@Data
class Vehicle {
    private String name;
    private VehicleType type;
}



@NoArgsConstructor
@Getter
@Setter
class Car extends Vehicle {
    private boolean sunRoof;

    public Car(VehicleType type, String name, boolean sunRoof) {
        super(name, type);
        this.sunRoof = sunRoof;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name=" + getName() +
                ", type=" + getType() +
                ", sunRoof=" + sunRoof +
                "} ";
    }
}




@NoArgsConstructor
@Getter
@Setter
class Plane extends Vehicle {
    private double wingspan;

    public Plane(VehicleType type, String name, double wingspan) {
        super(name, type);
        this.wingspan = wingspan;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "name=" + getName() +
                ", type=" + getType() +
                ", wingspan=" + wingspan +
                "} ";
    }
}


