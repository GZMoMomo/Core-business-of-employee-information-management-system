package crud.utils;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import crud.service.RoleService;
import crud.service.UserService;

public class DatabaseRealm extends AuthorizingRealm {
    @Autowired
	private UserService userService;
    @Autowired
    private RoleService roleService;
    
    
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		String userName=(String)principalCollection.getPrimaryPrincipal();
		Set<String>roles=roleService.listRoles(userName);
		SimpleAuthorizationInfo s=new SimpleAuthorizationInfo();
		s.addRoles(roles);
		return s;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken t=(UsernamePasswordToken)token;
		String userName=token.getPrincipal().toString();
		String password=new String(t.getPassword());
		String passwordInDB=userService.getPassword(userName);
		if(null==passwordInDB||!passwordInDB.equals(password)) {
			throw new AuthenticationException();
		}
		SimpleAuthenticationInfo a=new SimpleAuthenticationInfo(userName,password,getName());
		return a;
	}

}


















