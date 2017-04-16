package src;

public class Variable {
    public enum ValType {
        INT, FLOAT, BOOL, VOID
    }
    private final ValType vType;
    private Integer intVal;
    private Double floatVal;
    private Boolean boolVal;
    
    Variable() {
        vType = ValType.VOID;
    }
    
    Variable(Integer x) {
        vType = ValType.INT;
        intVal = x;
    }
    
    Variable(Double x) {
        vType = ValType.FLOAT;
        floatVal = x;
    }
    
    Variable(Boolean x) {
        vType = ValType.BOOL;
        boolVal = x;
    }
    
     /* Getters */
    public ValType getType() {
        return vType;
    }
    public Integer getIntVal() {
        return intVal;
    }
    public Double getFloatVal() {
        return floatVal;
    }
    public Boolean getBoolVal() {
        return boolVal;
    }
    
    @Override
    public String toString() {
        switch (vType) {
        case INT:
            return intVal.toString();
        case FLOAT:
            return floatVal.toString();
        case BOOL:
            return boolVal.toString();
        default:
            return "";
        }
    }
                            
//    public Variable or(Variable x) {
//        if (vType == ValType.BOOL && x.vType == ValType.BOOL) {
//            return new Variable(boolVal || x.boolVal);
//        } else {
//            parser.print_error("Type mismatch at ' |(OR) '");
//        }
//        return this;
//    }
    
//    public Variable and(Variable x) {
//        if (vType == ValType.BOOL && x.vType == ValType.BOOL) {
//            return new Variable(boolVal && x.boolVal);
//        } else {
//            parser.print_error("Type mismatch at ' &(AND) '");
//        }
//        return this;
//    }
    
//    public Variable eq(Variable x) {
//        if (vType == ValType.BOOL && x.vType == ValType.BOOL) {
//            return new Variable(boolVal.equals(x.boolVal));
//        } else if (vType == ValType.INT && x.vType == ValType.INT) {
//            return new Variable(intVal.equals(x.intVal));
//        } else if (vType == ValType.FLOAT && x.vType == ValType.FLOAT) {
//            return new Variable(floatVal.equals(x.floatVal));
//        } else {
//            parser.print_error("Type mismatch at ' eq ' operator");
//        }
//        return this;
//    }
    
//    public Variable ne(Variable x) {
//        if (vType == ValType.BOOL && x.vType == ValType.BOOL) {
//            return new Variable(!boolVal.equals(x.boolVal));
//        } else if (vType == ValType.INT && x.vType == ValType.INT) {
//            return new Variable(!intVal.equals(x.intVal));
//        } else if (vType == ValType.FLOAT && x.vType == ValType.FLOAT) {
//            return new Variable(!floatVal.equals(x.floatVal));
//        } else {
//            parser.print_error("Type mismatch at ' ne '");
//        }
//        return this;
//    }
    
//    public Variable lt(Variable x) {
//        if (vType == ValType.INT && x.vType == ValType.INT) {
//            return new Variable(intVal < x.intVal);
//        } else if (vType == ValType.FLOAT && x.vType == ValType.FLOAT) {
//            return new Variable(floatVal < x.floatVal);
//        } else {
//            parser.print_error("Type mismatch at ' lt '");
//        }
//        return this;
//    }
    
//    public Variable le(Variable x) {
//        if (vType == ValType.INT && x.vType == ValType.INT) {
//            return new Variable(intVal <= x.intVal);
//        } else if (vType == ValType.FLOAT && x.vType == ValType.FLOAT) {
//            return new Variable(floatVal <= x.floatVal);
//        } else {
//            parser.print_error("Type mismatch at ' le '");
//        }
//        return this;
//    }
//    
//    public Variable gt(Variable x) {
//        if (vType == ValType.INT && x.vType == ValType.INT) {
//            return new Variable(intVal > x.intVal);
//        } else if (vType == ValType.FLOAT && x.vType == ValType.FLOAT) {
//            return new Variable(floatVal > x.floatVal);
//        } else {
//            parser.print_error("Type mismatch at ' gt '");
//        }
//        return this;
//    }
    
//    public Variable ge(Variable x) {
//        if (vType == ValType.INT && x.vType == ValType.INT) {
//            return new Variable(intVal >= x.intVal);
//        } else if (vType == ValType.FLOAT && x.vType == ValType.FLOAT) {
//            return new Variable(floatVal >= x.floatVal);
//        } else {
//            parser.print_error("Type mismatch at ' ge '");
//        }
//        return this;
//    }
    
    public Variable plus(Variable x) {
        if (vType == ValType.INT && x.vType == ValType.INT) {
            return new Variable(intVal + x.intVal);
        } else if (vType == ValType.FLOAT && x.vType == ValType.FLOAT) {
            return new Variable(floatVal + x.floatVal);
        } else {
            parser.print_error("Type mismatch at ' + '");
        }
        return this;
    }
    
    public Variable minus(Variable x) {
        if (vType == ValType.INT && x.vType == ValType.INT) {
            return new Variable(intVal - x.intVal);
        } else if (vType == ValType.FLOAT && x.vType == ValType.FLOAT) {
            return new Variable(floatVal - x.floatVal);
        }  else {
            //parser.print_error("Type mismatch at ' - '");
            parser.print_error("Type mismatch at ' - '");
        }
        return this;
    }
    
    public Variable times(Variable x) {
        if (vType == ValType.INT && x.vType == ValType.INT) {
            return new Variable(intVal * x.intVal);
        } else if (vType == ValType.FLOAT && x.vType == ValType.FLOAT) {
            return new Variable(floatVal * x.floatVal);
        } else {
            parser.print_error(intVal.toString());
            parser.print_error("Type mismatch at ' * '");
        }
        return this;
    }
    
    public Variable divide(Variable x) {
        if (vType == ValType.INT && x.vType == ValType.INT) {
            if (x.intVal == 0) {
                parser.print_error("Cannot divide by zero");
            }
            return new Variable(intVal / x.intVal);
        } else if (vType == ValType.FLOAT && x.vType == ValType.FLOAT) {
            if (x.floatVal == 0.0) {
                parser.print_error("Cannot divide by zero");
            }
            return new Variable(floatVal / x.floatVal);
        } else {
            parser.print_error("Type mismatch at ' / '");
        }
        return this;
    }
    
//    public Variable not() {
//        if (vType == ValType.BOOL) {
//            return new Variable(!boolVal);
//        } else {
//            parser.print_error("Type mismatch at ' ! '");
//        }
//        return this;
//    }
    
   
}
