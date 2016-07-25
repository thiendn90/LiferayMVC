package ex.employee.service;

import ex.employee.dao.EmployeeDao;
import ex.employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by toannh on 7/25/2016.
 */
@Service
public class EmployeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public Employee saveOrUpdate(Employee employee) {
        if(employee.getId() != null){
            employeeDao.update(employee);
        }else{
            employeeDao.save(employee);
        }
        return null;
    }
}
