package src;

import java.time.Clock;
import java.util.LinkedList;

public class Astat {
    int statementType;
    int assignmentStatementType = 999;
    public static int assignment = 0;
    public static int varDeclaration = 1;
    public static int print = 2;
    public static int ifthen = 3;
    public static int block = 4;
    public static int whileloop = 5;
    public static int funDeclaration = 6;
    public static int returnStatement = 7;
    public static int forloop = 8;
    public static int funCall = 9;
    public static int printline = 10;

    /*
     * assignment statement: variable = expr
     *
     */
    String assVariable;
    Aexp assExpr;
    public static Astat assignment(String Variable, Aexp expr) {
        Astat statement = new Astat();
        statement.statementType = assignment;
        statement.assignmentStatementType = assignment;
        statement.assVariable = Variable;
        // parser.print_error(expr.getValue().toString()); // true
        //System.out.println("assignemnt, value: " + expr.getValue().toString());
        statement.assExpr = expr;
        return statement;
    }

    /*
     * while loop: while whileCondition begin whileBody end
     *
     */
    Aexp whileCondition;
    Astat whileBody;
    public static Astat whileloop(Aexp condition, Astat WhileBody) {
        Astat statement = new Astat();
        statement.statementType = whileloop;
        statement.whileCondition = condition;
        statement.whileBody = WhileBody;
        return statement;
    }
    
    Astat forVarDecl;
    Aexp forCondition;
    Astat forVarAssgList;
    Astat forBody;
    public static Astat forloop(Astat varDecl, Aexp condition, Astat varAssgList, Astat forBody) {
        Astat statement = new Astat();
        statement.statementType = forloop;
        statement.forVarDecl = varDecl;
        statement.forCondition = condition;
        statement.forVarAssgList = varAssgList;
        statement.forBody = forBody;
        return statement;
    }
    
    
    Integer varType;
    Lstat varDeclList;
    public static Astat varDec(int type, Lstat l) {
        Astat statement = new Astat();
        statement.statementType = varDeclaration;
        statement.varType = type;
        // parser.print_error(l.statementList.getClass().getName());
        // parser.print_error((new Integer(l.statementList.size())).toString());
        //parser.print_error((new String(l.statementList.get(0).assVariable)).toString());
        //System.out.println("Varname: " + (new String(l.statementList.get(0).assVariable)).toString());
        statement.varDeclList = l;
        //parser.print_error((new String(statement.varDeclList.statementList.get(0).varID)).toString());
        // System.out.println("l: " + (new String(statement.varDeclList.statementList.get(0).varID)).toString());
        return statement;
    }
    
//    String varID;
    public static Astat oneVarDec(String id) {
        Astat statement = new Astat();
        statement.statementType = varDeclaration;
        statement.assVariable = id;
        return statement;
    }
    /*
     * if then statement: if ifcondition then ifbody
     *
     */
    Aexp ifcondition;
    Astat ifbody;
    public static Astat ifthen(Aexp condition, Astat Ifbody) {
        Astat statement = new Astat();
        statement.statementType = ifthen;
        statement.ifcondition = condition;
        statement.ifbody = Ifbody;

        return statement;
    }

    /*
     * print statement: print e
     */
    Aexp printE;

    public static Astat print(Aexp expr) {
        Astat statement = new Astat();
        statement.statementType = print;
        statement.printE = expr;
        return statement;
    }
    
    public static Astat printline() {
        Astat statement = new Astat();
        statement.statementType = printline;
        return statement;
    }

    /*
     * block statement: begin statement_list end
     */
    Lstat blockBody;

    public static Astat block(Lstat l) {
        Astat statement = new Astat();
        statement.statementType = block;
        statement.blockBody = l;
        return statement;
    }
    
    String functionName;
    Lstat paramList;
    Astat functionBody;
    public static Astat functionDefine(Lstat functionName, Lstat paramList, Astat funcBody){
        Astat statement = new Astat();
        statement.statementType = funDeclaration;
        statement.functionName = functionName.getstat();
        statement.paramList = paramList;
        statement.functionBody = funcBody;
        return statement;
    }
    public String getFunctionName(){
        return functionName;
    }
    
    Listexp argList;
    public static Astat functionCall(String functionName, Listexp args){
        Astat statement = new Astat();
        statement.statementType = funCall;
        statement.functionName = functionName;
        statement.argList = args;
        return statement;
    }
    public Listexp getArgList(){
        return argList;
    }

    public String getstat() {
        if (statementType == varDeclaration) {
            return assVariable;
        } else if (statementType == assignment) {
            return assVariable + ":=" + assExpr.getexp();
        } else if (statementType == ifthen) {
            return "if " + ifcondition.getexp() + " " + ifbody.getstat();
        } else if (statementType == print) {
            return "print " + printE.getexp();
        } else if (statementType == whileloop) {
            return "while (" + whileCondition.getexp() + ") " + whileBody.getstat();
        } else if (statementType == forloop) {
           return "for (" + forVarDecl.getstat() + "; " + forCondition.getexp() + "; " + forVarAssgList.getstat() + ") " + forBody.getstat();
        } else if (statementType == block) {
            return "{ " + blockBody.getstat() + " }";
        } else if (statementType == funDeclaration) {
            return "define " + functionName + " as function() " + functionBody.getstat();
        } else if (statementType == funCall){
            return functionName + "(" + argList.getExp() + ")";
        } else {
            return "unknown";
        }
    }

