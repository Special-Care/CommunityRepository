package com.nsusoft.community.controller;

import com.nsusoft.community.entity.AccessToken;
import com.nsusoft.community.entity.GithubUser;
import com.nsusoft.community.entity.User;
import com.nsusoft.community.mapper.UserMapper;
import com.nsusoft.community.service.UserService;
import com.nsusoft.community.utils.GithubOkHttp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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

    @Autowired
    private UserService service;


    //前端登陆请求GET https://github.com/login/oauth/authorize?client_id=ad66024838c4b2109912&redirect_uri=http://localhost:8080/callback&scope=user&state=1
    @RequestMapping("/callback")
    public String callback(@RequestParam(value = "code")String code,
                           @RequestParam(value = "state") String state,
                           HttpServletResponse response) {
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
        GithubUser githubUser = githubOkHttp.getUser(token);
        if (githubUser != null && githubUser.getId() != null){
            String tokens = UUID.randomUUID().toString();
            User user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setToken(tokens);
            user.setPhotoUrl(githubUser.getAvatar_url());
            service.createOrUpdate(user);
            //mapper.insert(user);
            response.addCookie(new Cookie("token", tokens));
        }
        return "redirect:/";
    }

    //退出登录
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        //移除session中的User
        request.getSession().removeAttribute("user");
        //同时删除cookie中的token
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
