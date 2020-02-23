package local.test.pms.controller;

import local.test.pms.entity.Authority;
import local.test.pms.entity.Role;
import local.test.pms.entity.User;
import local.test.pms.repository.AuthorityRepository;
import local.test.pms.repository.RoleRepository;
import local.test.pms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginHandler {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @PostMapping("/login")
    public ModelAndView lgoin(User user) {
        User item = userRepository.findByName(user.getName());
        List<String> authsId = new ArrayList<String>();
        if(item.getRoles()!=null) {
            Iterator<Role> roles = roleRepository.findAllById(item.getRoles()).iterator();
            while(roles.hasNext()) {
                Role role = roles.next();
                List<String> ids = role.getAuths();
                if(ids!=null) {
                    for(String id:ids) {
                        authsId.add(id);
                    }
                }
            }
        }
        Iterator<Authority> auths = authorityRepository.findAllById(authsId).iterator();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("auths", auths);
        return mav;
    }
}
