package src;

import java.util.Hashtable;

public class SymbolTable extends Hashtable<String,Object>{

    static SymbolTable globalTable;
    
    static {globalTable = new SymbolTable();}

    static void setValue(String id, Variable value){
//        System.out.println("From symboltable setvalue: " + id);
        globalTable.put(id,value);
        // System.out.println("==============");
        // System.out.println(((Variable)globalTable.get(id)).getType());
    }

    static Variable getValue(String id){
        //return (Integer) globalTable.get(id);
        if (globalTable.get(id).getClass() == Variable.class )
            return (Variable) globalTable.get(id);
        else {
            if ((int)globalTable.get(id) == 1)
                return new Variable(1);
            else if((int)globalTable.get(id) == 2)
                return new Variable(true);
            else if((int)globalTable.get(id) == 3)
                return new Variable(1.0);
        }
        return null;
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
