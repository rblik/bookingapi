package isr.ek0.bookingapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Meal implements Serializable{
    @NotEmpty
    private String description;
    private Integer preparingTime;
    private Double price;
}
