public class TestObject {
    public String name;
    protected float hp;
      
    public String toString(){
        return name;
    }
      
    public static void main(String[] args) {
         
        TestObject h = new TestObject();
        h.name = "盖伦";
        System.out.println(h.toString());
        //直接打印对象就是打印该对象的toString()返回值
        System.out.println(h);
    }
}