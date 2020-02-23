package local.test.pms.controller;

import local.test.pms.entity.Authority;
import local.test.pms.entity.AuthorityVO;
import local.test.pms.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/authority")
public class AuthorityHandler {

    @Autowired
    private AuthorityRepository authorityRepository;


    @PostMapping("/add")
    public String add(Authority authority) {
        authorityRepository.save(authority);
        return "redirect:/authority_index.jsp";
    }

    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable(value = "id") String id){
        Optional<Authority> authorityOptional = authorityRepository.findById(id);
        Authority authority = authorityOptional.get();
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("authority_update");
        modelAndView.addObject("authority",authority);
        return modelAndView;
    }

    @PostMapping("/update")
    public String update(Authority authority){
        authorityRepository.save(authority);
        return "redirect:/authority_index.jsp";
    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") String id) {
        authorityRepository.deleteById(id);
        return "redirect:/authority_index.jsp";
    }

    @GetMapping("/getAll")
    @ResponseBody
    public AuthorityVO getAll(int page, int limit) {
        Pageable pageable = PageRequest.of(page -1, limit);
        PageImpl pageImpl = authorityRepository.findAll(pageable);
        List<Authority> list = pageImpl.getContent();
        AuthorityVO authorityVO =  new AuthorityVO();
        authorityVO.setData(list);
        authorityVO.setCount(authorityRepository.count());
        return authorityVO;
    }

}
