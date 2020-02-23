package local.test.pms.controller;

import local.test.pms.entity.Authority;
import local.test.pms.entity.Role;
import local.test.pms.entity.RoleVO;
import local.test.pms.repository.AuthorityRepository;
import local.test.pms.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/role")
public class RoleHandler {

    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/getAuths")
    public ModelAndView getAuths() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("role_add");
        mav.addObject("list",authorityRepository.findAll().iterator());
        return mav;
    }

    @PostMapping("/add")
    public String add(Role role) {
        roleRepository.save(role);
        return "redirect:/role_index.jsp";
    }

    @GetMapping(value="/getAll")
    @ResponseBody
    public RoleVO getAll(int page,int limit) {
        //分页处理
        Pageable pageable = PageRequest.of(page-1, limit);
        PageImpl pageImpl = roleRepository.findAll(pageable);
        List<Role> list =  pageImpl.getContent();
        RoleVO roleVO = new RoleVO();
        roleVO.setData(list);
        roleVO.setCount(roleRepository.count());
        return roleVO;

    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable(value="id")String id) {
        roleRepository.deleteById(id);
        return "redirect:/role_index.jsp";
    }

    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable(value="id")String id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        Role role = roleOptional.get();
        Iterator<Authority> allAuths = authorityRepository.findAll().iterator();
        List<Authority> list = new ArrayList<Authority>();
        if(role.getAuths() == null) {
            while(allAuths.hasNext()) {
                Authority item = allAuths.next();
                list.add(item);
            }
        }else {
            Iterator<Authority> myAuths = authorityRepository.findAllById(role.getAuths()).iterator();
            while(allAuths.hasNext()) {
                Authority item = allAuths.next();
                myAuths = authorityRepository.findAllById(role.getAuths()).iterator();
                while(myAuths.hasNext()) {
                    Authority item2 = myAuths.next();
                    if(item2.getId().equals(item.getId())) {
                        item.setHas(true);
                    }
                }
                list.add(item);
            }
        }
        ModelAndView mav = new ModelAndView();
        mav.setViewName("role_update");
        mav.addObject("role", role);
        mav.addObject("auths", list);
        return mav;
    }

    @PostMapping("/update")
    public String update(Role role) {
        roleRepository.save(role);
        return "redirect:/role_index.jsp";
    }
}
