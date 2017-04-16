package src;

import java.util.Hashtable;

public class SymbolTable extends Hashtable<String,Object>{

    static SymbolTable globalTable;
    
    static {globalTable = new SymbolTable();}

    static void setValue(String id, int value){
        globalTable.put(id,value);
        System.out.println("==============");
        System.out.println(globalTable.getType(id));
        System.out.println("eeeeeeeeeee");
    }

    static Integer getValue(String id){
        return (Integer) globalTable.get(id);
    }
    
    static Integer getType(String id) {
        return (Integer) globalTable.get(id);
    }
    
    static boolean isExistID(String id) {
        return globalTable.containsKey(id);
    }
    
    static void setType(int type, String id) {
        globalTable.put(id ,type);
    }
}
