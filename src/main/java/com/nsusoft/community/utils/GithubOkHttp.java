package com.nsusoft.community.utils;

import com.alibaba.fastjson.JSON;
import com.nsusoft.community.entity.AccessToken;
import com.nsusoft.community.entity.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubOkHttp {
    //返回类是的 access_token=e72e16c7e42f292c6912e7710c838347ae178b4a&token_type=bearer
    public String getAccessToken(AccessToken accessToken) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessToken));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            //截取并返回 access_token=e72e16c7e42f292c6912e7710c838347ae178b4a&token_type=bearer中的e72e16c7e42f292c6912e7710c838347ae178b4a
            String[] split = response.body().string().split("&");
            return split[0].split("=")[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
