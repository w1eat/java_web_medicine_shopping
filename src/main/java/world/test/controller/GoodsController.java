package world.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.test.entity.Goods;


/**
 * 药品控制器
 */
@RestController
@RequestMapping("cart")
public class GoodsController extends BaseController<Goods> {

}
