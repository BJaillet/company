package fr.treeptik.runtime;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.treeptik.model.Department;
import fr.treeptik.model.Employee;
import fr.treeptik.model.Project;

public class Runtime {

	public static void main(String[] args) {

		EntityManager entityManager = Persistence.createEntityManagerFactory(
				"sample").createEntityManager();

		// TypedQuery<Long> query = entityManager.createQuery(
		//
		// "select count(e) from Employee e where e.salary<30000", Long.class);
		// Employee employee = entityManager.find(Employee.class, 1);
		// entityManager.close();
		//
		// Employee employee = new Employee();
		// employee.setId(2);
		// TypedQuery<Employee> query =
		// entityManager.createQuery("select e from Employee e where e.id = ?1 and e.name = :employeeName",
		// Employee.class);
		// query.setParameter(1, employee.getId());
		// query.setParameter("employeeName", employee.getName());
		// List<Project> resultList = query.getResultList();

		// entityManager.close();

		// for (Project project : resultList) {
		// System.out.println(project.getEmployees());
		// }

		//upgradeWorstSalaries(entityManager);
		TypedQuery<Employee> query = entityManager.createNamedQuery("findEmployeeById", Employee.class);
		query.setParameter("id", 1);
		System.out.println(query.getSingleResult());

	}

	public static void listEmployees(EntityManager entityManager) {
		TypedQuery<Employee> query = entityManager.createQuery(
				"select e from Employee e", Employee.class);
		List<Employee> resultList = query.getResultList();

		entityManager.close();

		for (Employee employee : resultList) {
			System.out.println(employee.toString());
		}
	}

	public static void listEmployeesDepartment(EntityManager entityManager) {
		TypedQuery<Employee> query = entityManager.createQuery(
				"select e from Employee e", Employee.class);
		List<Employee> resultList = query.getResultList();

		entityManager.close();

		for (Employee employee : resultList) {
			System.out
					.println(employee.getFirstName() + " "
							+ employee.getLastName() + " - "
							+ employee.getDepartment());
		}
	}

	public static void listEmployeesWithProject(EntityManager entityManager) {
		TypedQuery<Employee> query = entityManager.createQuery(
				"select e from Employee e join fetch e.projects",
				Employee.class);
		List<Employee> resultList = query.getResultList();

		entityManager.close();

		for (Employee employee : resultList) {
			System.out.println(employee.getFirstName() + " "
					+ employee.getLastName() + " - " + employee.getProjects());
		}
	}

	public static void listProjectsEmployees(EntityManager entityManager) {
		TypedQuery<Project> query = entityManager.createQuery(
				"select distinct p from Project p join fetch p.employees",
				Project.class);
		List<Project> resultList = query.getResultList();

		entityManager.close();

		for (Project project : resultList) {
			System.out.println(project.getName() + " - "
					+ project.getEmployees());
		}
	}

	public static void listEmployeesNoManagerCloudUnit(
			EntityManager entityManager) {
		TypedQuery<Employee> query = entityManager
				.createQuery(
						"select e from Employee e join e.projects p where p.name = 'Cloudunit' and e.manager = null",
						Employee.class);
		List<Employee> resultList = query.getResultList();

		entityManager.close();

		for (Employee employee : resultList) {
			System.out.println(employee.getFirstName() + " "
					+ employee.getLastName());
		}
	}

	public static void listDepartmentAlpha(EntityManager entityManager) {
		TypedQuery<Department> query = entityManager.createQuery(
				"select d from Department d order by d.name asc",
				Department.class);
		List<Department> resultList = query.getResultList();

		entityManager.close();

		for (Department department : resultList) {
			System.out.println(department);
		}
	}

	public static void listSalariesOrder(EntityManager entityManager) {
		TypedQuery<Employee> query = entityManager.createQuery(
				"select e from Employee e order by e.salary desc",
				Employee.class);
		List<Employee> resultList = query.getResultList();

		entityManager.close();

		for (Employee employee : resultList) {
			System.out.println(employee);
		}
	}

	public static void managerMeanSalary(EntityManager entityManager) {
		TypedQuery<Double> query = entityManager.createQuery(
				"select avg(m.salary) from Manager m", Double.class);
		List<Double> resultList = query.getResultList();

		entityManager.close();

		System.out.println(resultList);
	}

	public static void maxSalaryEmployeeNoManager(EntityManager entityManager) {
		TypedQuery<Employee> query = entityManager.createQuery(
						"select e from Employee e where e not in (select m from Manager m) and e.manager = null order by e.salary desc",
						Employee.class);
		query.setMaxResults(1);
		List<Employee> resultList = query.getResultList();

		entityManager.close();

		for (Employee employee : resultList) {
			System.out.println(employee);
		}
	}
	
