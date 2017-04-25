package src;

import java.util.Hashtable;

public class SymbolTable extends Hashtable<String,Object>{

    static SymbolTable globalTable;
    
    static {globalTable = new SymbolTable();}

    static void setValue(String id, Variable value){
//        System.out.println("From symboltable setvalue: " + id);
        //parser.print_error(value.toString);
        globalTable.put(id,value);
        // System.out.println("==============");
        // System.out.println(((Variable)globalTable.get(id)).getType());
    }

    static Variable getValue(String id){
        //parser.print_error(id);
        // get type instead, int1, bool2
        // parser.print_error(id); // y
        // parser.print_error(globalTable.get(id).toString()); // 2
        // parser.print_error(globalTable.get(id).getClass().getName());
        return (Variable) globalTable.get(id);
        //return new Variable(globalTable.get(id));
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
