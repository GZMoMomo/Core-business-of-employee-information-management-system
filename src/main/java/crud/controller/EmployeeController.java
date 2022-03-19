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
 * 处理员工CRUD请求
 * @author Administrator
 *
 */
@Controller
public class EmployeeController {
	@Autowired
   EmployeeService employeeService;
	
	/**
	 * 单个删除/批量删除
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
	 * AJAX发送put请求有可能会拿不到数据
	 * Tomcat识别到put不会封装请求体中的数据为map,只有post才封装请求体
	 * 配置HttpPutFormContentFilter
	 * 将请求体中的数据解析包装成一个map
	 * request被重新包装，getparameter被重写，从自己封装的map取数据
	 * 员工更新
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	public Msg saveEmp(Employee employee) {
		employeeService.updateEmp(employee);
		return Msg.success();
		
	}
	
	/**
	 * 查询员工信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	public Msg getEmp(@PathVariable("id")Integer id) {
		Employee employee = employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	
	
	/**
	 * 检查用户名是否可用
	 * @param empName
	 * @return
	 */
	@RequestMapping("/checkuser")
	@ResponseBody
	public Msg checkuser(@RequestParam("empName")String empName) {
		String regx="(^[a-zA-Z0-9_-]{3,16}$)|(^[\\u2E80-\\u9FFF])";
		if(!empName.matches(regx)) {
			return Msg.fail().add("va_msg", "用户名必须大于三位！");
		}
		
		boolean b = employeeService.checkUser(empName);
		if(b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "用户名重复!");
		}
	}
	
	/**
	 * 员工保存
	 * 支持JSR303校验
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
				System.out.println("错误字段名:"+fieldError.getField());
				System.out.println("错误信息:"+fieldError.getDefaultMessage());
			    map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else {
			employeeService.saveEmp(employee);
		return Msg.success();
		}
		
	}
	
	/**
	 * 导入Jackson包
	 * @param pn
	 * @param model
	 * @return
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value="pn",defaultValue="1")Integer pn,org.springframework.ui.Model model) {
		PageHelper.startPage(pn, 5);
		List<Employee> emps= employeeService.getAll();
		//包装查询后的结果,连续显示五页
		PageInfo page=new PageInfo(emps,5);
		return Msg.success().add("pageInfo",page);
	}
	
	/**
	 * 查询员工数据 分页查询
	 * @return
	 */
	//@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn",defaultValue="1")Integer pn,org.springframework.ui.Model model) {
		
		PageHelper.startPage(pn, 5);
		List<Employee> emps= employeeService.getAll();
		//包装查询后的结果,连续显示五页
		PageInfo page=new PageInfo(emps,5);
		model.addAttribute("pageInfo",page);
		
		return "list";
	}
}
