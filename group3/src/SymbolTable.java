package src;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.List;

public class SymbolTable extends Hashtable<String,Object>{

    static List<SymbolTable> variableTable;

    static {
        SymbolTable globalTable = new SymbolTable();
        variableTable = new ArrayList<SymbolTable>();
        variableTable.add(globalTable);
    }
    
    static void newLocalTable() {
        SymbolTable newLocal = new SymbolTable();
        variableTable.add(newLocal);
    }
    
    static void deleteLocalTable() {
        variableTable.remove(variableTable.size()-1);
    }
    
    static void setObject(String id, Object value) {
        if (isExistID(id)) {
            for (int i=variableTable.size()-1; i >= 0; i--){
                SymbolTable symbolTable = variableTable.get(i);
                if (symbolTable.containsKey(id)){
                    symbolTable.put(id, value);
                    variableTable.set(i, symbolTable);
                    break;
                }
            }
        } else {
            SymbolTable symbolTable = variableTable.get(variableTable.size()-1);
            symbolTable.put(id, value);
            variableTable.set(variableTable.size()-1, symbolTable);
        }
        
    }
    
    static void setValue(String id, Variable value) {
        setObject(id, value);
    }
    
    static void setType(int value, String id) {
        setObject(id, value);
    }
    
    static void setFunction(String id, Aexp value){
        setObject(id, value);
    }
    
    static Object getObject(String id){
        for (int i=variableTable.size()-1; i >= 0; i--){
            SymbolTable symbolTable = variableTable.get(i);
            if (symbolTable.containsKey(id)){
                return symbolTable.get(id);
            }
        }
        return null;
    }

    static Variable getValue(String id){
        if (getObject(id).getClass() != Variable.class)
            return null;
        return (Variable) getObject(id);
    }
    
    static Integer getType(String id) {
        if (getObject(id).getClass() == Variable.class){
            Variable v = getValue(id);
            if (v.getType() == Variable.ValType.INT){
                return 1;
            }
            else if(v.getType() == Variable.ValType.BOOL){
                return 2;
            }
            else if(v.getType() == Variable.ValType.FLOAT){
                return 3;
            }
        }
        return (Integer) getObject(id);
    }
    
    static Aexp getFunction(String id){
        return (Aexp) getObject(id);
    }
    
    static boolean isExistID(String id) {
        boolean result = false;
        for (int i=variableTable.size()-1; i >= 0; i--){
            SymbolTable symbolTable = variableTable.get(i);
            result = symbolTable.containsKey(id);
        }
        return result;
    }
    
   
}
