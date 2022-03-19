package crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import crud.bean.Employee;
import crud.bean.Msg;
import crud.service.EmployeeService;
/**
 * 
 * ����Ա��CRUD����
 * @author Administrator
 *
 */
@Controller
public class EmployeeController {
	@Autowired
   EmployeeService employeeService;
	
	/**
	 * ����ɾ��/����ɾ��
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	public Msg deleteEmpById(@PathVariable("ids") String ids) {
		if(ids.contains("-")) {
			List<Integer> del_ids=new ArrayList<Integer>();
			String[] str_ids = ids.split("-");
            for(String string:str_ids) {
            	del_ids.add(Integer.parseInt(string));
            	
            }
            employeeService.deleteBath(del_ids);
		}else {
			Integer id = Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
		
		return Msg.success();
	}
	
	
	/**
	 * AJAX����put�����п��ܻ��ò�������
	 * Tomcatʶ��put�����װ�������е�����Ϊmap,ֻ��post�ŷ�װ������
	 * ����HttpPutFormContentFilter
	 * ���������е����ݽ�����װ��һ��map
	 * request�����°�װ��getparameter����д�����Լ���װ��mapȡ����
	 * Ա������
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	public Msg saveEmp(Employee employee) {
		employeeService.updateEmp(employee);
		return Msg.success();
		
	}
	
	/**
	 * ��ѯԱ����Ϣ
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	public Msg getEmp(@PathVariable("id")Integer id) {
		Employee employee = employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	
	
	/**
	 * ����û����Ƿ����
	 * @param empName
	 * @return
	 */
	@RequestMapping("/checkuser")
	@ResponseBody
	public Msg checkuser(@RequestParam("empName")String empName) {
		String regx="(^[a-zA-Z0-9_-]{3,16}$)|(^[\\u2E80-\\u9FFF])";
		if(!empName.matches(regx)) {
			return Msg.fail().add("va_msg", "�û������������λ��");
		}
		
		boolean b = employeeService.checkUser(empName);
		if(b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "�û����ظ�!");
		}
	}
	
	/**
	 * Ա������
	 * ֧��JSR303У��
	 * hibernate validator
	 * @return
	 */
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult result) {
		if(result.hasErrors()) {
			Map<String,Object> map=new HashMap<String, Object>();
			List<FieldError> errors=result.getFieldErrors();
			for(FieldError fieldError:errors) {
				System.out.println("�����ֶ���:"+fieldError.getField());
				System.out.println("������Ϣ:"+fieldError.getDefaultMessage());
			    map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else {
			employeeService.saveEmp(employee);
		return Msg.success();
		}
		
	}
	
	/**
	 * ����Jackson��
	 * @param pn
	 * @param model
	 * @return
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value="pn",defaultValue="1")Integer pn,org.springframework.ui.Model model) {
		PageHelper.startPage(pn, 5);
		List<Employee> emps= employeeService.getAll();
		//��װ��ѯ��Ľ��,������ʾ��ҳ
		PageInfo page=new PageInfo(emps,5);
		return Msg.success().add("pageInfo",page);
	}
	
	/**
	 * ��ѯԱ������ ��ҳ��ѯ
	 * @return
	 */
	//@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn",defaultValue="1")Integer pn,org.springframework.ui.Model model) {
		
		PageHelper.startPage(pn, 5);
		List<Employee> emps= employeeService.getAll();
		//��װ��ѯ��Ľ��,������ʾ��ҳ
		PageInfo page=new PageInfo(emps,5);
		model.addAttribute("pageInfo",page);
		
		return "list";
	}
}
