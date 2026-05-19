package br.com.fiap.clyvovet.dto;

import br.com.fiap.clyvovet.enums.StatusLembrete;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LembreteResponseDTO {
    private Long id;
    private LocalDateTime dataHoraAlerta;
    private String mensagemPersonalizada;
    private StatusLembrete status;
}