package br.com.feedbacks.functions;

import br.com.feedbacks.dtos.AvaliacaoRequestDTO;
import br.com.feedbacks.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@AllArgsConstructor
public class AvaliacaoAnaliseFunction {

    private final EmailService emailService;

    private static final String SUBJECT = "Avaliação precisa de atenção!";

    private static final String BODY =
            "Uma nova avaliação foi recebida:\n\nDescrição: %s\nNota: %d\n\nPor favor, verifique os detalhes.";

    @Bean
    public Function<AvaliacaoRequestDTO, String> analisarAvaliacao() {
        return payload -> {

            if (payload.requestPayload().nota() <= 5) {
                String body = String.format(
                        BODY, payload.requestPayload().descricao(), payload.requestPayload().nota());

                emailService.sendSimpleEmail(SUBJECT, body);
            }

            return "Processado" ;
        };
    }
}
