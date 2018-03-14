import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Register {
    private Map<String, Integer> register ;
    private String fileName;
    private Scanner scanner;
    private int highestValue;

    public Register(String fileName){
        this.fileName = fileName;
        register= new HashMap();
        highestValue=Integer.MIN_VALUE;
    }

    public void readRegister() {
        // read only name of register
        try {
            scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (String registerName : line.split(" ")) {
                    register.put(registerName, 0);
                    break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        readLine();
    }

    private void readLine() {
        // read all line and split for parts
        try {
            String firstRegister = "";
            String action = "";
            int number = 0;
            String secondRegister = "";
            String sign = "";
            int comparingValue = 0;
            scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String splitString = scanner.nextLine();
                int positionInLine = -1;
                for (String ss : splitString.split(" ")) {
                    positionInLine++;
                    switch (positionInLine) {
                        case 0: {
                            firstRegister = ss;
                            break;
                        }
                        case 1: {
                            action = ss;
                            break;
                        }
                        case 2: {
                            number = Integer.parseInt(ss);
                            break;
                        }
                        case 4: {
                            secondRegister = ss;
                            break;
                        }
                        case 5: {
                            sign = ss;
                            break;
                        }
                        case 6: {
                            comparingValue = Integer.parseInt(ss);
                            break;
                        }
                        default:
                            break;
                    }
                }
                if(checkCondition(secondRegister, sign, comparingValue)){
                    action(firstRegister, number, action);
                }
                checkHighestValue();
            }


        } catch (FileNotFoundException e) {
            ;
        }
    }

    private boolean checkCondition(String registerName, String sign, int number) {
        boolean flag = false;
        switch (sign) {
            case "==": {
                if (register.get(registerName) == number) flag=true;
                break;
            }
            case "!=": {
                if (register.get(registerName) != number) flag=true;
                break;
            }
            case ">=": {
                if (register.get(registerName) >= number) flag=true;
                break;
            }
            case ">": {
                if (register.get(registerName) > number) flag=true;
                break;
            }
            case "<=": {
                if (register.get(registerName) <= number) flag=true;
                break;
            }
            case "<": {
                if (register.get(registerName) < number) flag=true;
                break;
            }

        }
        return flag;
    }

    private void action(String registerName , int valueToIncOrDec, String condition){
        int x = register.get(registerName);
        if(condition.compareTo("inc") == 0) {
            register.put(registerName, x+valueToIncOrDec);
        }
        else {
            register.put(registerName, x-valueToIncOrDec);
        }

    }

    public int getLargestValueFromRegisters(){
        int max = Integer.MIN_VALUE;
        for(int value: register.values()){
            if(value > max) max = value;
        }
        return max;
    }

    private void checkHighestValue(){
        for(int value: register.values()){
            if(value>highestValue) highestValue=value;
        }
    }

    public int getHighestValueInRegisterDuringProcess(){
        return highestValue;
    }
}