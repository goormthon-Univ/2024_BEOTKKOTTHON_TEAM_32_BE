package site.balpyo.guest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class VerifyResponse {
    boolean isVerified;
    String yourUID;
}
