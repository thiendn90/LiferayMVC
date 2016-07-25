package ex.employee.dao.impl;

import ex.employee.dao.EmployeeDao;
import ex.employee.model.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by toannh on 7/21/2016.
 */
@Repository
@Transactional
public class EmployeeDaoImpl extends GenericDaoImpl<Employee> implements EmployeeDao{
}
