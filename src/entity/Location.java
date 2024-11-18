package entity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location {
    int id;
    int longitude;
    int latitude;
}
