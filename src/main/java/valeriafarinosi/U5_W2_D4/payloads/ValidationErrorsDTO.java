package valeriafarinosi.U5_W2_D4.payloads;

import java.time.LocalDateTime;
import java.util.List;

public record ValidationErrorsDTO(String message, LocalDateTime timestamp, List<String> errors) {
}
