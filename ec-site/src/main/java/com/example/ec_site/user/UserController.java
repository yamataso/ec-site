package com.example.ec_site.user;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // 追加
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // 追加：パスワードハッシュ化用

    // ログイン画面の表示
    @GetMapping("/login")
    public String login() {
        return "login"; // templates/login.html を表示する
    }

    /*
     * Spring Securityの formLogin (.loginProcessingUrl("/login")) が認証処理を自動で行うため、
     * 独自の @PostMapping("/login") は競合を避けるためにコメントアウトします。
     */
    /*
    @PostMapping("/login")
    public String loginProcess() {
        // Spring Securityが裏側で処理するため不要になります
        return null;
    }
    */

    // ユーザー新規登録画面の表示
    @GetMapping("/user_register")
    public String createInput(Model model) {
        model.addAttribute("userRegisterForm", new UserRegisterForm());
        return "user_register"; // templates/user_register.html を表示する
    }

    // ユーザー新規登録の処理
    @PostMapping("/user_register")
    public String userRegisterComplete(@ModelAttribute("userRegisterForm") UserRegisterForm form, Model model) {
        // パスワードと確認用パスワードの一致チェック
        if (form.getPassword() == null || !form.getPassword().equals(form.getConfirmPassword())) {
            model.addAttribute("error", "パスワードが一致しません。");
            return "user_register";
        }

        User user = new User();
        user.setUserName(form.getUserName());
        user.setUserNameKana(""); 
        user.setPhone("");       
        user.setEmail(form.getEmail());
        
        // ★重要：Spring Securityで認証できるように、パスワードをBCryptでハッシュ化して保存
        user.setPasswords(passwordEncoder.encode(form.getPassword()));
        
        user.setUserAddress("未登録"); 
        
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        
        userRepository.save(user);
        
        return "redirect:/login";
    }
}