package optimitzacio;

public class Temps {

    public static final String T_TAULA = "TEMPS";
    
    public static final String T_NUM1 = "NUM1";
    public static final String T_NUM2 = "NUM2";
    
    public static final String SELECT_ALL = "SELECT "+T_NUM1 +", "+T_NUM1+" FROM "+T_TAULA;
        
    private int num1;
    private int num2;
    
    public Temps(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }
    
    public Temps() {
        this.num1 = 0;
        this.num2 = 0;
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }
    
    public static String createQuery(){
        return Temps.SELECT_ALL;
    }
    
    public static String createQuery(String[] where){
        String st = Temps.SELECT_ALL+" WHERE ";
        for(int i = 0; i<where.length;i++){
            st += where[i]+"= ?";
            if(where.length>1) st+=" AND ";
        }
        
        return st;
    }

    public static String createQuery(String select, String[] where){
        String st = "Select "+select+" FROM "+Temps.T_TAULA+" WHERE ";
        for(int i = 0; i<where.length;i++){
            st += where[i]+"= ?";
            if(where.length>1&&i!=where.length-1) st+=" AND ";
        }
        
        return st;
    }

    @Override
    public String toString() {
        return "Temps{" + "num1=" + num1 + ", num2=" + num2 + '}';
    }   
}