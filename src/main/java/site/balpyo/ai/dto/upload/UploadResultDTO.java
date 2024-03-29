package site.balpyo.ai.dto.upload;

import lombok.*;

@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadResultDTO {
    private String profileUrl;

    private int playTime;
}