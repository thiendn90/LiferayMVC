package ex.employee.controller;

import ex.employee.dao.EmployeeDao;
import ex.employee.model.Employee;
import ex.employee.service.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Controller for manages employee
 *
 * @author toannh
 */
@RequestMapping("VIEW")
@Controller
public class EmployeeController {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    private Logger logger = Logger.getLogger(EmployeeController.class);
    // Constants
    private static final String EMPLOYEE_LIST_JSP = "employeeList";
    private static final String EMPLOYEE_UPDATE_JSP = "employeeUpdate";
    private static final String EMPLOYEE_VIEW_JSP = "employeeView";
    private static final String EMPLOYEE_ADD_JSP = "employeeAdd";

    private MessageSource messageSource;

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private EmployeeService employeeService;

    /**
     * Default render method when page is load
     *
     * @return
     */
    @RenderMapping
    public String defaultRender(PortletRequest request, Model model) {
        List<Employee> employees = employeeDao.findAll();
        model.addAttribute("employees", employees);
        return EMPLOYEE_LIST_JSP;
    }

    @ActionMapping(params = "action=doFormAction")
    public void doFormAction(@ModelAttribute("employees") Employee someObject, ActionRequest request) {
        String strname = request.getParameter("name");
        System.out.println("someObject : " + someObject.toString());
    }

    @RenderMapping(params = "render=employeeUpdate")
    public String employeeForm(@RequestParam(value = "employeeId", required = false) Integer employeeId,
                               Map<String, Object> map) {
        Employee employee = null;
        if (employeeId != null) {
            employee = employeeDao.findById(employeeId);
        }
        if (employee == null) {
            employee = new Employee();
        }
        map.put("employee", employee);
        return EMPLOYEE_UPDATE_JSP;
    }

    @RenderMapping(params = "render=employeeView")
    public String employeeView(@RequestParam(value = "employeeId", required = false) Integer employeeId,
                               Map<String, Object> map) {
        Employee employee = employeeDao.findById(employeeId);
        map.put("employee", employee);
        return EMPLOYEE_VIEW_JSP;
    }

    @RenderMapping(params = "render=employeeAdd")
    public String employeeAdd( Map<String, Object> map){
        map.put("employee", new Employee());
        return EMPLOYEE_ADD_JSP;
    }

    @ResourceMapping("processDelete")
    public String employeeDelete(@RequestParam("employeeId") Integer employeeId, Model model){
            if(!Objects.isNull(employeeId)){
                Employee employee = employeeDao.findById(employeeId);
                logger.debug("get employee /t"+employee);
                try{
                    employeeDao.delete(employee);
                    logger.debug("Process delete follow id "+employeeId);
                }catch (Exception ex){
                    model.addAttribute(ERROR, "Error");
                }
                model.addAttribute(SUCCESS,"Process success");
                return EMPLOYEE_LIST_JSP;
            }
        return "404";
    }

    @ActionMapping(params = "action=save")
    public void saveEmployee(ActionRequest actionRequest, ActionResponse actionResponse, Model model,
                             @ModelAttribute("employee") Employee employee, BindingResult result) throws IOException,
            PortletException {
        try {
            employeeService.saveOrUpdate(employee);
        } catch (Exception e) {
            logger.error("Error while saving employee", e);
        }

        if (employee != null) {
            actionRequest.setAttribute(SUCCESS,"Save thanh cong");
        } else {
            actionRequest.setAttribute(ERROR,"Loi");
        }
    }
}
