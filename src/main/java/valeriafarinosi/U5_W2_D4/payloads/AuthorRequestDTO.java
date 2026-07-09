package valeriafarinosi.U5_W2_D4.payloads;

import java.time.LocalDate;

public record AuthorRequestDTO(
        String name,
        String surname,
        String email,
        LocalDate birthDate) {
}
