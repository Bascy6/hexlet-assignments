package exercise.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

// BEGIN
@Getter
@Setter
public class GuestCreateDTO {
    @NotBlank
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "^\\+[0-9]{11,13}$", message = "Prone number is incorrect")
    private String phoneNumber;

    @Pattern(regexp = "^[0-9]{4}", message = "Card number is incorrect")
    private String clubCard;

    @Future
    private LocalDate cardValidUntil;
}
// END
