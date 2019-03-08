package com.poison.utils;


import com.poison.domain.*;
import com.poison.mapper.ModuleMapper;
import com.poison.mapper.RoleMapper;
import com.poison.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 自定义realm
 * 
 *
 */
public class ShiRoRealm extends AuthorizingRealm {
	@Resource
	private UserMapper userMapper;
	@Resource
	private ModuleMapper moduleMapper;
    @Resource
	private RoleMapper roleMapper;
	/**
	 * 认证方法
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();// 从令牌中获得用户名

        UserExample userexam=new UserExample();
        userexam.createCriteria().andUserNameEqualTo(username);
        List<User> users = userMapper.selectByExample(userexam);
        if (users.size()==0) {
			// 用户名不存在
			return null;
		} else {
			// 用户名存在
            User user = users.get(0);
            String password = user.getPassword();// 获得数据库中存储的密码
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,
					password, this.getClass().getSimpleName());
			return info;//返回给安全管理器，由安全管理器负责比对数据库中查询出的密码和页面提交的密码
		}
	}

	/**
	 * 授权方法
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		User user= (User) principals.getPrimaryPrincipal();
        Set<Role> roles= roleMapper.findRoleByUser(user.getUserId());
		List<String> permissions=new ArrayList<String>();
        for(Role role :roles){
		 Set<Module> modules=   moduleMapper.findModuleByRole(role.getRoleId());
		 for(Module module:modules){
		     permissions.add(module.getCpermission());
         }

        }
		info.addStringPermissions(permissions);

        return info;
	}

}
