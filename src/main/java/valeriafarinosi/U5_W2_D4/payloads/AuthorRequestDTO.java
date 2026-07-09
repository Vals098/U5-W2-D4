package valeriafarinosi.U5_W2_D4.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AuthorRequestDTO(
        @NotBlank(message = "You must insert a name, no blank space.")
        @Size(min = 2, max = 40, message = "The name must contain between 2 and 40 letters.")
        String name,
        @NotBlank(message = "You must insert a surname, no blank space.")
        @Size(min = 2, max = 40, message = "The surname must contain between 2 and 40 letters.")
        String surname,
        @NotBlank(message = "You must insert an email address, no blank space.")
        @Email(message = "Email appropriate format required.")
        String email,
        @NotBlank(message = "You must insert a date of birth, no blank space.")
        @Past(message = "The date of birth must be in the past.")
        LocalDate birthDate) {
}
