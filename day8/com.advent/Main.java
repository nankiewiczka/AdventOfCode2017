
public class Main {

        public static void main(String[] args) {
        String fileName= "/home/karolina/Documents/AdventOfCode2017/day8/date.txt";
        Register r = new Register(fileName);
        r.readRegister();
        System.out.println(r.getLargestValueFromRegisters());
        System.out.println(r.getHighestValueInRegisterDuringProcess());
        }
    }
