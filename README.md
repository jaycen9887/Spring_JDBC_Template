# Spring_JDBC_Template
#### This is a Spring JDBC Template using the com.mysql.cj.jdbc.Driver data source driver. 

## Project structure 

```
|--src/main/
|	
|	|--java
|	|	|--com/jdbc/template
|	|	|	**SpringJdbcTemplateApplication.java
|	|	|	|--config
|	|	|	|	**JdbcConfig.java
|	|	|	|--dao
|	|	|	|	|--impl
|	|	|	|	|	**EmployeeDAOImpl.java
|	|	|	|	|--models
|	|	|	|	|	**EmployeeDAO.java
|	|	|	|--errorHandlers
|	|	|	|	**CustomSQLErrorCodeTranslator.java
|	|	|	|--mapper
|	|	|	|	**EmployeeRowMapper.java
|	|	|	|--models
|	|	|	|	**Employee.java
|	|	|	|--services
|	|	|	|	**EmpoyeeTest.java
|	|--resources
|		**application.properties
```
							
### Run Template as is

1. Create a table inside your database called "Employee"

```sql
CREATE TABLE `Employee` (
  `id` int(11) unsigned NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
)	
```

2. Insert your mysql database information (username, password, and url) into the application.properties file
					
```properties
spring.datasource.username=[username]
spring.datasource.password=[password]
spring.datasource.url=jdbc:mysql://[url]:[port]/[database name]
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

```

3. Run it! 

### Notes

a. The EmployeeTest class [com.jdbc.template.services] simply just saves a single "employee" to the table and outputs if it was successful or not.

```java
@Service
public class EmployeeTest {

	@Autowired
	EmployeeDAO employeeDAO;

	@PostConstruct
	public void test() {

		Employee emp1 = new Employee(0, "[NAME HERE]", "[ROLE HERE]");

		int output = employeeDAO.save(emp1);

		if (output != 0) {
			System.out.println("Employee " + emp1.getName() + " was saved successfully with id: " + emp1.getId());
		} else {
			System.out.println("Employee " + emp1.getName() + "with id: " + emp1.getId() + " could not be saved.");
		}
	}
}
```

	The @PostConstruct annotation just executes the "test" method after all dependencies injections have been completed. 
	This isn't required, this is just a quick way to call the method for demo purposes. 