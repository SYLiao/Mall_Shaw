package me.shaw.mall.service.impl;

import me.shaw.mall.dao.AdminRoleRelationDAO;
import me.shaw.mall.model.Permission;
import me.shaw.mall.model.UserAdmin;
import me.shaw.mall.repository.UserAdminRepository;
import me.shaw.mall.service.UserAdminService;
import me.shaw.mall.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserAdminServiceImpl implements UserAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAdminServiceImpl.class);
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.tokenHead")
    private String tokenHead;

    @Autowired
    private AdminRoleRelationDAO adminRoleRelationDAO;

    @Autowired
    private UserAdminRepository userAdminRepository;

    @Override
    public UserAdmin getUserByName(String username) {
        List<UserAdmin> result = userAdminRepository.findByUsername(username);
        if(result != null && result.size() > 0){
            return result.get(0);
        }
        return null;
    }

    @Override
    public UserAdmin register(String username, String password) {
        UserAdmin userAdmin = new UserAdmin();
        userAdmin.setCreateTime(new Date());
        userAdmin.setStatus(1);
        List<UserAdmin> result = userAdminRepository.findByUsername(username);
        if(result != null && result.size() > 0){
            return null;
        }
        userAdmin.setPassword(password);
        userAdmin.setUsername(username);
        userAdminRepository.save(userAdmin);
        return userAdmin;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try{
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(!passwordEncoder.matches(password, userDetails.getPassword())){
                throw new BadCredentialsException("Incorrect password");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
        return token;
    }

    @Override
    public List<Permission> getPermissionList(Long adminId) {
        return adminRoleRelationDAO.getPermissionList(adminId);
    }
}
