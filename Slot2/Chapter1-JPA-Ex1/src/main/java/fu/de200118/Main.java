package fu.de200118;

import fu.de200118.dao.EmployeeDAO;
import fu.de200118.pojo.Employee;
import fu.de200118.pojo.Gender;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EmployeeDAO dao = new EmployeeDAO();

        System.out.println("========== DEMO TODO 0.8: CRUD FLOW ==========");
        //===== Create =====
        Employee emp = new Employee("Nguyen Van A", "c@fpt.edu.vn",
                new BigDecimal("15000000"), Gender.MALE, LocalDate.of(2022, 3, 1));

        dao.save(emp);
        System.out.println("Da tao: " + emp);

        //===== READ =====
        Employee found = dao.findById(emp.getId());
        System.out.println("Doc lai: " + found);

        // ===== UPDATE =====
        found.setSalary(new BigDecimal("17000000"));
        Employee updated = dao.update(found);
        System.out.println("Sau update: " + updated);

        // Doc lai de kiem chung
        Employee reChecked = dao.findById(emp.getId());
        System.out.println("Kiem tra lai sau update: " + reChecked);

        // ===== DELETE =====
        dao.delete(emp.getId());
        Employee afterDelete = dao.findById(emp.getId());
        System.out.println("Sau khi xoa, tim lai: " + afterDelete); // ky vong: null

    }


}
