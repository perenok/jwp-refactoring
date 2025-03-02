package kitchenpos.testtool.request;


import kitchenpos.testtool.option.RequestOption;
import kitchenpos.testtool.util.TestTool;
import org.springframework.http.HttpMethod;

public class RequestApi {

    private final TestTool testTool;
    private final TestAdapterContainer testAdapterContainer;

    public RequestApi(TestTool testTool, TestAdapterContainer testAdapterContainer) {
        this.testTool = testTool;
        this.testAdapterContainer = testAdapterContainer;
    }

    public RequestOption get(String url, Object... pathVariables) {
        return getRequestOption(HttpMethod.GET, url, null, pathVariables);
    }

    public RequestOption post(String url, Object data, Object... pathVariables) {
        return getRequestOption(HttpMethod.POST, url, data, pathVariables);
    }

    public RequestOption put(String url, Object data, Object... pathVariables) {
        return getRequestOption(HttpMethod.PUT, url, data, pathVariables);
    }

    public RequestOption delete(String url, Object... pathVariables) {
        return getRequestOption(HttpMethod.DELETE, url, null, pathVariables);
    }

    private RequestOption getRequestOption(
            HttpMethod httpMethod,
            String url,
            Object data,
            Object... pathVariables
    ) {
        return new RequestOption(httpMethod, url, testTool, testAdapterContainer, data,
                pathVariables);
    }
}
