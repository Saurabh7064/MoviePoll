package com.dragan1982.movies.serviceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.dragan1982.movies.dao.impl.RoleDaoImpl;
import com.dragan1982.movies.model.Role;
import com.dragan1982.movies.service.RoleService;
import com.dragan1982.movies.service.impl.RoleServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {
	
		@Mock
	    private RoleDaoImpl mockRoleDao;
		
		@InjectMocks
	    private RoleService roleService = new RoleServiceImpl();
	    
	    @Test
	    public void testSaveRole() {
	    	
	    	Role role = new Role();
	    	
	    	doAnswer(new Answer<Role>() {
                @Override
                public Role answer(InvocationOnMock invocation) throws Throwable {
                    Role role = (Role) invocation.getArguments()[0];
                    role.setId(1L);
                    return role;
                }
            }).when(mockRoleDao).saveRole(any(Role.class));
	    		    	
	    	assertEquals(null, role.getId());
	    	
	    	mockRoleDao.saveRole(role);
	    	
	    	assertNotNull(role.getId());
	        assertSame(1L, role.getId());
	    }
	    
	    @Test
	    public void testListRoles() {
	    	
	    	Role role1 = new Role();
	    	Role role2 = new Role();
	    	role1.setId(1L);
	    	role2.setId(2L);
	    	
	    	List<Role> roleList = Arrays.asList(role1, role2);
	    	
	    	assertTrue(mockRoleDao.listRoles().isEmpty()); 
	    	 
	    	when(mockRoleDao.listRoles()).thenReturn(roleList);
	    	
	    	assertFalse(mockRoleDao.listRoles().isEmpty());
	    	assertNotNull(mockRoleDao.listRoles().get(1)); 	
	    }
	    
	    @Test
	    public void testGetRole() {
	    	
	    	Role role = new Role();
	    	role.setId(1L);
	    	
	    	assertNull(mockRoleDao.getRole(role.getId()));
	    	
	    	when(mockRoleDao.getRole(anyLong())).thenReturn(role);
	    	
	    	assertNotNull(mockRoleDao.getRole(role.getId()));
	    	
	    }
	    
	    @Test
	    public void testDeleteRole() { 
	    	
	    	doNothing().when(mockRoleDao).deleteRole(anyLong());
	    		   	
	    	mockRoleDao.deleteRole(anyLong());
	    	
	    	verify(mockRoleDao, times(1)).deleteRole(anyLong());	    	
	 	}  	    
}	