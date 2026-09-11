package fu.de200118;

import fu.de200118.dao.EmployeeDAO;
import fu.de200118.pojo.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HSF302_Chapter1");
        try {
            EntityManager em = emf.createEntityManager();
            EmployeeDAO empDAO = new EmployeeDAO(emf);
            testToDo04(empDAO);

        } finally {
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }

    public static void testToDo03(EmployeeDAO employeeDAO) {
        System.out.println("========== TEST TODO 0.3: CREATE ==========");
        Employee emp = new Employee("Nguyen Van A", "nguyenvana@gmail.com");
        System.out.println("Trước khi save: ID = " + emp.getId());

        employeeDAO.save(emp);

        System.out.println("Sau khi save: ID = " + emp.getId());
        System.out.println("Thông tin nhân viên: " + emp);
    }

    public static void testToDo04(EmployeeDAO employeeDAO) {
        System.out.println("========== TEST TODO 0.4: READ ==========");

        // 1. Test findById với ID tồn tại (ví dụ ID = 1L)
        System.out.println("--- 1. findById (tồn tại) ---");
        Employee found = employeeDAO.findByID(1L);
        System.out.println("Kết quả tìm ID 1: " + (found != null ? found : "Không tìm thấy"));

        // 2. Test findById với ID không tồn tại
        System.out.println("\n--- 2. findById (không tồn tại) ---");
        Employee notFound = employeeDAO.findByID(999999L);
        System.out.println("Kết quả tìm ID 999999: " + notFound); // Kỳ vọng trả về null

        // 3. Test findAll
        System.out.println("\n--- 3. findAll ---");
        List<Employee> list = employeeDAO.findAll();
        System.out.println("Tổng số nhân viên: " + list.size());
        for (Employee e : list) {
            System.out.println(" + " + e);
        }
    }
}
