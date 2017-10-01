package fi.jamk.hawnin.solteqassignmentv1;

/**
 * Created by jmprs on 2017. 09. 25..
 */

public class Employee {

        //Table structure
        public static final String TABLE = "Employee";
        //Table column
        public static final String KEY_ID = "id";
        public static final String KEY_name = "name";
        public static final String KEY_email = "email";
        public static final String KEY_age = "age";
        public static final String KEY_salary = "salary";
        public static final String KEY_position = "position";
        //Attributes
        public int employee_id;
        public String name;
        public String email;
        public int age;
        public int salary;
        public String position;

        public int getEmployee_id() {
                return employee_id;
        }

        public void setEmployee_id(int employee_id) {
                this.employee_id = employee_id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public int getAge() {
                return age;
        }

        public void setAge(int age) {
                this.age = age;
        }

        public int getSalary() {
                return salary;
        }

        public void setSalary(int salary) {
                this.salary = salary;
        }

        public String getPosition() { return position; }

        public void setPosition(String position) { this.position = position; }
}
