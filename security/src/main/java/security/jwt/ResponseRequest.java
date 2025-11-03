package security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ResponseRequest {
    private String jwtToken;
    private String username;
    List<String> roles;
}
