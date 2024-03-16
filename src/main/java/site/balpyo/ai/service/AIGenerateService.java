package site.balpyo.ai.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import site.balpyo.ai.dto.AIGenerateRequest;
import site.balpyo.ai.entity.AIGenerateLogEntity;
import site.balpyo.ai.entity.GPTInfoEntity;
import site.balpyo.ai.repository.AIGenerateLogRepository;
import site.balpyo.ai.repository.GPTInfoRepository;
import site.balpyo.common.dto.CommonResponse;
import site.balpyo.common.dto.ErrorEnum;
import site.balpyo.common.util.CommonUtils;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AIGenerateService {


    private final AIGenerateUtils aiGenerateUtils;

    private final AIGenerateLogRepository aiGenerateLogRepository;


    @Value("${secrets.GPT_API_KEY}")
    public String GPT_API_KEY;
    public ResponseEntity<CommonResponse> generateScript(AIGenerateRequest request){

        // TODO :: TEST인 경우 TEST값 반환 <- 개발 완료 후 삭제 예정
        if(request.isTest()) return CommonResponse.success(new GPTTestObject().getGPTTestObject());

        //API KEY가 없는경우 에러 반환
        String CURRENT_GPT_API_KEY = GPT_API_KEY; if(CommonUtils.isAnyParameterNullOrBlank(CURRENT_GPT_API_KEY)) return CommonResponse.error(ErrorEnum.GPT_API_KEY_MISSING);

        //1. 주제, 소주제, 시간을 기반으로 프롬프트 생성
        String currentPromptString = aiGenerateUtils.createPromptString(request.getTopic(), request.getKeywords(), request.getSecTime());
        //2. 작성된 프롬프트를 기반으로 GPT에게 대본작성 요청
        ResponseEntity<Map> generatedScriptObject = aiGenerateUtils.requestGPTTextGeneration(currentPromptString, 0.5f, 4000, CURRENT_GPT_API_KEY);
        //3. GPT응답을 기반으로 대본 추출 + 대본이 없다면 대본 생성 실패 에러 반환
        Object resultScript = generatedScriptObject.getBody().get("choices"); if(CommonUtils.isAnyParameterNullOrBlank(resultScript)) return CommonResponse.error(ErrorEnum.GPT_GENERATION_ERROR);

        //4. GPT 응답에서 Body 추출
        Object resultBody = generatedScriptObject.getBody();
        //5. GPT 응답에서 GPTInfoEntity 추출 및 jpa로 저장할 수 있도록 GPTInfoEntity로변환
        GPTInfoEntity gptInfoData = new GPTInfoEntity().ResponseBodyToGPTInfoEntity(resultBody);
        //6. AI 사용기록에 gpt정보와 요청값들을 AIGenerateLogEntity형태로 변환
        AIGenerateLogEntity aiGenerateLog = new AIGenerateLogEntity().convertToEntity(request , gptInfoData);
        aiGenerateLogRepository.save(aiGenerateLog); //저장

        return CommonResponse.success(resultScript);
    }






}
