package entity;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ride {
    String id;
    Customer customer;
    Driver driver;
    Location start;
    Location end;
}
