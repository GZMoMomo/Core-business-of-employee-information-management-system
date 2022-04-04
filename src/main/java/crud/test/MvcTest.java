package crud.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;

import crud.bean.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/springMVC.xml"} )
public class MvcTest {
	//Springmvc��ioc
	@Autowired
  WebApplicationContext context	;
	
  MockMvc mockMvc;
  @Before
  public void initMockMvc() {
	 mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
  }
  @Test
  public void testPage() throws Exception {
	MvcResult result=  mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "5")).andReturn();
    
	MockHttpServletRequest request = result.getRequest();
   PageInfo attribute = (PageInfo) request.getAttribute("pageInfo");
   System.out.println("��ǰҳ��"+attribute.getPageNum());
   System.out.println("��ҳ��"+attribute.getPages());
   System.out.println("�ܼ�¼��"+attribute.getTotal());
   System.out.println("��ҳ����Ҫ������ʾ��ҳ��");
   int[] nums=attribute.getNavigatepageNums();
   for(int i:nums) {
	  System.out.print(" "+i);
   }
   
  List<Employee> list= attribute.getList();
  for(Employee employee:list) {
	  System.out.println("id:"+employee.getEmpId()+"  Name:"+employee.getEmpName());
  }
  
  
  }
}
