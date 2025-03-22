package com.zoltan.bloggingwebapi.payloads;

import java.time.LocalDateTime;

public record ErrorRespDTO(String message, LocalDateTime data) {
}
