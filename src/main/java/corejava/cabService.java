package corejava;

public class employee {
  private   int id;
   private String name;
    private String area;

    employee(int Emp_id, String emp_name1, String emp_area) {
        id = Emp_id;
        name = emp_name1;
        area = emp_area;
    }

    public static void main(String[] args) {
        employee e = new employee(200, "jbj", "Banglore");
        System.out.println(e.name);
        System.out.println(e.id);
        System.out.println(e.area);


    }
}