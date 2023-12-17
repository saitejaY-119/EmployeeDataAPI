package com.EmployeeFinal.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController
{

    private  EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/getEmployeeById")
    public Employee getEmployeeById(@RequestParam("Id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/createEmployee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/updateEmployee")
    public Employee updateEmployee(@RequestParam("Id") Long id, @RequestBody Employee employee) throws Exception {
        Employee existingEmployee = employeeService.getEmployeeById(id);

        if (existingEmployee != null) {
            // Set the ID to ensure it's the same as the path variable
            employee.setId(id);
            return employeeService.saveEmployee(employee);
        } else {
            throw new Exception("Employee not found with id: " + id);
        }
    }

    @DeleteMapping("/deleteEmployee")
    public void deleteEmployee(@RequestParam("Id") Long id) throws Exception {
        Employee existingEmployee = employeeService.getEmployeeById(id);

        if (existingEmployee != null) {
            employeeService.deleteEmployee(id);
        } else {
            throw new Exception("Employee not found with id: " + id);
        }
    }
}
