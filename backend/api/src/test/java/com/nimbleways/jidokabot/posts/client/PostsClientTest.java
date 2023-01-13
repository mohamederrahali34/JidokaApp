package com.nimbleways.jidokabot.posts.client;

import com.nimbleways.jidokabot.configuration.RetrofitConfiguration;
import com.nimbleways.jidokabot.fixtures.mockserver.PostsMockServer;
import com.nimbleways.jidokabot.posts.client.model.PostDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({MockServerExtension.class})
class PostsClientTest {
    private final MockServerClient mockServer;

    private final PostsClient postClient;

    PostsClientTest(MockServerClient client) {
        mockServer = client;
        postClient = getPostsClient(client);
    }

    @AfterEach
    public void teardown() {
        mockServer.reset();
    }

    @Test
    void should_map_response_from_posts_api() throws IOException {
        // GIVEN
        PostsMockServer.from(mockServer).whenGetPosts().willReturnPosts().mock();


        // WHEN
        List<PostDTO> posts = postClient.getPosts().execute().body();


        // THEN
        assertThat(posts).isNotNull();
        assertThat(posts.size()).isEqualTo(3);
    }

    private static PostsClient getPostsClient(MockServerClient client) {
        final String baseUrl = String.format("http://127.0.0.1:%s", client.getPort());
        RetrofitConfiguration configuration = new RetrofitConfiguration();
        return configuration.postsClient(configuration.okHttpClient(), baseUrl);
    }
}