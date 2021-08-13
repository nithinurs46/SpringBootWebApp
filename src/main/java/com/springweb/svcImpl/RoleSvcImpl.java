package com.springweb.svcImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springweb.daoImpl.RoleRepository;
import com.springweb.model.Role;
import com.springweb.model.User;
import com.springweb.svc.RoleSvc;

@Service
public class RoleSvcImpl implements RoleSvc {

	@Autowired
	RoleRepository roleRepo;

	@Override
	public void addRole(Role form) {
		roleRepo.save(form);

	}

	@Override
	public void updateRole(Role form) {
		roleRepo.save(form);

	}

	@Override
	public void deleteRole(String roleName) {
		roleRepo.deleteById(roleName);

	}

	@Override
	public Role getRole(String roleName) {
		return roleRepo.findByRoleName(roleName);
	}

	@Override
	public List<Role> getAllRoles() {
		return roleRepo.findAll();
	}

	@Override
	public Map<String, String> getRoleNames() {
		List<Role> roles = roleRepo.findAll();
		Map<String, String> rolesMap = roles.stream().collect(Collectors.toMap(Role::getRoleName, Role::getRoleName));
		return rolesMap;
	}

	@Override
	public User getUserRoleList(User user) {
		String[] roles = user.getSelectedRoles();
		if (roles == null || roles.length <= 0) {
			return user;
		}
		for (int i = 0; i < roles.length; i++) {
			Role userRole = new Role();
			userRole.setUser(user);
			userRole.setRoleName(roles[i]);
			user.getRoles().add(userRole);
		}
		return user;

	}

	public String[] getSelectedRolesForUser(User user) {
		List<String> selectedRoles = new ArrayList<String>();
		Set<Role> userRole = user.getRoles();
		userRole.forEach((role) -> {
			selectedRoles.add((String) role.getRoleName());
		});
		return selectedRoles.toArray(new String[selectedRoles.size()]);
	}
}
