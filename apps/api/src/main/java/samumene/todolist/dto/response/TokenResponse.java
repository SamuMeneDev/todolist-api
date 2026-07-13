package samumene.todolist.dto.response;

import java.time.LocalDateTime;

public record TokenResponse(
        String token,
        String email,
        LocalDateTime timestamp
) {}
