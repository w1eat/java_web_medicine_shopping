package world.test.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.test.dto.RespResult;
import world.test.entity.User;
import world.test.utils.Assert;

/**
 * 用户控制器
 */
@RestController
@RequestMapping(value = "user")
public class UserController extends BaseController<User> {

    /**
     * 修改资料
     */
    @PostMapping("/saveProfile")
    public RespResult saveProfile(User user) {

        if (Assert.isEmpty(user)) {
            return RespResult.fail("保存对象不能为空");
        }
        user = userService.save(user);
        session.setAttribute("loginUser", user);
        return RespResult.success("保存成功");
    }

    /**
     * 修改密码
     */
    @PostMapping("/savePassword")
    public RespResult savePassword(String oldPass, String newPass) {
        // 使用MD5对密码进行加密
        String Pwd = DigestUtils.md5Hex(oldPass);
        if (!loginUser.getUserPwd().equals(Pwd)) {
            return RespResult.fail("旧密码错误");
        }
        String encryptedPwd = DigestUtils.md5Hex(newPass);
        loginUser.setUserPwd(encryptedPwd);
        loginUser = userService.save(loginUser);
        session.setAttribute("loginUser", loginUser);
        return RespResult.success("保存成功");
    }
}
