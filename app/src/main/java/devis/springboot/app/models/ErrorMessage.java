package devis.springboot.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.net.http.HttpRequest;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private String messagge;
    private HttpStatus status;
    private String url;
}
