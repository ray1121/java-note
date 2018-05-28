/**
 * SimpleDotCom
 */ 
public class SimpleDotCom {
    private int numOfHits =0;
    private int[] locationCells;
    /**
     * @return the numOfHits
     */
    public int getNumOfHits() {
        return numOfHits;
    }
    /**
     * @param locationCells the locationCells to set
     */
    public void setLocationCells(int[] locationCells) {
        this.locationCells = locationCells;
    }
    public String checkUserAnswer(String userAnswer) {
        int localnAswer = Integer.parseInt(userAnswer);
        String result = "miss";
        for (int cell : locationCells) {
            if (cell == localnAswer) {
                result = "hit";
                numOfHits++;
                break;
            }
        }
        if (numOfHits == locationCells.length) {
            result = "Youwin";
        }
        System.out.println(result);
        return result;
    }
}

/**
 * SimpleDotCom测试代码
 */
class SimpleDotComTestDrive {
    public static void main(String[] args) {
        /**
         * 新建一个目标网站的实例对象
         */
        SimpleDotCom dot = new SimpleDotCom();
        int[] locations= {2,3,4};//创建一个目标的位置数组
        dot.setLocationCells(locations);
        //伪造用户输入的猜测答案
        String userGuess = "2";
        String result = dot.checkUserAnswer(userGuess);
        String testResult = "failed";
        if (result == "hit") {
            testResult = "pass";
        }
        System.out.println(testResult);
    }

}