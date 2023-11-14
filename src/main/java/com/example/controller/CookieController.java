package com.example.controller;

// CookieクラスとHttpServletResponseクラスをインポートします
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("cookie")
public class CookieController {
	// http://localhost:8080/cookie/set?value=Cookie
    // cookieの保存はよくPOSTメソッドにより行いますが、解説のためGETメソッドを採用しています
    @GetMapping("/set")
    @ResponseBody
    public String setCookie(@RequestParam String value, HttpServletResponse res) {
        // cookie名をtest_cookieとして保存します
        res.addCookie(new Cookie("test_cookie", value));
        return "保存しました";
    }
    
    // http://localhost:8080/cookie/get
    @GetMapping("/get")
    @ResponseBody
    public String getCookie(@CookieValue("test_cookie") Cookie cookie) {
        // cookieの値を取得します
        return "cookieValue: " + cookie.getValue();
    }
    
    // http://localhost:8080/cookie/delete
    // cookieの削除はよくPOSTメソッドにより行いますが、解説のためGETメソッドを採用しています
    @GetMapping("/delete")
    @ResponseBody
    public String deleteCookie(@CookieValue("test_cookie") Cookie cookie, HttpServletResponse res) {
        cookie.setMaxAge(0);
        res.addCookie(cookie);
        return "削除しました";
    }
    
}