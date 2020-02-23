package local.test.pms.controller;

import local.test.pms.entity.Role;
import local.test.pms.entity.User;
import local.test.pms.entity.UserVO;
import local.test.pms.repository.RoleRepository;
import local.test.pms.repository.UserRepository;
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
@RequestMapping("/user")
public class UserHandler {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getRoles")
    public ModelAndView getRoles() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user_add");
        mav.addObject("list",roleRepository.findAll().iterator());
        return mav;
    }

    @PostMapping("/add")
    public String add(User user) {
        userRepository.save(user);
        return "redirect:/user_index.jsp";
    }

    @GetMapping(value="/getAll")
    @ResponseBody
    public UserVO getAll(int page,int limit) {
        //分页处理
        Pageable pageable = PageRequest.of(page-1, limit);
        PageImpl pageImpl = userRepository.findAll(pageable);
        List<User> list =  pageImpl.getContent();
        UserVO userVO = new UserVO();
        userVO.setData(list);
        userVO.setCount(userRepository.count());
        return userVO;
    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable(value ="id")String id) {
        userRepository.deleteById(id);
        return "redirect:/user_index.jsp";
    }

    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable(value="id")String id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();
        Iterator<Role> allRoles = roleRepository.findAll().iterator();
        List<Role> list = new ArrayList<Role>();
        if(user.getRoles() == null) {
            while(allRoles.hasNext()) {
                Role item = allRoles.next();
                list.add(item);
            }
        }else {
            Iterator<Role> myRoles = roleRepository.findAllById(user.getRoles()).iterator();
            while(allRoles.hasNext()) {
                Role item = allRoles.next();
                myRoles = roleRepository.findAllById(user.getRoles()).iterator();
                while(myRoles.hasNext()) {
                    Role item2 = myRoles.next();
                    if(item2.getId().equals(item.getId())) {
                        item.setHas(true);
                    }
                }
                list.add(item);
            }
        }
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user_update");
        mav.addObject("user", user);
        mav.addObject("roles", list);
        return mav;
    }

    @PostMapping("/update")
    public String update(User user) {
        userRepository.save(user);
        return "redirect:/user_index.jsp";
    }
}
