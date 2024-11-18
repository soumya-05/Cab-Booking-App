package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer{
    int id;
    String name;
    int age;
    String gender;
    Location currLocation;
    Location searchLocation;
    Ride currentRide;
}
