package com.util.jackson;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.util.List;

public class PolymorphicDeserialization {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = "[" +
                "{\"name\":\"Ferrari\",\"vehicleType\":\"CAR\",\"sunRoof\":false}," +
                "{\"name\":\"Fake Ferrari\",\"vehicleType\":\"CAR2\",\"sunRoof\":false}," +
                "{\"name\":\"Boeing 750\",\"vehicleType\":\"PLANE\",\"wingspan\":19.25}," +
                "{\"name\":\"Tata\",\"vehicleType\":\"TRUCK\", \"numOfWheels\":8 }" +
                "]";

        List<Vehicle> vehicles = mapper.readValue(json, new TypeReference<List<Vehicle>>(){});
        System.out.println("\n\n-----------List of Vehicles objects after Deserialization-----------");
        System.out.println(vehicles);

        System.out.println("\n\n-----------Vehicles json after serialization-----------");
        final String jsonOfVehicles = mapper.writeValueAsString(vehicles);

        System.out.println(jsonOfVehicles);

    }
}


enum VehicleType {
    CAR,
    CAR2,
    PLANE,
    TRUCK
}


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "vehicleType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Car.class, name = "CAR"),
        @JsonSubTypes.Type(value = Car.class, name = "CAR2"),
        @JsonSubTypes.Type(value = Plane.class, name = "PLANE"),
        @JsonSubTypes.Type(value = Truck.class, name = "TRUCK")
})
@Getter
@Setter
abstract class Vehicle {
    private VehicleType vehicleType;

    abstract String getName();
}



@NoArgsConstructor
@Getter
@Setter
class Car extends Vehicle {
    private boolean sunRoof;
    private String name;

    @Override
    public String toString() {
        return "Car{" +
                "name=" + getName() +
                ", vehicleType=" + getVehicleType() +
                ", sunRoof=" + sunRoof +
                "} ";
    }


}

@NoArgsConstructor
@Getter
@Setter
class Plane extends Vehicle {
    private double wingspan;
    private String name;

    @Override
    public String toString() {
        return "Plane{" +
                "name=" + getName() +
                ", vehicleType=" + getVehicleType() +
                ", wingspan=" + wingspan +
                "} ";
    }
}


@NoArgsConstructor
@Getter
@Setter
class Truck extends Vehicle {
    private int numOfWheels;
    private String name;

    @Override
    public String toString() {
        return "Truck{" +
                "name=" + getName() +
                ", vehicleType=" + getVehicleType() +
                ", numOfWheels=" + numOfWheels +
                "} ";
    }
}