	public static void leastPaidEmployees(EntityManager entityManager) {
		TypedQuery<Employee> query = entityManager.createQuery(
						"select e from Employee e order by e.salary asc",
						Employee.class);
		query.setMaxResults(2);
		List<Employee> resultList = query.getResultList();

		entityManager.close();

		for (Employee employee : resultList) {
			System.out.println(employee);
		}
	}
	
	public static void listHiredEmployeesDate(EntityManager entityManager) {
		TypedQuery<Employee> query = entityManager.createQuery(
						"select e from Employee e where e.startDate between '2013/04/13' and '2014/04/25'",
						Employee.class);
		query.setMaxResults(2);
		List<Employee> resultList = query.getResultList();

		entityManager.close();

		for (Employee employee : resultList) {
			System.out.println(employee);
		}
	}
	
	public static void listAEmployees(EntityManager entityManager) {
		TypedQuery<Employee> query = entityManager.createQuery(
						"select e from Employee e where e.lastName like 'E%'",
						Employee.class);
		List<Employee> resultList = query.getResultList();

		entityManager.close();

		for (Employee employee : resultList) {
			System.out.println(employee);
		}
	}
	
	public static void listDetroitEmployees(EntityManager entityManager) {
		TypedQuery<Employee> query = entityManager.createQuery(
						"select e from Employee e join e.address a where a.town = 'Detroit'",
						Employee.class);
		List<Employee> resultList = query.getResultList();

		entityManager.close();

		for (Employee employee : resultList) {
			System.out.println(employee);
		}
	}
	
	public static void listEmployeesProjectsPhoneNumbers(EntityManager entityManager) {
		TypedQuery<Employee> query = entityManager.createQuery(
						"select e from Employee e left join fetch e.projects p left join fetch e.phoneNumbers",
						Employee.class);
		List<Employee> resultList = query.getResultList();

		entityManager.close();

		for (Employee employee : resultList) {
			System.out.println(employee + " - " + employee.getProjects() + " - " + employee.getPhoneNumbers());
		}
	}
	
	public static void countUnder30kEmployees(EntityManager entityManager) {
		TypedQuery<Long> query = entityManager.createQuery(
				"select count(e.id) from Employee e where e.salary < 30000", Long.class);
		List<Long> resultList = query.getResultList();

		entityManager.close();

		System.out.println(resultList);
	}
	
	public static void listNotManagedByHarper(EntityManager entityManager) {
		TypedQuery<Project> query = entityManager.createQuery(
						"select pr from Project pr where pr not in (select p from Project p left join p.employees e where e.firstName = 'Harper')",	Project.class);
		List<Project> resultList = query.getResultList();

		entityManager.close();

		for (Project project : resultList) {
			System.out.println(project);
		}
	}
	
	public static void listNotManagerBestSalary(EntityManager entityManager) {
		TypedQuery<Employee> query = entityManager.createQuery(
						"select e from Employee e where e not in (select m from Manager m) and e.phoneNumbers.size != 0 order by e.salary desc",
						Employee.class);
		query.setMaxResults(3);
		List<Employee> resultList = query.getResultList();

		entityManager.close();

		for (Employee employee : resultList) {
			System.out.println(employee);
		}
	}
	
	public static void countDetroitEmployees(EntityManager entityManager) {
		TypedQuery<Long> query = entityManager.createQuery(
						"select count(e.id) from Employee e join e.address a where a.town = 'Detroit'",
						Long.class);
		List<Long> resultList = query.getResultList();

		entityManager.close();

		System.out.println(resultList);
	}
	
	public static void bestSalariesMean(EntityManager entityManager) {
		Query query = entityManager.createNativeQuery(
				"select avg(emp.salary) from (select e.salary from Employee e order by e.salary desc limit 5) as emp");
		BigDecimal result = (BigDecimal) query.getSingleResult();

		entityManager.close();

		System.out.println(result);
	}
	
	public static void listManagersWith2Projects(EntityManager entityManager) {
		TypedQuery<Employee> query = entityManager.createQuery(
						"select m from Manager m where m.projects.size > 1",
						Employee.class);
		List<Employee> resultList = query.getResultList();

		entityManager.close();

		for (Employee employee : resultList) {
			System.out.println(employee);
		}
	}
	
	public static void upgradeWorstSalaries(EntityManager entityManager) {
		Query query = entityManager.createNativeQuery(
						"update Employee e set e.salary = 26000 order by e.salary limit 2",
						Employee.class);
		entityManager.getTransaction().begin();
		query.executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();

		
	}
}
