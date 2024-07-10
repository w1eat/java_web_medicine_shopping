package world.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import world.test.dto.RespResult;
import world.test.entity.User;

import java.io.File;
import java.io.IOException;

/**
 * 文件控制器
 */
@RestController
@RequestMapping("/file")
public class FileController extends BaseController<User> {


    /**
     * 上传文件
     */
    @PostMapping("/upload")
    public RespResult upload(@RequestParam("file") MultipartFile file) {
        try {
            // 获取源文件的文件名
            String originalFilename = file.getOriginalFilename();
            System.out.println(originalFilename);
            session.setAttribute("uploadsImgName","/uploads"+originalFilename);
            // 存储文件到磁盘上
            file.transferTo(new File("E:\\java项目\\smart-medicine-main2\\smart-medicine-main\\src\\main\\resources\\static\\uploads\\" + originalFilename));
            // 返回上传成功的结果，这里假设上传成功后，可以直接访问上传的文件，所以返回文件的 URL

            return RespResult.success("上传成功");
        } catch (IOException e) {
            // 处理文件上传异常，比如磁盘空间不足等
            e.printStackTrace();
            return RespResult.fail("上传失败", e.getMessage());
        }
    }
}
