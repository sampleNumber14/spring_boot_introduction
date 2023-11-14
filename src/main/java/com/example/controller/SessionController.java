package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/session")
public class SessionController {

    // HttpSession型のフィールドを定義する
    private HttpSession session;

    // コンストラクタを作成し、@Autowiredアノテーションを付与する
    @Autowired
    public SessionController(HttpSession session) {
        // フィールドに代入する
        this.session = session;
    }
    
    // 保存処理
    // http://localhost:8080/session/set?name=太郎&bloodType=AB
    // sessionの保存はよくPOSTメソッドにより行いますが、解説のためGETメソッドを採用しています
    @GetMapping("/set")
    @ResponseBody
    public String set(@RequestParam("name") String name, @RequestParam("bloodType") String bloodType) {
        // Sessionへの保存
        this.session.setAttribute("name", name);
        this.session.setAttribute("bloodType", bloodType);
        return "保存しました";
    }
    
    // 取得
    
    // http://localhost:8080/session/get
    @GetMapping("/get")
    @ResponseBody
    public String get() {
        String name = (String) this.session.getAttribute("name");
        String bloodType = (String) this.session.getAttribute("bloodType");
        if (name == null) {
            name = "名無し";
        }
        if (bloodType == null) {
            bloodType = "不明";
        }
        return "名前: " + name + "<br>血液型: " + bloodType;
    }
    
    // 削除
    // http://localhost:8080/session/delete
    
    // sessionの削除はよくPOSTメソッドにより行いますが、解説のためGETメソッドを採用しています
    @GetMapping("/delete")
    @ResponseBody
    public String delete() {
        // Session名を指定して削除
        session.removeAttribute("name");
        session.removeAttribute("bloodType");
        return "削除しました";
    }
    
    // http://localhost:8080/session/get
}