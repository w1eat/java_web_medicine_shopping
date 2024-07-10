package world.test.controller;

import cn.hutool.core.util.StrUtil;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.security.provider.MD5;
import world.test.dto.RespResult;
import world.test.entity.User;
import world.test.utils.Assert;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 登录控制器
 */
@RestController
@RequestMapping(value = "login")
public class LoginController extends BaseController<User> {

    /**
     * 注册
     */
    @PostMapping("/register")
    public RespResult register(User user, String code) {
        String email = user.getUserEmail();
        String pwd = user.getUserPwd();

        // 使用MD5对密码进行加密
        String encryptedPwd = DigestUtils.md5Hex(pwd);
        user.setUserPwd(encryptedPwd);

        if (Assert.isEmpty(email)) {
            return RespResult.fail("邮箱不能为空");
        }
        Map<String, Object> codeData = (Map<String, Object>) session.getAttribute("EMAIL_CODE" + email);
        if (codeData == null) {
            return RespResult.fail("尚未发送验证码");
        }
        String sentCode = (String) codeData.get("code");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime((Date) codeData.get("time"));
        calendar.add(Calendar.MINUTE, 5);
        if (System.currentTimeMillis() > calendar.getTime().getTime()) {
            session.removeAttribute("EMAIL_CODE" + email);
            return RespResult.fail("验证码已经超时");
        }
        if (!sentCode.equals(code)) {
            return RespResult.fail("验证码错误");
        }
        List<User> query = userService.query(User.builder().userAccount(user.getUserAccount()).build());
        if (Assert.notEmpty(query)) {
            return RespResult.fail("账户已被注册");
        }
        user.setRoleStatus(0);
        user.setImgPath("uploads/1.jpg");
        user = userService.save(user);
        //session.setAttribute("userName", user.getUserName());
        //session.setAttribute("loginUser", user);
        return RespResult.success("注册成功", user);
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public RespResult login(User user) {
        String pwd = user.getUserPwd();
        // 使用MD5对密码进行加密
        String encryptedPwd = DigestUtils.md5Hex(pwd);
        user.setUserPwd(encryptedPwd);

        List<User> users = userService.query(user);
        if (Assert.notEmpty(users)) {
            session.setAttribute("loginUser", users.get(0));
            return RespResult.success("登录成功");
        }
        if (Assert.isEmpty(userService.query(User.builder().userAccount(user.getUserAccount()).build()))) {
            return RespResult.fail("账户尚未注册");
        }
        return RespResult.fail("密码错误");
    }

    /**
     * 发送邮箱验证码
     */
    @PostMapping("/sendEmailCode")
    public RespResult sendEmailCode(String email, Map<String, Object> map) {
        if (StrUtil.isEmpty(email)) {
            return RespResult.fail("邮箱不可为空");
        }
        // 发送验证码
        String verifyCode = emailClient.sendEmailCode(email);
        map.put("email", email);
        map.put("code", verifyCode);
        map.put("time", new Date());
        session.setAttribute("EMAIL_CODE" + email, map);

        return RespResult.success("发送成功");
    }

    /**
     * 忘记密码
     */
    @PostMapping("/forgot")
    public RespResult forgot(User user, String code) {
        List<User> users = userService.query(user);
        String email = user.getUserEmail();
        if (Assert.isEmpty(email)) {
            return RespResult.fail("邮箱不能为空");
        }
        Map<String, Object> codeData = (Map<String, Object>) session.getAttribute("EMAIL_CODE" + email);
        if (codeData == null) {
            return RespResult.fail("尚未发送验证码");
        }
        String sentCode = (String) codeData.get("code");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime((Date) codeData.get("time"));
        calendar.add(Calendar.MINUTE, 5);
        if (System.currentTimeMillis() > calendar.getTime().getTime()) {
            session.removeAttribute("EMAIL_CODE" + email);
            return RespResult.fail("验证码已经超时");
        }
        if (!sentCode.equals(code)) {
            return RespResult.fail("验证码错误");
        }
        if (Assert.notEmpty(users)) {
            session.setAttribute("loginUser", users.get(0));
            return RespResult.success("验证成功已经成功登录");
        }

        return RespResult.fail("账户尚未注册");
    }


}
