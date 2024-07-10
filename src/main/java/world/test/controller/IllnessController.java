package world.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.test.entity.Illness;


/**
 * 疾病控制器
 */
@RestController
@RequestMapping("illness")
public class IllnessController extends BaseController<Illness> {

}
