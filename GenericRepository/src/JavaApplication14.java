package javaapplication14;

import java.lang.reflect.InvocationTargetException;

public class JavaApplication14 {

    public static void main(String[] args) throws InvalidEntityException {
        Product p1 = new Product("koko", 80);
        Student s1 = new Student("Igoooor'");
        
        GenericRepository<Product> productRepo = new GenericRepository<>();
      //  System.out.println(productRepo.getAsCsvString(p1));

       // productRepo.saveCsv(p1);
         
        // System.out.println(productRepo.read(p1));
        GenericRepository<Student> studentRepo = new GenericRepository<>();
        studentRepo.saveCsv(s1);
        Student s2 = studentRepo.read(s1);
        System.out.println(s2);
        System.out.println(studentRepo.getAsCsvString(s1));
    }
    
}
