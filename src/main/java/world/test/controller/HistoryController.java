package world.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.test.entity.History;


/**
 * 历史控制器
 */
@RestController
@RequestMapping("history")
public class HistoryController extends BaseController<History> {

}
