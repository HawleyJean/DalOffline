package com.csci3130.daloffline.backend;

import org.apache.commons.beanutils.BeanUtils;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Separate Java service class.
 * Backend implementation for the address book application, with "detached entities"
 * simulating real world DAO. Typically these something that the Java EE
 * or Spring backend services provide.
 */
// Backend service class. This is just a typical Java backend implementation
// class and nothing Vaadin specific.
public class StudentService {

    // Create dummy data by randomly combining first and last names
    static String[] fnames = { "Peter", "Alice", "John", "Mike", "Olivia",
            "Nina", "Alex", "Rita", "Dan", "Umberto", "Henrik", "Rene", "Lisa",
            "Linda", "Timothy", "Daniel", "Brian", "George", "Scott",
            "Jennifer" };
    static String[] lnames = { "Smith", "Johnson", "Williams", "Jones",
            "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor",
            "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin",
            "Thompson", "Young", "King", "Robinson" };

    private static StudentService instance;

    public static StudentService createDemoService() {
        if (instance == null) {

            final StudentService studentService = new StudentService();

            Random r = new Random(0);
            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < 100; i++) {
                Student student = new Student();
                student.setFirstName(fnames[r.nextInt(fnames.length)]);
                student.setLastName(lnames[r.nextInt(fnames.length)]);
                student.setStudentID("B00"+(int)(100000+r.nextFloat()*900000));
                student.setEmail(student.getFirstName().substring(0,1) + student.getLastName().substring(0,1) + (int)(100000+r.nextFloat()*900000) + "@dal.ca");
                student.setMajor("Computer Science");
                
                studentService.save(student);
            }
            instance = studentService;
        }

        return instance;
    }

    private HashMap<Long, Student> students = new HashMap<>();
    private long nextId = 0;

    public synchronized List<Student> findAll(String stringFilter) {
        ArrayList arrayList = new ArrayList();
        for (Student student : students.values()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                			|| student.toString().toLowerCase().contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(student.clone());
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(StudentService.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
        Collections.sort(arrayList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return (int) (o2.getId() - o1.getId());
            }
        });
        return arrayList;
    }

    public synchronized long count() {
        return students.size();
    }

    public synchronized void delete(Student value) {
    	students.remove(value.getId());
    }

    public synchronized void save(Student entry) {
        if (entry.getId() == null) {
            entry.setId(nextId++);
        }
        try {
            entry = (Student) BeanUtils.cloneBean(entry);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        students.put(entry.getId(), entry);
    }

}
