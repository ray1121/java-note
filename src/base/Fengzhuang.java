class Dog{
    private int leg = 20;
    /**
     * @return the leg
     */
    public int getLeg() {
        return leg;
    }
    /**
     * @param leg the leg to set
     */
    public void setLeg(int leg) {
        this.leg = leg;
    }
    public void localVariable() {
        int a = 1;
        System.out.println(a);
    }
}

class DogTestDrive{
    public static void main(String[] args) {
        Dog dog = new Dog();
        // dog.setLeg(30);
        System.out.println(dog.getLeg());
    }
}