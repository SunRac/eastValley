package cn.eastvalley.control;

import cn.eastvalley.data.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author java_shj
 * @desc    传递模型数据到视图中
 * 传入参数：请求参数@RequestParam、路径变量@PathVariable、表单参数
 * @createTime 2019/9/30 16:00
 **/
@Controller
@RequestMapping("/spittles")
public class SpittleController {
    private SpittleRepository spittleRepository;
    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }
    @RequestMapping(value="/{spittleId}", method = RequestMethod.GET)
    // 由于查询参数都是string类型的，因此defaultValue是字符串，但是绑定到方法中的max参数时会转换为long
    public String spittles(@RequestParam(value="max",defaultValue = "99999") long max,
                           @RequestParam(value = "count",defaultValue = "20") int count,
                           @PathVariable("spittleId") long spittleId,
                           Model model) {

        //model实际是一个map，它会传递给视图，这样数据(spittleRepository)就渲染到客户端了
        model.addAttribute("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));
        return "home";
    }

}
