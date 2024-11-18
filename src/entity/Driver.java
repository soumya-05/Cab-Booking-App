package entity;

import enums.STATUS;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver{
    int id;
    String name;
    int age;
    String gender;
    Location location;
    List<Ride> rides;
    Vehicle vehicle;
    STATUS status;
}
