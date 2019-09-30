package com.eastValley.control.spittr;

import com.eastValley.data.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author java_shj
 * @desc    传递模型数据到视图中
 * @createTime 2019/9/30 16:00
 **/
@Controller
@RequestMapping("/spittles")
public class SpittleController {
    private SpittleRepository  spittleRepository;
    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }
    @RequestMapping(method = RequestMethod.GET)
    public String spittles(Model model) {
        //model实际是一个map，它会传递给视图，这样数据就渲染到客户端了
        model.addAttribute("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));
        return "spittles";
    }

}
