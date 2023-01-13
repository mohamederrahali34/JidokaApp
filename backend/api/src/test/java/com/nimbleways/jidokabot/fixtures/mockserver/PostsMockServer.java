package com.nimbleways.jidokabot.fixtures.mockserver;


import org.mockserver.client.MockServerClient;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import static com.nimbleways.jidokabot.utils.TestUtils.readJsonFile;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.JsonBody.json;

public class PostsMockServer {

    protected final MockServerClient mockServer;
    protected HttpRequest requestDefinition;
    protected HttpResponse httpResponse;

    protected PostsMockServer(final MockServerClient mockServer) {
        this.mockServer = mockServer;
    }

    public static PostsMockServer from(final MockServerClient mockServer) {
        return new PostsMockServer(mockServer);
    }

    public void mock() {
        mockServer.when(requestDefinition).respond(httpResponse);
    }

    protected HttpResponse getOkWithResponseIn(final String fixtureFilePath) {
        return HttpResponse
                .response()
                .withStatusCode(200)
                .withBody(json(readJsonFile(fixtureFilePath, PostsMockServer.class)));
    }

    public PostsMockServer whenGetPosts() {
        requestDefinition =
                request()
                        .withMethod("GET")
                        .withPath("/posts");

        return this;
    }

    public PostsMockServer willReturnPosts() {
        httpResponse = getOkWithResponseIn("posts/posts_list.json");

        return this;
    }
}
