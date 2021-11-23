package kitchenpos.testtool.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.ResultActions;

public class MockMvcResult implements HttpResponse {

    private final ResultActions resultActions;
    private final ObjectMapper objectMapper;

    public MockMvcResult(ResultActions resultActions, ObjectMapper objectMapper) {
        this.resultActions = resultActions;
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> T convertBody(Class<T> tClass) {
        try {
            String json = resultActions.andReturn()
                    .getResponse()
                    .getContentAsString();
            return objectMapper.readValue(json, tClass);
        } catch (UnsupportedEncodingException | JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> List<T> convertBodyToList(Class<T> tClass) {
        try {
            final String json = resultActions.andReturn().getResponse().getContentAsString();
            return objectMapper.readValue(json,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, tClass));
        } catch (UnsupportedEncodingException | JsonProcessingException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public HttpStatus statusCode() {
        return HttpStatus.valueOf(resultActions.andReturn().getResponse().getStatus());
    }
}
