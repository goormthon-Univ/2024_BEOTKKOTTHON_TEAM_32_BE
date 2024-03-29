package site.balpyo.ai.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;


public class GPTTestObject {
    String jsonString = "[{\"text\": \"\\n\\n안녕하세요, 여러분. 오늘은 스크럼을 효율적으로 하는 방법에 대해 알아보겠습니다. 스크럼은 소프트웨어 개발 방법론 중 하나로, 팀 기반의 애자일 개발 프로세스입니다. 이를 통해 팀은 작은 주기로 나누어진 작업을 완료하고, 지속적으로 개선하며 제품을 출시할 수 있습니다.\\n\\n효율적인 스크럼을 위해서는 몇 가지 중요한 요소가 있습니다. 첫 번째로는 팀의 역할과 책임을 명확히 하는 것입니다. 스크럼에서는 제품 책임자, 스크럼 마스터, 개발 팀으로 구성되며, 각자의 역할과 책임을 잘 이해하고 역할을 수행해야 합니다.\\n\\n두 번째로는 스크럼 이벤트를 잘 계획하고 진행하는 것이 중요합니다. 스크럼에서는 일일 스크럼, 스프린트 계획 회의, 스프린트 검토 회의, 스프린트 회고 등의 이벤트가 있습니다. 이를 효율적으로 진행하고, 각 이벤트에서 나오는 결과물을 잘 활용하는 것이 스크럼의 핵심입니다.\\n\\n세 번째로는 팀의 커뮤니케이션과 협업을 잘 조율하는 것이 중요합니다. 스크럼에서는 팀원들 간의 의사소통을 매우 중요하게 여기며, 서로의 업무를 이해하고 지원하는 것이 필수적입니다. 또한 스크럼에서는 개발 팀과 제품 책임자, 스크럼 마스터 간의 원활한 협업이 필요합니다.\\n\\n마지막으로는 지속적인 개선과 학습을 추구하는 것이 스크럼의 핵심 가치 중 하나입니다. 스크럼에서는 스프린트 회고를 통해 지속적으로 개선할 점을 찾고, 이를 반영하여 더 나은 제품을 만들어가는 것이 중요합니다.\\n\\n이렇게 몇 가지 핵심 요소를 잘 준수하면, 효율적인 스크럼을 구축할 수 있습니다. 이를 통해 팀은 더 빠르고 효율적으로 제품을 출시할 수 있으며, 지속적인 개선과 협업을 통해 더 나은 결과물을 만들어낼 수 있습니다.\\n\\n이상으로 스크럼을 효율적으로 하는 방법에 대해 알아보았습니다. 감사합니다.\", \"index\": 0, \"logprobs\": null, \"finish_reason\": \"stop\"}]";
    public Object getGPTTestObject(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Object testObjects = objectMapper.readValue(jsonString, Object.class);
            return testObjects;
        } catch (Exception e) {
            return "";
        }
    }

}