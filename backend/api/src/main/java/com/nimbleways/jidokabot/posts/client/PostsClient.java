package com.nimbleways.jidokabot.posts.client;

import com.nimbleways.jidokabot.posts.client.model.PostDTO;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface PostsClient {
    @GET("/posts")
    Call<List<PostDTO>> getPosts();
}
