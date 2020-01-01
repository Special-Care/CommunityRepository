package com.nsusoft.community.controller;

import com.nsusoft.community.entity.AccessToken;
import com.nsusoft.community.entity.GithubUser;
import com.nsusoft.community.utils.GithubOkHttp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubOkHttp githubOkHttp;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.client.utl}")
    private String redirectUrl;


    //前端登陆请求GET https://github.com/login/oauth/authorize?client_id=ad66024838c4b2109912&redirect_uri=http://localhost:8080/callback&scope=user&state=1
    @RequestMapping("/callback")
    public String callback(@RequestParam(value = "code")String code,
                           @RequestParam(value = "state") String state) {
        //将拿到的数据和Github申请的OAuth Apps的数据封装成对象
        AccessToken accessToken = new AccessToken();
        accessToken.setClient_id(clientId);
        accessToken.setClient_secret(clientSecret);
        accessToken.setCode(code);
        accessToken.setRedirect_uri(redirectUrl);
        accessToken.setState(state);
        /**
         * 再次通过OkHttp的POST https://github.com/login/oauth/access_token
         * Response access_token=e72e16c7e42f292c6912e7710c838347ae178b4a&token_type=bearer
         * 返回 token的字符串
         */
        String token = githubOkHttp.getAccessToken(accessToken);
        /**
         * 再次通过OkHttp的GET https://api.github.com/user?access_token
         * 返回 user信息封装成GithubUser
         */
        GithubUser user = githubOkHttp.getUser(token);
        return "index";
    }
}
