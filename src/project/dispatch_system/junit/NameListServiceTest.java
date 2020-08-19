package project.dispatch_system.junit;

import project.dispatch_system.control.service.NameListService;
import project.dispatch_system.control.service.TeamException;
import project.dispatch_system.model.staff.Employee;
import org.junit.jupiter.api.Test;

/**
 * @author echofzoe
 * @version v1.0
 * @ClassName NameListServiceTest
 * @Description 对 NameListService 类的测试
 * @date 2020/5/25 19:23
 */
public class NameListServiceTest {
    @Test
    public void testGetAllEmployees() {
        NameListService service = new NameListService();
        Employee[] employees = service.getAllEmployees();

        for (int i = 0; i < employees.length; i++) {
            System.out.println(employees[i]);
        }
    }

    @Test
    public void testGetEmployee() {
        NameListService service = new NameListService();
        int id = 3;
        try {
            System.out.println(service.getEmployee(id));
        } catch (TeamException e) {
            e.printStackTrace();
        }
    }
}