    public void execute() {
//         parser.print_error(statementType);
//         System.out.println("statementType: " + statementType);
//         parser.print_error((new String(l.statementList.get(0).assVariable)).toString());
//        System.out.println(statementType);
        if (statementType == assignment) {
//            
//            System.out.println("Assvariable " + assVariable + " assignment " + assExpr.getValue().getType());
//            System.out.println("symbol" + SymbolTable.globalTable.get(assVariable) );
            if((assExpr.getValue().getType() == Variable.ValType.INT) && (SymbolTable.getType(assVariable) == 1)){
                SymbolTable.setValue(assVariable, assExpr.getValue());
            }
            else if((assExpr.getValue().getType() == Variable.ValType.BOOL) && (SymbolTable.getType(assVariable) == 2)){
                SymbolTable.setValue(assVariable, assExpr.getValue());  
            }
            else if((assExpr.getValue().getType() == Variable.ValType.FLOAT) && (SymbolTable.getType(assVariable) == 3)){
                SymbolTable.setValue(assVariable, assExpr.getValue());
            }
            else {
                parser.print_error("Variable Type mismatch");
            }
//            SymbolTable.setValue(assVariable, assExpr.getValue());
        } else if (statementType == varDeclaration) {
           // if ( !SymbolTable.isExistID(varID)) {
           // parser.print_error((new String(varDeclList.statementList.get(0).varID)).toString());

           for (Astat s : varDeclList.statementList) {
               // parser.print_error((new Integer(varType)).toString());
               // parser.print_error(assExpr.toString());
               // System.out.println("assignemntStatementType: " + s.assignmentStatementType);
               SymbolTable.setType(varType, s.assVariable);
               if (s.assignmentStatementType == assignment) {
//                    System.out.println("hello2");
//                    System.out.println("s.assVariable: " + s.assVariable);
//                    System.out.println("assExpr.getValue: " + s.assExpr.getValue());
//                    SymbolTable.setValue(s.assVariable, s.assExpr.getValue());
                    if((s.assExpr.getValue().getType() == Variable.ValType.INT) && (SymbolTable.getType(s.assVariable) == 1)){
                        SymbolTable.setValue(s.assVariable, s.assExpr.getValue());
                    }
                    else if((s.assExpr.getValue().getType() == Variable.ValType.BOOL) && (SymbolTable.getType(s.assVariable) == 2)){
                        SymbolTable.setValue(s.assVariable, s.assExpr.getValue());  
                    }
                    else if((s.assExpr.getValue().getType() == Variable.ValType.FLOAT) && (SymbolTable.getType(s.assVariable) == 3)){
                        SymbolTable.setValue(s.assVariable, s.assExpr.getValue());
                    }
                    else {
                        parser.print_error("Variable Type mismatch");
                    }
               }
               //SymbolTable.setValue(assVariable, assExpr.getValue());
           }
          //  } else {
            //    System.err.println("Duplicate ID: " + this.varID);
           // }
        } else if (statementType == ifthen) {
            SymbolTable.newLocalTable();
            if (ifcondition.getValue().getBoolVal() != false) {
                ifbody.execute();
            }
            SymbolTable.deleteLocalTable();
        } else if (statementType == whileloop) {
            SymbolTable.newLocalTable();
            for (;;) {

                if (whileCondition.getValue().getBoolVal() != false) {
                    whileBody.execute();
                } else {
                    break;
                }

            }
            SymbolTable.deleteLocalTable();

        } else if (statementType == forloop) {
            SymbolTable.newLocalTable();
            forVarDecl.execute();
            for (;;) {
                if (forCondition.getValue().getBoolVal() != false) {
                    forBody.execute();
                    forVarAssgList.execute();
                } else {
                    break;
                }

            }
            SymbolTable.deleteLocalTable();

        } else if (statementType == print) {

            System.out.print(printE.getValue());

        } else if (statementType == printline) {
            
            System.out.println();
            
        } else if (statementType == block) {
            
            for (Astat s : blockBody.statementList) {
                s.execute();
            }
            
        } else if (statementType == funDeclaration) {
            
            SymbolTable.setFunction(functionName, new Aexp(this));
            
        } else if (statementType == funCall) {
            
            Astat functDeclared = SymbolTable.getFunction(functionName).getFuncStatement();
            SymbolTable.newLocalTable();
            if (argList != null && functDeclared.paramList != null) {
                LinkedList<Aexp> list = argList.exprList;
                int index = 0;
                while(!list.isEmpty()){  
                    Aexp assExpr = list.poll(); 
                    String assVariable = functDeclared.paramList.statementList.get(index).varDeclList.statementList.get(0).assVariable;
//                    System.out.println("iceza");
//                    System.out.println("value = "+assExpr.getValue());
//                    System.out.println("variable = "+assVariable);
                    int varType = functDeclared.paramList.statementList.get(index).varType;
                   
                    if(assExpr.getEType() == Aexp.AexpType.VALUE)
                        SymbolTable.setType(varType, assVariable);
                    
                    if((assExpr.getValue().getType() == Variable.ValType.INT) && (SymbolTable.getType(assVariable) == 1)){
                        SymbolTable.setValue(assVariable, assExpr.getValue());
                    }
                    else if((assExpr.getValue().getType() == Variable.ValType.BOOL) && (SymbolTable.getType(assVariable) == 2)){
                        SymbolTable.setValue(assVariable, assExpr.getValue());  
                    }
                    else if((assExpr.getValue().getType() == Variable.ValType.FLOAT) && (SymbolTable.getType(assVariable) == 3)){
                        SymbolTable.setValue(assVariable, assExpr.getValue());
                    }
                    else {
                        parser.print_error("Variable Type mismatch");
                    }
                    index++; 
                }  
            }
            functDeclared.functionBody.execute();
            SymbolTable.deleteLocalTable();
            
        }
    }
}
