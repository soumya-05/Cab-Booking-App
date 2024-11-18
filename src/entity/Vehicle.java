package entity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehicle {
    int id;
    String name;
    String number;
}
